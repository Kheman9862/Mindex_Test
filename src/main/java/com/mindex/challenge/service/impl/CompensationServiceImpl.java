package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompensationRepository compensationRepository;

    /**
     *
     * In this I am just reading the compensation data for the given employeeId.
     * @param employeeId: id for which user wants to read the compensation data
     * @return List<Compensation>: After pulling the list of compensation it is returned to the user.
     *
     * */

    @Override
    public List<Compensation> getCompByEmpId(String employeeId) {
        LOG.debug("EmployeeId with the given compensation is: [{}]", employeeId);

        Employee employee = employeeService.read(employeeId);
        if(employee==null){
            throw new NullPointerException("Employee Id given is invalid Please check" + employeeId);
        }
        List<Compensation> compensation = compensationRepository.findByEmployee(employee);
        if (compensation == null) {
            throw new RuntimeException("Compensation for this EmployeeId is not present please insert it first: " + employeeId);
        }

        return compensation;

    }

    /**
     *
     * In this I am first checking if the given employee id is correct else it will throw an error.
     * Secondly if it is correct then it will insert the data in the database.
     * @param compensation: It is the compensation model data that i am receiving from the user in the form of JSON
     * @return compensation: After adding the compensation it is returned to the user.
     *
     * */

    @Override
    public Compensation createComp(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        String id = compensation.getEmployee().getEmployeeId();
        Employee employee = employeeService.read(id);
        if(employee==null){
            throw new NullPointerException("Employee Id given is invalid Please check" + id);
        }
        else{
            compensation.setCompensationId(UUID.randomUUID().toString());
            compensationRepository.insert(compensation);
        }

        return compensation;
    }
}
