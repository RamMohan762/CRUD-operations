package com.marolix.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
	
	public void loadProperties() throws IOException {
		Properties p=new Properties();
		File f=new File("C:\\PropertiesDemo\\Properties\\abc.properties");
		FileInputStream fi=new FileInputStream(f);
		p.load(fi);
		String name=p.getProperty("name");
		System.out.println("Name is "+name);
		System.out.println("url is "+p.getProperty("url"));
		System.out.println("Username ="+p.getProperty("username"));
		System.out.println("Password ="+p.getProperty("password"));
	}

	public static void main(String[] args) {
		PropertiesDemo pd=new PropertiesDemo();
		try {
			pd.loadProperties();
		} catch (IOException e) {
			
			System.out.println("Something went wrong "+e.getMessage());
		}

	}

}
