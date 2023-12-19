package com.marolix.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCConnectionDemo {
	
	public static void establishConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/set7_db", "root", "root");
		Statement statemt=connection.createStatement();
		String s1="alter table student modify std_name varchar(52)";
		String s2="insert into student(std_name,std_fname,class)values('Mohan','Reddy',13)";
		String s3="update student set std_id=8 where std_name='Mohan'";
		String s4="select * from student;";
		//System.out.println(statemt.executeQuery(s4));
		ResultSet rs=statemt.executeQuery(s4);
		System.out.println("Student details\n");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4));
		}
		
	
	}


	public static void main(String[] args) throws SQLException {
		
		try {
			establishConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
