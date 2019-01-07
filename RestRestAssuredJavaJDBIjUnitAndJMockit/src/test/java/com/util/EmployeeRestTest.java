package com.util;
import java.util.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import org.junit.*;
import static org.junit.Assert.*;
import com.jayway.restassured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.given;
import org.codehaus.jackson.map.ObjectMapper;
import com.jayway.restassured.response.*;
public class EmployeeRestTest {
	final String baseUrl="http://localhost:9191/RestAssured/webapi/obj";
	//@Test
	public void testPlainText() throws AssertionError, URISyntaxException {
	String msg = given().
				 accept(ContentType.TEXT).
				 when().get(baseUrl).
				 getBody().
				 asString();
	assertEquals("<h1>Got it!</h1>",msg);	
	}
	
	//@Test
	public void testXml() throws AssertionError, URISyntaxException {
	String msg = given().
				 accept(ContentType.XML).
				 when().
				 get(baseUrl+"/xml").
				 getBody().
				 asString();
	assertEquals("<h1>Got it!</h1>",msg);	
	}

	//@Test
	public void testHtml() throws AssertionError, URISyntaxException {
	String msg = given().
				 accept(ContentType.HTML).
				 when().
				 get(baseUrl+"/html").
				 getBody().
				 asString();
	assertEquals("<h1>Got it!</h1>",msg);	
	}
	
	//@Test
	public void testQueryParam() throws AssertionError, URISyntaxException {
	String name = given().
				  accept(ContentType.TEXT).
				  queryParam("name","Raj").
				  when().
				  get(baseUrl+"/queryParam").
				  getBody().
		          asString();
	assertEquals("Welcome Raj",name);	
	}

	//@Test
	public void testPathParam() throws AssertionError, URISyntaxException {
	String fullname = given().
					  accept(ContentType.TEXT).
					  pathParam("fname","Raj").
					  pathParam("lname","Deep").
					  when().
				get(baseUrl+"/pathParam/{fname}/{lname}").
					  getBody().
					  asString();
	String name ="Welcome Raj, Deep";
	assertEquals(name,fullname);	
	}
	

	//@Test
	public void testEmpPlain() throws AssertionError, URISyntaxException {
	String empObj = given().
					queryParam("id","100").
					queryParam("name","Raj").
					queryParam("salary","12000").
					accept(ContentType.TEXT).
					when().
					get(baseUrl+"/empPlain").
					getBody().
					asString();
	String obj ="Id is 100 Name is Mr Raj Salary is 14000.0";
	assertEquals(obj,empObj);	
	}
	
	//@Test
	public void testEmpJson() throws AssertionError, URISyntaxException {
	Emp empJson = given().
				  queryParam("id","100").
				  queryParam("name","Raj").
				  queryParam("salary","12000").
				  accept(ContentType.JSON).
				  when().
				  get(baseUrl+"/empJson").
				  getBody().
				  as(Emp.class);
	assertEquals(100,empJson.getId());	
	assertEquals("Mr Raj",empJson.getName());
	assertEquals(14000.0f,empJson.getSalary(),2);	
	}

	
	//@Test
	public void testGetEmp() throws AssertionError, URISyntaxException {
	Emp emp = given().
			  accept(ContentType.JSON).
			  when().
			  get(baseUrl+"/getEmp").
			  getBody().
			  as(Emp.class);
	assertEquals(100,emp.getId());	
	assertEquals("Raj",emp.getName());
	assertEquals(12000.0f,emp.getSalary(),2);	
	}

	//@Test
	public void testGetEmployees() throws AssertionError, URISyntaxException {
	List<Emp> listOfEmp = given().
			  accept(ContentType.JSON).
			  when().
			  get(baseUrl+"/getEmps").
			  as(List.class);
	assertEquals(4,listOfEmp.size());
	System.out.println(listOfEmp.get(0));
	}


	@Test
	public void testGetEmployees() throws AssertionError, URISyntaxException {
	List<Employee> listOfEmp = given().
			  accept(ContentType.JSON).
			  when().
			  get(baseUrl+"/getEmps").
			  as(List.class);
	assertEquals(4,listOfEmp.size());
	System.out.println(listOfEmp.get(0));
	}
	
	//@Test
	public void testError() throws AssertionError, URISyntaxException {
	given().when().get("http://www.google.com").then().statusCode(200).assertThat();
	given().when().get(baseUrl+"/getEmps").then().statusCode(200).assertThat();
	given().when().get(baseUrl+"abc").then().statusCode(404).assertThat();
	//given().when().get(baseUrl+"/getEmps").then().contentType(ContentType.TEXT).assertThat();
	given().when().get(baseUrl+"/getEmps").then().contentType(ContentType.JSON).assertThat();
	}	
	
	/*	Passing the JSON format data as well as return the json data. 
	@Test
	public void testEmpJsonPost() throws AssertionError, URISyntaxException {
	Emp emp = new Emp();
	emp.setId(100);
	emp.setName("Seeta");
	emp.setSalary(14000);
	ObjectMapper mapper = new ObjectMapper();
	String jsonEmp=null;
	try{
	jsonEmp = mapper.writeValueAsString(emp);
	}catch(Exception e){
		System.out.println("Exception "+e);
	}
	given().header("content-Type", "application/json;charset=UTF-8").accept(ContentType.JSON).
	when().post(baseUrl+"/postData").getBody().as(Emp.class);

	//Emp empJson = given().header("accept","application/json").accept(ContentType.JSON).
	//body(jsonEmp).when().post(baseUrl+"/postData").as(Emp.class);
	
	//Emp empJson = res.as(Emp.class);
	
	//assertEquals(100,empJson.getId());	
	//assertEquals("Seeta",empJson.getName());
	//assertEquals(14000.0f,empJson.getSalary(),2);	
	}
	*/
	
}