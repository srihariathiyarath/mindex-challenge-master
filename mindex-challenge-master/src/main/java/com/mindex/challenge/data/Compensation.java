package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
	
	private int salary;
	private Employee employee;
	private Date effectiveDate;
	
	public Compensation() {
		
	}
	
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public Employee getEmployee() {
		return employee;

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	public Date getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
