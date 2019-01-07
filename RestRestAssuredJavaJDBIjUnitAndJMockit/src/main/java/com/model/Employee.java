package com.model;
import org.skife.jdbi.v2.DBI;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
public class Employee {
	private int id;
	private String name;
	private float salary;
	private LocalDate doj;
	private LocalTime classStartTime;
	
	

	public Employee(){
	
	}
		
	public Employee(int id, String name, float salary){
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public Employee(int id, String name, float salary,LocalDate doj, LocalTime classStartTime){
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.doj = doj;
		this.classStartTime = classStartTime;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public LocalTime getClassStartTime() {
		return classStartTime;
	}
	public void setClassStartTime(LocalTime classStartTime) {
		this.classStartTime = classStartTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public float getSalary() {
		return salary;
	}

	@Override
	public boolean equals(Object obj){
		if(obj==null){
			return false;
		}
		if(getClass()!=obj.getClass()){
			return false;
		}
		Employee emp = (Employee)obj;
		if(Objects.equals(this.id,emp.id) && Objects.equals(this.name,this.name) && Objects.equals(this.salary,emp.salary)){
			return true;
		}else {
			return false;
		}
	}
	@Override 
	public int hashCode() {
		return Objects.hash(id,name,salary);
	}
	@Override
	public String toString() {
	return "Id is "+id+" Name is "+name+" Salary is "+salary; 
	}	
	
	
	
		
}