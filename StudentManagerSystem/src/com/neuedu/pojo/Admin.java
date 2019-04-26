package com.neuedu.pojo;

public class Admin {

	//重写构造方法
	public Admin(String username,String password) {
		this.username = username;
		this.password = password;
		
	}
	//定义用户名和密码变量
	public String username;
	public String password;
	//获取用户名
	public String getUsername(){
		return username;
	}
	//查看用户名
	public void steUsername(String username){
		this.username = username;
	}
	//获取密码
	public String getPassword(){
		return password;
	}
	//查看用户名
	public void setPassword(String password){
		this.password = password;
	}
	
	
}
