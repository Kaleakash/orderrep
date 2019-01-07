package com.util;

import com.model.Employee;
import com.factory.EmployeeFactory;
import com.persistence.EmployeeDao;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import mockit.*;
import java.util.*;
import mockit.integration.junit4.JMockit;
//@RunWith(JMockit.class)
public class EmployeeTest {
	//@Test
	public void testProperty() {
	
	//Empty constructor and getter method converage 
	final Employee emp = new Employee();
	emp.setId(100);
	emp.setName("Raj");
	emp.setSalary(12000);
	assertEquals(100,emp.getId());
	assertEquals("Raj",emp.getName());
	assertEquals(12000,emp.getSalary(),0.1);
	
	//Parameterized constructor and getter method converage
	Employee emp1 = new Employee(101,"Seeta",14000);
	assertEquals(101,emp1.getId());
	assertEquals("Seeta",emp1.getName());
	assertEquals(14000,emp1.getSalary(),0.1);

	//Equals method 
	assertNotEquals(emp1,null);
	assertEquals(emp1,emp1);
	assertNotEquals(emp,emp1);

	//Hashcode method 
	assertEquals(emp1.hashCode(),emp1.hashCode());
	}		
	
	//@Test 
	public void testAddEmpDetails(@Mocked final EmployeeDao empDao) {
		new Expectations(){
			{
				empDao.addEmpDao(1,"Raj",12000);result = 1;	
				empDao.addEmpDao(2,"Seeta",14000);result = 0;			
			}
		};
			new MockUp<EmployeeFactory>() {
			@Mock
			EmployeeDao dao() {
			return empDao;
			}
			};
			
			String res1 = EmployeeFactory.addEmpDetails(1,"Raj",12000);
			String res2 = EmployeeFactory.addEmpDetails(2,"Seeta",14000);
		    assertEquals("Successfully Inserted",res1);
			assertEquals("Failure try once again",res2);
	   }

	//@Test 
	public void testDeleteEmp(@Mocked final EmployeeDao empDao) {
		new Expectations(){
			{
				empDao.deleteEmpDao(100);result = 1;	
				empDao.deleteEmpDao(200);result = 0;			
			}
		};
			new MockUp<EmployeeFactory>() {
			@Mock
			EmployeeDao dao() {
			return empDao;
			}
			};
			
			String res1 = EmployeeFactory.deleteEmp(100);
			String res2 = EmployeeFactory.deleteEmp(200);
		    assertEquals("Successfully Deleted",res1);
			assertEquals("There is no record with the id as 200",res2);
	   }

	//@Test 
	public void testRaiseSalary(@Mocked final EmployeeDao empDao) {
		new Expectations(){
			{
				empDao.incrementEmpSalary(123,14000);result = 1;	
				empDao.incrementEmpSalary(456,16000);result = 0;			
			}
		};
			new MockUp<EmployeeFactory>() {
			@Mock
			EmployeeDao dao() {
			return empDao;
			}
			};
			
			String res1 = EmployeeFactory.raiseSalary(123,14000);
			String res2 = EmployeeFactory.raiseSalary(456,16000);
		    assertEquals("Successfully Incremented",res1);
			assertEquals("There is no record with the id as 456",res2);
	   }

	//@Test 
	public void testDropSalary(@Mocked final EmployeeDao empDao) {
		new Expectations(){
			{
				empDao.decrementEmpSalary(1234,18000);result = 1;	
				empDao.decrementEmpSalary(4567,20000);result = 0;			
			}
		};
			new MockUp<EmployeeFactory>() {
			@Mock
			EmployeeDao dao() {
			return empDao;
			}
			};
			
			String res1 = EmployeeFactory.dropSalary(1234,18000);
			String res2 = EmployeeFactory.dropSalary(4567,20000);
		    assertEquals("Successfully Decremented",res1);
			assertEquals("There is no record with the id as 4567",res2);
	   }

      //@Test 
	public void testListAllEmp(@Mocked final EmployeeDao empDao) {
		new Expectations(){
			{
				List<Employee> listOfRec =new ArrayList<Employee>();
				
				listOfRec.add(new Employee(1,"Ajay",12000));
				listOfRec.add(new Employee(2,"Vijay",14000));
				empDao.getAllEmployees(); result = listOfRec;		
			}
		};
			new MockUp<EmployeeFactory>() {
			@Mock
			EmployeeDao dao() {
			return empDao;
			}
			};
			
			List<Employee> listOfEmployee = EmployeeFactory.listAllEmp();
			
		    assertEquals(2,listOfEmployee.size());
			Employee emp = listOfEmployee.get(0);
			assertEquals(1,emp.getId());
			assertEquals("Ajay",emp.getName());
			assertEquals(12000,emp.getSalary(),0.1);
	   }
}
