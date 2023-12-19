package com.marolix.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JDBCCrudOperations {

	public static void insert() {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter employee details");
		System.out.println("Enter employee id");
		Integer empid=scan.nextInt();
		System.out.println("Enter emp name");
		String name=scan.next();
		System.out.println("Enter salary");
		Float f=scan.nextFloat();
		System.out.println("Enter domain");
		String domain=scan.next();
		System.out.println("Enter location");
		String location=scan.next();
		
		Employee e=new Employee();
		e.setEmpid(empid);
		e.setName(name);
		e.setSalary(f);
		e.setDomain(domain);
		e.setLocation(location);
		
		Repositry r=new Repositry();
		try {
			int a=r.insert(e);
			if(a>0) 
				System.out.println(a+" rows inserted successfully");
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		
	}
	
	public static void readAllRows() {
		Repositry r=new Repositry();
		try {
			List<Employee> emp=r.readAllEmployee();
			for(Employee e:emp) {
				System.out.println(e);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		Repositry r=new Repositry();
//		try {
//			boolean b=r.createTable();
//			if(b) {
//				System.out.println("Table created successfully");
//			}
//			else {
//				System.out.println("Something went wrong"); 
//			}
//		} catch (SQLException e) {
//			
//			System.out.println(e.getMessage());
//		}
		//insert();
		readAllRows();
//		Repositry r=new Repositry();
//		try {
//			System.out.println("Enter updating details");
//			Scanner scan=new Scanner(System.in);
//			System.out.println("Enter emp_id");
//			Integer id=scan.nextInt();
//			System.out.println("Enter Updating name");
//			String name=scan.next();
//			int a=r.updateEmployee(id,name);
//			if(a==1)
//				System.out.println("Updated successfully");
//			else
//				System.out.println("Not updated");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Repositry r=new Repositry();
//		try {
//			int a=r.deleteEmployee(1);
//			if(a==1)
//				System.out.println("Deleted successfully");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
   
		
	}

}
