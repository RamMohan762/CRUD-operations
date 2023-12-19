package com.marolix.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerLayer {
	
	public boolean isValidName(String name) {
		String regex="[A-Z][a-z]+";
		return name.matches(regex);
	}
	public boolean isValidPrice(Double double1) {
		return true;
	}
	public void isValidInputData(Hotel h) {
		if(!isValidName(h.getPname())) {
			throw new RuntimeException("Check the name entered "+h.getPname());
		}
		if(!isValidPrice(h.getPrice())) {
			throw new RuntimeException("Check the entered price"+h.getPrice());
		}
		
	}
	
	public void addItemToMenu(Hotel h) throws ClassNotFoundException {
		isValidInputData(h);
		List<Hotel> existingProduct=viewWithName(h.getPname());
		if(!existingProduct.isEmpty()) {
			throw new RuntimeException("Product already available "+h.getPname());
		}
		addItem(h);
		System.out.println("Item added successfully");
	}
	
	public void addItemInToMenu() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Product name");
		String name=sc.next();
		System.out.println("Enter price");
		Double price=sc.nextDouble();
		Hotel hotel=new Hotel(name,price);
		try {
			addItemToMenu(hotel);
		} catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public void addItem(Hotel h) throws ClassNotFoundException {
		try {
			Connection c=establishConnection();
			PreparedStatement ps=c.prepareStatement("insert into hotel_menu(pname,price)values(?,?);");
			ps.setString(1, h.getPname());
			ps.setDouble(2, h.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Hotel> viewWithName(String name) throws ClassNotFoundException {
		try {
		Connection c=establishConnection();
		PreparedStatement ps=c.prepareStatement("select * from hotel_menu where pname=?;");
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		List<Hotel> hotel=new ArrayList<>();
		while(rs.next()) {
			Integer id=rs.getInt("id");
			String pname=rs.getString("pname");
			Double price=rs.getDouble("price");
			Hotel h=new Hotel(id,pname,price);
			hotel.add(h);
		}
		return hotel;
		}
		catch (SQLException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public static void viewMenu()  {
		Connection c;
		try {
			c = establishConnection();
		
		Statement st=c.createStatement();
		String query="select * from hotel_menu;";
		ResultSet rs=st.executeQuery(query);
		System.out.println("id     pname     price");
		
		while(rs.next()) {
			Integer id=rs.getInt("id");
			String pname=rs.getString("pname");
			Double price=rs.getDouble("price");
			Hotel hotel=new Hotel(id,pname,price);
			
			System.out.println(id+"\t"+pname+"\t"+price);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getPrice() {
		
		try {
	Connection c = establishConnection();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Product name");
		String pname=scan.next();
		System.out.println("Enter quqntity");
		Integer quantity=scan.nextInt();
		String query="select price from hotel_menu where pname= ?;";
		PreparedStatement ps=c.prepareStatement(query);
		ps.setString(1,pname );
		ResultSet rs= ps.executeQuery();
		if(rs.next()) {
			Double price=rs.getDouble("price");
			price=quantity*price;
			System.out.println("The price of "+pname+" is "+price+" rupees.");
		}
		else {
			System.out.println("Product not found");
		}
		c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static Connection establishConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/set7_db","root","root");
		return c;
	}
	

}
