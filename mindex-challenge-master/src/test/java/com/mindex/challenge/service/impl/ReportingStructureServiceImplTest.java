package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
	private String reportingStructureWithIdUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	reportingStructureWithIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testRead() {
    	// Read checks
    	String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
    	ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureWithIdUrl, ReportingStructure.class, employeeId).getBody();
    	assertNotNull(readReportingStructure.getEmployee());
    	assertEquals(employeeId, readReportingStructure.getEmployee().getEmployeeId());
    	assertEquals(4, readReportingStructure.getNumberOfReports());
    }
}
