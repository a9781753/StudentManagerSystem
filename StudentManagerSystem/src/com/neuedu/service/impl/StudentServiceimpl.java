package com.neuedu.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.neuedu.common.Const;
import com.neuedu.common.IO;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.service.IStudentService;




public class StudentServiceimpl implements IStudentService{
	private static final long serialVersionUID = 5353785332727880550L;
	private static StudentServiceimpl studentServiceimpl;
	static Map<Integer,Student> students = new HashMap<Integer,Student>();

	static String rgetime=null;
	static String modtime=null;
	public static void main(String[] args) {

	}
	
	private StudentServiceimpl() {
		
	}
	
	public static StudentServiceimpl getstudentServiceimpl() {
		if(studentServiceimpl == null) {
			return new StudentServiceimpl();
		}
		return studentServiceimpl;
	}
	
	public  Map<Integer,Student> getstudents() {
		return students;
	}

	

	
	
	
	
	
	//获取当前时间
	private static String date() {
		long date = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String _date = sdf.format(date);
		return _date;
	}
	
	//添加学生信息
			public static ServerResponse<Student> addStudent(int id,String name,String password,String sex,int age,String _class,int score, String rgetime, String modtime) {
				IO io = new IO();
				io.fanxulie();
								rgetime = date();
								modtime = date();
								Student student = new Student(id,name,password,sex,age,_class,score,rgetime,modtime);
								students.put(student.getId(), student);
								io.xulie();
								return ServerResponse.ServerResponseBySucess("添加成功",student);
				}
			
			
			//修改学生信息
			public static ServerResponse<Student> modStudent(int id,String name,String sex,int age,String _class,int score, String rgetime, String modtime) {
				new IO().fanxulie();
			
						    	
								Student student = students.get(id);
								modtime = date();
								student.id=id;
								student.name=name;
								student.sex=sex;
								student.age=age;
								student._class=_class;
								student.score = score;
								student.modtime = modtime;
								new IO().xulie();
								return ServerResponse.ServerResponseBySucess("修改成功",student);
								
						
					
					
							
					
			}
			
			
			
			//判断有没有这个ID
			public static  boolean chaStudent(int id) {
				
				return students.containsKey(id);
				
				}
				
			
			
			//删除学生信息
			public static  ServerResponse<Map<Integer, Student>> delStudent(int id) {
				new IO().fanxulie();
				
				if(students.containsKey(id)) {
					students.remove(id);
				}
				return ServerResponse.ServerResponseBySucess("删除成功",students);
				
			}
			
			//根据ID查看学生信息
			public static  ServerResponse<Student> SeeStudentById(int id) {
				
				return ServerResponse.ServerResponseBySucess("查看成功",students.get(id));
			}
			
			
			

			
			
			
			
			
			
			
			
			
	
			//登录
			public ServerResponse<Student> studentlogin(int id, String password) {
				// TODO Auto-generated method stub
				
				//step1:判断参数是否存在
				
				if(id+""==null) {
					return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_NULL, "用户名不能为空");
				}
				
				if(password==null||password.equals("")) {
					return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_NULL, "密码不能为空");
				}
				
				//step2:校验用户名是否存在
				if(chaStudent(id)) {//学号存在
						
					Student student=students.get(id);
					int _id=student.getId();
					String _password=student.getPassword();
					if(_id==id) {//学号存在
						
						if(password.equals(_password)) {//登录成功
							
							return ServerResponse.ServerResponseBySucess("登录成功",student);
							
						}else {//id 和password 不匹配
							return ServerResponse.ServerResponseByFail(Const.PASSWORD_ERROR, "密码错误");
							
						}
					}
				
				}else {
					
					return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_EXISTS,"用户名不存在");
					
				}
				
			   
				
				
				return null;
			}
			

			
	
		
				
				
				
			

}
