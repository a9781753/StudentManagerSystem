package com.neuedu.pojo;

public class Admin {

	//��д���췽��
	public Admin(String username,String password) {
		this.username = username;
		this.password = password;
		
	}
	//�����û������������
	public String username;
	public String password;
	//��ȡ�û���
	public String getUsername(){
		return username;
	}
	//�鿴�û���
	public void steUsername(String username){
		this.username = username;
	}
	//��ȡ����
	public String getPassword(){
		return password;
	}
	//�鿴�û���
	public void setPassword(String password){
		this.password = password;
	}
	
	
}
