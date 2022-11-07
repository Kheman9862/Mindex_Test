package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public List<Employee> readAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);
        Employee updatedEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setPosition(employee.getPosition());
        updatedEmployee.setDepartment(employee.getDepartment());
        updatedEmployee.setDirectReports(employee.getDirectReports());
        return employeeRepository.save(updatedEmployee);
    }


    /**
     *
     * This is the read method to obtain the total number of reports
     * received by an employee. In this first employee and total
     * number of reports will be extracted for the given employeeId.
     * Then both the values obtained will set both the properties.
     * @param employeeId:
     * @return reportingStructure: This will return back the reporting structure model with values set in them.
     *
     * */

    @Override
    public ReportingStructure GetReportingStructure(String employeeId) {
            LOG.debug("EmployeeId with the given ReportingStructure is : [{}]", employeeId);
            Employee employee = this.read(employeeId);
            if(employee==null){
                throw new NullPointerException("Employee not found. Please type the correct employee Id");
            }
            HashSet<String> checkCycle = new HashSet<>();
            int totalDirectReports = GetUniqueReports(employeeId, checkCycle);
            ReportingStructure reportingStructure = new ReportingStructure();
            reportingStructure.setEmployee(employee);
            reportingStructure.setNumberOfReports(totalDirectReports);
            return reportingStructure;
    }

    /**
     *
     * A really good concept that was seen is sometimes data can contain a cycle, and we have to make sure that if there is a cycle we should simply
     * return instead of calculating because otherwise it will go to infinite loop.
     *
     * To resolve this issue i have used HashSet which will store the employeeId and if it sees the same id again it will return -2(subtracting the sum+1) else
     * we will further go inside the recursion.
     *
     *   @param employeeId:
     *   @param checkCycle:
     *   @return int: This will return back the sum of unique reports.
     *
     * */

    public int GetUniqueReports(String employeeId, HashSet<String> checkCycle) {
        int sum = 0;
        if(checkCycle.contains(employeeId)){
            return -2;
        }else{
            checkCycle.add(employeeId);
        }
        Employee employee = this.read(employeeId);
        List<Employee> reports = employee.getDirectReports();
        if (reports != null) {
            for (int i=0;i<reports.size();i++) {
                sum += (GetUniqueReports(reports.get(i).getEmployeeId(),checkCycle)+1);
            }
        }
        return sum;
    }

}
