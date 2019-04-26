package com.neuedu.dao.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.pageModel;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.pojo.StudentCourse;
import com.neuedu.service.impl.CourseServiceimpl;

public class AdminDaoimpl {

	//学生判断有没有选这个课
public static int isexit(int sid,int cid) {
		
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		StudentCourse courses = new StudentCourse(sid,cid);
		session = sqlMapper.openSession();
		int count = session.selectOne("isexit",courses);
		
		return count;
		
		
	}
	
	//管理员添加课程
	public  String addcourse(String cname) {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession();
		try {
			session.insert("addcourse", cname);
			session.commit();
			return ServerResponse.ServerResponseBySucess("添加成功").obj2str();
			
		}catch(Exception e){
			
			return ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"已经有这门课了").obj2str();
		}	
				
		
	}
	
	
	

	//管理员查看课程
	public static  String findcourse() {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession();
		List<Course> courses = new ArrayList<>();
		courses = session.selectList("findcourse");
	if(courses!=null) {
		 
			
			return ServerResponse.ServerResponseBySucess("查询成功",courses).obj2str();
	}
	return ServerResponse.ServerResponseByFail(Const.STUDENT_COURSE_NULL,"课程列表为空").obj2str();
	}
	

		
	//修改课程
	public static ServerResponse updatacourse(int cid,String cname) {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession();
		Course course = new Course(cid,cname);
		int a = session.update("updatecourse",course);
		session.commit();
			return ServerResponse.ServerResponseBySucess("修改成功");

	}
			
	
	
	
	//分页查看课程  按钮
	public pageModel<Course> FindCourseByPage(int pageNo,int pageSize) {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		SqlSession session1;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		List<Course>  courses = new ArrayList<Course>();
		pageModel<Course> pagemodel = new pageModel<Course>();
		List list = new ArrayList();
		session = sqlMapper.openSession();
		session1 = sqlMapper.openSession();
		int record  = session.selectOne("count");			
		int totalPage = 0;
		if(record%pageSize == 0) {
			totalPage = record/pageSize;
		}else {
			totalPage = record/pageSize+1;
		}
		
		pagemodel.setTotalPage(totalPage);
		pagemodel.setCurrentPage(pageNo);
		pagemodel.setHasbefore(pageNo>1);
		pagemodel.setHasnext(pageNo<totalPage);
		int a=(pageNo-1)*pageSize;
		if(a>=0) {
			pagemodel.setPageNo(a);
		}else {
			pagemodel.setPageNo(0);
		}
		
		pagemodel.setPageSize(pageSize);
		list = session1.selectList("findcourseByPage",pagemodel);
		pagemodel.setData(list);
		return pagemodel;
	}
	
	
	
	//分页查看学生
public static pageModel<Student> findstudentBypage(int pageNo,int pageSize) {
		
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		SqlSession session1;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		List<Student>  courses = new ArrayList<Student>();
		pageModel<Student> pagemodel = new pageModel<Student>();
		List list = new ArrayList();
		session = sqlMapper.openSession();
		session1 = sqlMapper.openSession();
		int record  = session.selectOne("studentcount");			
		int totalPage = 0;
		if(record%pageSize == 0) {
			totalPage = record/pageSize;
		}else {
			totalPage = record/pageSize+1;
		}
		
		pagemodel.setTotalPage(totalPage);
		pagemodel.setCurrentPage(pageNo);
		pagemodel.setHasbefore(pageNo>1);
		pagemodel.setHasnext(pageNo<totalPage);
		int a=(pageNo-1)*pageSize;
		if(a>=0) {
			pagemodel.setPageNo(a);
		}else {
			pagemodel.setPageNo(0);
		}
		
		pagemodel.setPageSize(pageSize);
		list = session1.selectList("findStudentByPage",pagemodel);
		pagemodel.setData(list);
		return pagemodel;
		
	}
	

	//添加学生
	public  static ServerResponse addstudent(String name,String password,String sex,int age,String _class,int score,String rgetime,String modtime,int id) {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession();
		Student student = new Student(id,name,password,sex,age,_class,score,rgetime,modtime);
			session.insert("addStudent", student);
			session.commit();
			return ServerResponse.ServerResponseBySucess("添加成功");
	}

			
	//修改学生
	public static int updatastudent(String name,String password,String sex,int age,String _class,int score,String rgetime,String modtime,int id) {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		int a = 0;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession();
		Student student = new Student(id,name,password,sex,age,_class,score,rgetime,modtime);
		a = session.insert("updateStudent", student);
		session.commit();
		return a;

	}
	
	
	
	
	
	
	
	
	
}
