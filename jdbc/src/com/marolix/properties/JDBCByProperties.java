package com.marolix.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCByProperties {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties p=new Properties();
		File f=new File("C:\\PropertiesDemo\\Properties\\abc.properties");
		FileInputStream fi=new FileInputStream(f);
		p.load(fi);
		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		//Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/set7_db", "root","root");
		Connection c=DriverManager.getConnection(url, username, password);
		Statement st=c.createStatement();
		boolean b=st.execute("update student set std_id=9 where std_name='Mohan'");
		System.out.println(b);

	}

}
