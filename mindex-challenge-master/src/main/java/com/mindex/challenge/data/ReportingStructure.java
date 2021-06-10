package com.mindex.challenge.data;

/* Using simple setter and getter methods */

public class ReportingStructure {
	
	private Employee employee;
	private int numberOfReports;
	
	public ReportingStructure() {
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public int getNumberOfReports() {
		return numberOfReports;
	}
	

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
}
