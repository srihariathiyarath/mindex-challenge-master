package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
	private String compensationUrl;
    private String compensationWithIdUrl;
    private String employeeIdUrl;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	compensationUrl = "http://localhost:" + port + "/compensation";
    	compensationWithIdUrl = "http://localhost:" + port + "/compensation/{id}";
    	employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    public void testCreateReadUpdate() {
    	String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    	Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, employeeId).getBody();
    	assertNotNull(readEmployee);
    	
    	Date effectiveDate = new Date();
    	Compensation testCompensation = new Compensation();
    	testCompensation.setEmployee(readEmployee);
    	testCompensation.setSalary(125000);
    	testCompensation.setEffectiveDate(effectiveDate);

        // Create checks
    	Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation);
        assertNotNull(createdCompensation.getEmployee());
        assertEquals(employeeId, createdCompensation.getEmployee().getEmployeeId());
        assertEquals(125000, createdCompensation.getSalary());
        assertEquals(effectiveDate, createdCompensation.getEffectiveDate());


        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationWithIdUrl, Compensation.class, employeeId).getBody();
        assertNotNull(readCompensation);
        assertEquals(employeeId, readCompensation.getEmployee().getEmployeeId());
        assertEquals(125000, createdCompensation.getSalary());
        assertEquals(effectiveDate, createdCompensation.getEffectiveDate());
    }
}
