package com.neuedu.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.neuedu.common.Const;
import com.neuedu.common.IO;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.mybatis.AdminDaoimpl;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.service.ICourseService;

public class CourseServiceimpl implements ICourseService {
	private static CourseServiceimpl instance;
	static Map<Integer,Course> courses = new HashMap<Integer,Course>();
	private CourseServiceimpl() {
		
	}
	public static CourseServiceimpl getCourseServiceimpl() {
		if(instance == null) {
			return new CourseServiceimpl();
		}
		return instance;
	}
	
	public  Map<Integer,Course> getCourses() {
		return courses;
	}
	
	//����Ա��ӿγ���Ϣ
	public String  addCourse(String cname) {
	
						String course =  new com.neuedu.dao.mybatis.AdminDaoimpl().addcourse(cname);
						return course;
						
		}
	
	
	//����Աchakan�γ���Ϣ
	public String  findCourse() {
		
						String course =  new com.neuedu.dao.mybatis.AdminDaoimpl().findcourse();
						return course;
						
		}
	
	
	
	//����Ա��ҳ�鿴�γ���Ϣ
	public String  findCourseByPage(int pageNo,int pageSize) {
		
						String course =  new com.neuedu.dao.mybatis.AdminDaoimpl().FindCourseByPage(pageNo, pageSize).obj2str();
						return course;
						
		}
	
	//����Ա�޸Ŀγ���Ϣ
	public static ServerResponse updataCourse(int cid,String cname) {
		
	
				return   new com.neuedu.dao.mybatis.AdminDaoimpl().updatacourse(cid, cname);
						
										
			
	}
	
	
	//����Աɾ���γ���Ϣ
	public static  void delCourse(int cid) {
		new IO().fanxulie();
		
		if(courses.containsKey(cid)) {
			courses.remove(cid);
		}
		
	}
	
	//����Ա����ID�鿴�γ���Ϣ
	public static  ServerResponse findCourseByCid(int cid) {
		if(courses.containsKey(cid)) {
		return ServerResponse.ServerResponseBySucess("�Ѹ��ݿγ�id�鵽�γ�", courses.get(cid));
		}else {
			return ServerResponse.ServerResponseByFail(Const.COURSE_NOT_EXISTS,"��ID������");
		}
	}
	
	
	
	//�ж���û�����ID
	public static  boolean chaCourse(int cid) {
		
		return courses.containsKey(cid);
		
		}
	
	
	public static boolean chaCourseName(int cid) {
		Collection<Course> cs = courses.values();
		Iterator<Course> iterator = cs.iterator();
		while(iterator.hasNext()) {
			Course c=iterator.next();
			if(c.getCid()==cid) {
				return true;
			}
			
		}
		return false;
		
		
	}
		
	
	
	

}
