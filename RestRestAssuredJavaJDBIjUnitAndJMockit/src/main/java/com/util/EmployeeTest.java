package com.util;
import com.factory.EmployeeFactory;
import com.model.Employee;
import java.util.*;	

public class EmployeeTest 
{
	static Scanner obj = new Scanner(System.in);
	static int id;		
	static String name;
	static float salary;
	public static void addEmp(int id,String name,float salary){
		String res = EmployeeFactory.addEmpDetails(id,name,salary);
		System.out.println(res);
	}
	public static void deleteEmp(int id) {
		String res = EmployeeFactory.deleteEmp(id);
		System.out.println(res);	
	}
	public static void incrementSalary(int id, float salary) {
		String res = EmployeeFactory.raiseSalary(id,salary);
		System.out.println(res);	
	}		
	public static void decrementSalary(int id, float salary) {
		String res = EmployeeFactory.dropSalary(id,salary);
		System.out.println(res);	
	}
	public static void displayAll() {
	System.out.println("All Employee Information");	
	List<Employee> listOfRec = EmployeeFactory.listAllEmp();
	Iterator<Employee> ii = listOfRec.iterator();
		while(ii.hasNext()) {
			Employee emp = ii.next();
			System.out.println(emp);
		}
	}
	
    	public static void main( String[] args )
    	{
	String con = null;
	do{
		System.out.println("1:Add Employee");
		System.out.println("2:Delete Employee");
		System.out.println("3:Increment Salary");
		System.out.println("4:Decrement Salary");
		System.out.println("5:Display All Employee Salary");
		System.out.println("6:Exit");
		int op = obj.nextInt();
		switch(op) {
		case 1:	System.out.println("Enter the id");
			id = obj.nextInt();
			System.out.println("Enter the name");
			name = obj.next();
			System.out.println("Enter the salary");
			salary = obj.nextFloat();
			addEmp(id,name,salary);
			break;
		case 2:	System.out.println("Enter the id");
			id = obj.nextInt();
			deleteEmp(id);
			break;
		case 3:	System.out.println("Enter the id");
			id = obj.nextInt();
			System.out.println("Enter the salary");
			salary = obj.nextFloat();
			incrementSalary(id,salary);
			break;
		case 4:	System.out.println("Enter the id");
			id = obj.nextInt();
			System.out.println("Enter the salary");
			salary = obj.nextFloat();
			decrementSalary(id,salary);
			break;	
		case 5:	displayAll();
			break;
		}
		System.out.println("Do you want to continue....");
		con = obj.next();
	}while(con.equals("y"));	
	}
}
