package com.marolix.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HotelProject {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/set7_db"; 
        String username = "root"; 
        String password = "root"; 
        
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter Product name");
        String productName =scan.next();
        System.out.println("Enter no of quantity");
        int quantity=scan.nextInt();
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT price FROM hotel_menu WHERE pname = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);

            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                double price = resultSet.getDouble("price");
                price=price*quantity;
                System.out.println("The price of " + productName + " with quantity " + quantity + " is: $" + price);
            } else {
                System.out.println("Product not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}


}
