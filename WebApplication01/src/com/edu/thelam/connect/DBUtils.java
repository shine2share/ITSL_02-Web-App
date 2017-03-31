package com.edu.thelam.connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
	String url;
	String user;
	String password;
	Properties p = new Properties();

	public void initialize() {

		try {
			p.load(new FileInputStream("database.properties"));
			url = p.getProperty("url");
			user = p.getProperty("user");
			password = p.getProperty("password");
			System.out.println(url);
			System.out.println(user + "\n" + password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static Connection connection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "Iloveyou1992");
			if (con != null) {
				System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (Exception e) {
			System.out.println("Failed! " + e);
			System.exit(0);
		}
		return con;
	}

	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "Iloveyou1992");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

	}
}