package com.neuedu.common;

import java.io.PrintWriter;

import com.google.gson.Gson;

/**
 * ��Ӧǰ�˵ĸ߿��ö���
 * 
 * */

public class ServerResponse<T> {
	
	private static ServerResponse instance;
	private  int status;
	private String msg;
	private T data;
	
	public static ServerResponse getInstance() {
		if(instance==null) {
			return new ServerResponse();
		}
		return instance;
	}
	
	
	
	//�ӿڵ��óɹ�
	public static ServerResponse ServerResponseBySucess() {
		return new ServerResponse(0);		
	}
	//�ӿڵ��óɹ��������ַ���
	public static ServerResponse ServerResponseBySucess(String msg) {
		return new ServerResponse(0,msg);		
	}
	//���ýӿڳɹ��������ַ���+data
	public static <T> ServerResponse<T> ServerResponseBySucess(String msg,T data) {
		return new ServerResponse<T>(0,msg,data);		
	}
	//���ýӿ�ʧ��
	public static  ServerResponse ServerResponseByFail(int status) {
		return new ServerResponse(status);		
	}
	//���ýӿ�ʧ�ܣ������ַ���
	public static ServerResponse ServerResponseByFail(int status,String msg) {
		return new ServerResponse(status,msg);		
	}
	
	public  String  obj2str() {
   	 Gson gson=new Gson();
		String responseText=gson.toJson(this);
	    return responseText;
 }
	

	private ServerResponse(){
		
	}
	
	private ServerResponse(int status,String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	private ServerResponse(int status,T data) {
		this.status = status;
		this.data = data;
	}
	
	private ServerResponse(int status) {
		this.status = status;
	}
	
	private ServerResponse(int status,String msg,T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	//�жϽӿ��Ƿ���óɹ�
	public boolean isSucess() {
		return this.status == 0;
	}
	
	
	
	public  int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	

	
}
