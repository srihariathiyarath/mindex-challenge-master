package com.mindex.challenge;

//import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
//import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBootstrapTest {

    @Autowired
    private DataBootstrap dataBootstrap;

    @Autowired
    private EmployeeRepository employeeRepository;
    
//    @Autowired
//    private CompensationRepository compensationRepository;

    @Before
    public void init() {
        dataBootstrap.init();
    }

/*
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4.
*/


    @Test
    public void test() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Lennon", employee.getLastName());
        assertEquals("Development Manager", employee.getPosition());
        assertEquals("Engineering", employee.getDepartment());
        
    }
}
