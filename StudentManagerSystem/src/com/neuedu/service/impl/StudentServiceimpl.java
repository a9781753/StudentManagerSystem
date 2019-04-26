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

	

	
	
	
	
	
	//��ȡ��ǰʱ��
	private static String date() {
		long date = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String _date = sdf.format(date);
		return _date;
	}
	
	//���ѧ����Ϣ
			public static ServerResponse<Student> addStudent(int id,String name,String password,String sex,int age,String _class,int score, String rgetime, String modtime) {
				IO io = new IO();
				io.fanxulie();
								rgetime = date();
								modtime = date();
								Student student = new Student(id,name,password,sex,age,_class,score,rgetime,modtime);
								students.put(student.getId(), student);
								io.xulie();
								return ServerResponse.ServerResponseBySucess("��ӳɹ�",student);
				}
			
			
			//�޸�ѧ����Ϣ
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
								return ServerResponse.ServerResponseBySucess("�޸ĳɹ�",student);
								
						
					
					
							
					
			}
			
			
			
			//�ж���û�����ID
			public static  boolean chaStudent(int id) {
				
				return students.containsKey(id);
				
				}
				
			
			
			//ɾ��ѧ����Ϣ
			public static  ServerResponse<Map<Integer, Student>> delStudent(int id) {
				new IO().fanxulie();
				
				if(students.containsKey(id)) {
					students.remove(id);
				}
				return ServerResponse.ServerResponseBySucess("ɾ���ɹ�",students);
				
			}
			
			//����ID�鿴ѧ����Ϣ
			public static  ServerResponse<Student> SeeStudentById(int id) {
				
				return ServerResponse.ServerResponseBySucess("�鿴�ɹ�",students.get(id));
			}
			
			
			

			
			
			
			
			
			
			
			
			
	
			//��¼
			public ServerResponse<Student> studentlogin(int id, String password) {
				// TODO Auto-generated method stub
				
				//step1:�жϲ����Ƿ����
				
				if(id+""==null) {
					return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_NULL, "�û�������Ϊ��");
				}
				
				if(password==null||password.equals("")) {
					return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_NULL, "���벻��Ϊ��");
				}
				
				//step2:У���û����Ƿ����
				if(chaStudent(id)) {//ѧ�Ŵ���
						
					Student student=students.get(id);
					int _id=student.getId();
					String _password=student.getPassword();
					if(_id==id) {//ѧ�Ŵ���
						
						if(password.equals(_password)) {//��¼�ɹ�
							
							return ServerResponse.ServerResponseBySucess("��¼�ɹ�",student);
							
						}else {//id ��password ��ƥ��
							return ServerResponse.ServerResponseByFail(Const.PASSWORD_ERROR, "�������");
							
						}
					}
				
				}else {
					
					return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_EXISTS,"�û���������");
					
				}
				
			   
				
				
				return null;
			}
			

			
	
		
				
				
				
			

}
