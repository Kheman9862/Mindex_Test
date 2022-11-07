package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureURL;
    private String employeeURL;
    private Employee employee;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Before
    public void setup() {
        employeeURL = "http://localhost:" + port + "/employee";
        reportingStructureURL = "http://localhost:" + port + "/reportingStructure/{id}";
        employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @Test
    public void testRead() {
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(4);

        // Read checks
        ReportingStructure readReporting = restTemplate.getForEntity(reportingStructureURL, ReportingStructure.class, employee.getEmployeeId()).getBody();

        //Assertions
        assertEquals(employee.getEmployeeId(), readReporting.getEmployee().getEmployeeId());
        assertEquals(reportingStructure.getNumberOfReports(),readReporting.getNumberOfReports());
    }

}
