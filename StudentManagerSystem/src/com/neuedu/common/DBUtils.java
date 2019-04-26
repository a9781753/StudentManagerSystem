package com.neuedu.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class DBUtils {
	
	
	private static Properties  properties = new Properties();
	
	
	static {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(is);
			Class.forName(properties.getProperty("jdbc.driver"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//获取连接
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("jdbc.url"),properties.getProperty("jdbc.username"),properties.getProperty("jdbc.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	
	//获取连接
	public static void close(Connection c,PreparedStatement p) {
		try {
			if(p!=null){
				p.close();
			}
			if(c!=null){
				c.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public static void close(Connection c,PreparedStatement p,ResultSet set) {
		
		try {
			if(set!=null) {
				set.close();
			}
			if(p!=null){
				p.close();
			}
			if(c!=null){
				c.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	
	
	
	
	
}
