package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Override
	public ReportingStructure read(String id){
		LOG.debug("Reading  the reporting structure for employee id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("employeeId is Invalid: " + id);
        }
        
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(getCompleteNumberOfReports(employee));

        return reportingStructure;
	}
	
	/**
	 * Recursively get the direct reports of an Employee and the Employee's direct reports.
	 * @param employee
	 * @return count of all of the direct reports in the tree
	 */
	private int getCompleteNumberOfReports(Employee employee) {
		if (employee == null || employee.getDirectReports() == null) {
			return 0;
		}
		
		int count = employee.getDirectReports().size();
		for (Employee directReportReference : employee.getDirectReports()) {
			Employee directReport = employeeRepository.findByEmployeeId(directReportReference.getEmployeeId());
			count += getCompleteNumberOfReports(directReport);
		}
		
		return count;
	}
}
