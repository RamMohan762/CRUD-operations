package com.marolix.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Repositry {
	
	private static final String url="jdbc:mysql://localhost:3306/set7_db";
	private static final String username="root";
	private static final String password="root";
	
	public static Connection c=null;
	
	public static int deleteEmployee(int id) throws SQLException {
		try {
		Connection c=establishConnection();
		Statement st=c.createStatement();
		String delete="delete from employee where emp_id="+id+";";
		return st.executeUpdate(delete);
		}
		finally {
			c.close();
		}
	}
	
	public static int updateEmployee(Integer emp_id,String emp_name) throws SQLException {
		try {
		Connection c=establishConnection();
		Statement st=c.createStatement();
		String update="update employee set emp_name="+emp_name+" where emp_id="+emp_id+";";
		System.out.println(update);
		return st.executeUpdate(update);
		}
		finally {
			c.close();
		}
	}
	
	public static List<Employee> readAllEmployee() throws SQLException{
		try {
		Connection c=establishConnection();
		Statement stm=c.createStatement(); 
		String query="select * from employee;";
		ResultSet rs=stm.executeQuery(query);
		List<Employee> list=new ArrayList<>();
		while(rs.next()) {
			Employee e=new Employee();
			e.setEmpid(rs.getInt(1));
			e.setDomain(rs.getString("domain"));
			e.setLocation(rs.getString(5));
			e.setName(rs.getString(2));
			e.setSalary(rs.getFloat("salary"));
			list.add(e);
		}
		return list;
		}
		finally {
			c.close();
		}
		
	}
	
	public static int insert(Employee e) throws SQLException {
		try {
		Connection c=establishConnection();
		Statement stmt=c.createStatement();
	   String query= String.format("insert into employee(emp_id,emp_name,salary,domain,location)values(%d,\"%s\",%f,\"%s\",\"%s\");", e.getEmpid(),e.getName(),e.getSalary(),e.getDomain(),e.getLocation());
	    System.out.println(query);
	   return stmt.executeUpdate(query);
		}
		finally {
			c.close(); 
		}
	}
	
	public static boolean createTable() throws SQLException {
		try {
		String query="create table employee(emp_id int primary key,emp_name varchar(50),salary float,domain varchar(50),location varchar(20));";
		Connection c=establishConnection();
		Statement st=c.createStatement();
		boolean b=st.execute(query);
		return b;
		}
		finally {
			c.close();
		}
		
	}
	private static Connection establishConnection() throws SQLException {
		c=DriverManager.getConnection(url, username, password);
		return c;
		
	}

}
