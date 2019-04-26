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
	
	//管理员添加课程信息
	public String  addCourse(String cname) {
	
						String course =  new com.neuedu.dao.mybatis.AdminDaoimpl().addcourse(cname);
						return course;
						
		}
	
	
	//管理员chakan课程信息
	public String  findCourse() {
		
						String course =  new com.neuedu.dao.mybatis.AdminDaoimpl().findcourse();
						return course;
						
		}
	
	
	
	//管理员分页查看课程信息
	public String  findCourseByPage(int pageNo,int pageSize) {
		
						String course =  new com.neuedu.dao.mybatis.AdminDaoimpl().FindCourseByPage(pageNo, pageSize).obj2str();
						return course;
						
		}
	
	//管理员修改课程信息
	public static ServerResponse updataCourse(int cid,String cname) {
		
	
				return   new com.neuedu.dao.mybatis.AdminDaoimpl().updatacourse(cid, cname);
						
										
			
	}
	
	
	//管理员删除课程信息
	public static  void delCourse(int cid) {
		new IO().fanxulie();
		
		if(courses.containsKey(cid)) {
			courses.remove(cid);
		}
		
	}
	
	//管理员根据ID查看课程信息
	public static  ServerResponse findCourseByCid(int cid) {
		if(courses.containsKey(cid)) {
		return ServerResponse.ServerResponseBySucess("已根据课程id查到课程", courses.get(cid));
		}else {
			return ServerResponse.ServerResponseByFail(Const.COURSE_NOT_EXISTS,"该ID不存在");
		}
	}
	
	
	
	//判断有没有这个ID
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
