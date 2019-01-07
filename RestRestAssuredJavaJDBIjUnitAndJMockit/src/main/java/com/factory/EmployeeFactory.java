package com.factory;
import com.persistence.EmployeeDao;
import com.model.Employee;
import com.persistence.DbConnection;
import java.util.*;
public class EmployeeFactory {
	
	//Database Connection 
	private static EmployeeDao dao() {
    	DbConnection db = new DbConnection();
    	return db.getConnect().onDemand(EmployeeDao.class);
  	}
	
	//Add Employee Records 
	public static String addEmpDetails(int id,String name,float salary){
		int res = dao().addEmpDao(id,name,salary);
		if(res>0) {
			return "Successfully Inserted";
		}else {
			return "Failure try once again";
		}
	}

	//Delete Employee Records 
	public static String deleteEmp(int id){
		int res = dao().deleteEmpDao(id); 
		if(res>0) {
			return "Successfully Deleted";
		}else {
			return "There is no record with the id as "+id;
		}
	}

	//Increment Employee Salary 
	public static String raiseSalary(int id,float salary){
		int res = dao().incrementEmpSalary(id,salary);	
		if(res>0) {
			return "Successfully Incremented";
		}else {
			return "There is no record with the id as "+id;
		}
	}

	//Increment Employee Salary 
	public static String dropSalary(int id,float salary){
		int res = dao().decrementEmpSalary(id,salary);	
		if(res>0) {
			return "Successfully Decremented";
		}else {
			return "There is no record with the id as "+id;
		}
	}
	
	//Retrieve all Employee information  
	public static List<Employee> listAllEmp() {
    	List<Employee> es = dao().getAllEmployees();
    	return es;
  	}
}