package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeURL;
    private String createCompensationURL;
    private String readCompensationURL;
    private Employee employee;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setup() {
        employeeURL = "http://localhost:" + port + "/employee";
        createCompensationURL = "http://localhost:" + port + "/compensation";
        readCompensationURL = "http://localhost:" + port + "/compensation/employee/{id}";
        employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }


    @Test
    public void testCreateRead() {
        Compensation newTestCompensation = new Compensation();
        newTestCompensation.setEmployee(employee);
        newTestCompensation.setSalary(95000.0);
        newTestCompensation.setEffdate(new Date());

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(createCompensationURL, newTestCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getEmployee());
        assertCompensationEquivalence(newTestCompensation, createdCompensation);


        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(readCompensationURL, Compensation.class, createdCompensation.getEmployee()).getBody();
        assertEquals(employee.getEmployeeId(), createdCompensation.getEmployee().getEmployeeId());
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getCompensationId(), actual.getCompensationId());
    }
}