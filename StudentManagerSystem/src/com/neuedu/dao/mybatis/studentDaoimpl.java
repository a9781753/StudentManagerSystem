package com.neuedu.dao.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.StudentCourse;

public class studentDaoimpl {

	//判断学时目前有没有这个课
	public   boolean isChoice(int sid,int cid) {
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
		StudentCourse courses = new StudentCourse(sid,cid);
		int a = session.selectOne("com.neuedu.pojo.Student.isexit",courses);
	if(a>0) {
		 
			
			return false;
	}
	return true;
	} 
	
	
	
	
	public static int ChoiceCourse(int sid, int cid) {
		int a = 0;
		if(new studentDaoimpl().isChoice(sid, cid)==true) {
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
			StudentCourse courses = new StudentCourse(sid,cid);
			a = session.insert("com.neuedu.pojo.Student.choiceCourse",courses);	
			session.commit();
	}

		return a;
		
	}	
	
	
	
	//学生课表
	public static  List<StudentCourse> findCoursesBySid(int sid) {
		List<StudentCourse> studentCourse = new ArrayList<StudentCourse>();
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
		
		studentCourse = session.selectList("com.neuedu.pojo.Student.studentcourses",sid);	
		return studentCourse;
	}
	
	
	
	//取消选课
	public static int deleteSutdentCourse(int sid,int cid) {
		int a=0;
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
		StudentCourse courses = new StudentCourse(sid,cid);
		a = session.update("com.neuedu.pojo.Student.deletecourse",courses);
		session.commit();
		return a;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
