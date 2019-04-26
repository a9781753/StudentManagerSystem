package com.neuedu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ILoginDao;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Student;

public class LoginDao implements ILoginDao {
	
	public  ServerResponse<Admin> login(String username,String password) {
		Connection connection = null;
		PreparedStatement  statement = null;
		ResultSet res = null;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from admin where  username=? and password=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			statement.setString(2, password);
			res = statement.executeQuery();
			
			if(res.first()) {
				Admin admin = new Admin(username,password);
				return ServerResponse.ServerResponseBySucess("登录成功",admin);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				DBUtils.close(connection, statement,res);
		}
		
		return null;
	}
	
	
	
	public  ServerResponse<Student> sutdentLogin(String name,String password) {
		Connection connection = null;
		Statement  statement = null;
		ResultSet res = null;
		Student student = null;
		try {
			connection = DBUtils.getConnection();
			String sql = "select * from students where  name='"+name+"' and password='"+password+"'";
			statement = connection.createStatement();
			res = statement.executeQuery(sql);
			while(res.next()) {
			int age = res.getInt("age");
			int score = res.getInt("score");
			int id = res.getInt("id");
			String _class = res.getString("_class");
			String sex = res.getString("sex");
			String regtime = res.getString("regtime");
			String modtime = res.getString("modtime");
			 
			 student = new Student(id,name,password,sex,age,_class,score,regtime,modtime);
			
			}
			if(res.first()) {
				
				return ServerResponse.ServerResponseBySucess("登录成功",student);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				try {
					if(res!=null) {
						res.close();
					}
					if(statement!=null) {
						statement.close();
					}
					if(connection!=null) {
						connection.close();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return null;
	}
	

}
