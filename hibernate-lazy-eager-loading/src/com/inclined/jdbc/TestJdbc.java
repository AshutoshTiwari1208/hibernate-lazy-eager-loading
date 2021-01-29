package com.inclined.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcURL = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String username = "hbstudent";
		String password = "hbstudent";
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection established : "+ conn);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
