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

	//ѧ���ж���û��ѡ�����
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
	
	//����Ա��ӿγ�
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
			return ServerResponse.ServerResponseBySucess("��ӳɹ�").obj2str();
			
		}catch(Exception e){
			
			return ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"�Ѿ������ſ���").obj2str();
		}	
				
		
	}
	
	
	

	//����Ա�鿴�γ�
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
		 
			
			return ServerResponse.ServerResponseBySucess("��ѯ�ɹ�",courses).obj2str();
	}
	return ServerResponse.ServerResponseByFail(Const.STUDENT_COURSE_NULL,"�γ��б�Ϊ��").obj2str();
	}
	

		
	//�޸Ŀγ�
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
			return ServerResponse.ServerResponseBySucess("�޸ĳɹ�");

	}
			
	
	
	
	//��ҳ�鿴�γ�  ��ť
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
	
	
	
	//��ҳ�鿴ѧ��
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
	

	//���ѧ��
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
			return ServerResponse.ServerResponseBySucess("��ӳɹ�");
	}

			
	//�޸�ѧ��
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
