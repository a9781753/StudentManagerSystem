package com.neuedu.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.pageModel;
import com.neuedu.dao.impl.StudentChoiceCourseDao;
import com.neuedu.dao.impl.jdbc;
import com.neuedu.dao.mybatis.studentDaoimpl;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.pojo.StudentCourse;
import com.neuedu.service.impl.CourseServiceimpl;
import com.neuedu.service.impl.StudentServiceimpl;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(updatastudent("z","z","z",1,"z",1,"5","5",17));
		
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
	
	//分页查看课程  按钮
	public static pageModel<Course> FindCourseByPage(int pageNo,int pageSize) {
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
	
	
	
	
	public static int deleteSutdentCourse1(int sid,int cid) {
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
	
	
	
	public static  List<StudentCourse> findCoursesBySid1(int sid) {
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
	
	public static  boolean findcourse(int sid,int cid) {
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
	
	//分页查看课程
	public static String findCourseByPage(int pageNo,int pageSize) {
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
		pageModel pagemodel =new pageModel(pageNo, pageSize);
		ResultSet resulitSet =session.selectOne("findcourseByPage",pagemodel);
		
		Map<Integer,Course> courses = CourseServiceimpl.getCourseServiceimpl().getCourses();
		
		int cid = 0;
			try {
				while(resulitSet.next()) {
					cid = resulitSet.getInt("cid");
					String cname = resulitSet.getString("cname");
					Course course = new Course(cid,cname);
					courses.put(cid,course);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
				if(cid==0){
					return ServerResponse.ServerResponseByFail(Const.STUDENT_COURSE_NULL,"当前页没有课程").obj2str();
				}
				
		
		return ServerResponse.ServerResponseBySucess("查课成功",courses.values()).obj2str();
		
		
	}
	
	

			public static void updatacourse(int cid,String cname) {
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
				session.update("updatecourse",course);
				session.commit();
				
				
			
				
			}
			
	
	
	//管理员查看课程
			public static  String findcourse(int cid) {
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
				session.selectList("deletecourse",cid);
			
			return ServerResponse.ServerResponseByFail(Const.STUDENT_COURSE_NULL,"课程列表为空").obj2str();
			}
	
	
	public static String addcourse(String cname) {
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
			return ServerResponse.ServerResponseBySucess("选课成功").obj2str();
			
		}catch(Exception e){
			
			return ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"已经有这门课了").obj2str();
		}	
				
		
	}
	
	public static String addcourse1(String cname,int sid,int cid) {
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
		if(isexit(sid,cid)==0) {
		session.insert("addcourse", cname);
		return  ServerResponse.ServerResponseBySucess("添加成功").obj2str();
		}
				
		return ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"已经选过这门课了").obj2str();
	}
	
	
	
	
	
	
	
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
	
	
	
	public  static ServerResponse<Admin> login(String username, String password) {
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
		Admin admin = new Admin(username,password);
		
		Admin admin1 = session.selectOne("login",admin);
		
		if (admin1!=null) {
			return  ServerResponse.ServerResponseBySucess("登录成功",admin);
		}
		
		return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_EXISTS,"用户名或密码错误");
	}
	
	public static boolean login1(String username) {
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
		
		
		int count = session.selectOne("hahaha",username);
		if (count>0) {
			return true;
		}
		
		return false;
	}
	   	 
	public int deleteSutdentCourse(int sid,int cid) {
		String sql = "delete from studentcourse where sid=? and cid=?";
		Connection connection = null;
		PreparedStatement statement = null;
		int a=0;
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, sid);
			statement.setInt(2, cid);
			a = statement.executeUpdate();
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if (statement!=null) {
					
					statement.close();
					
				}
				if (connection!=null) {
					connection.close();
					
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
		return a;
	}
	
	
	//查看已选课程
	public List<StudentCourse> findCoursesBySid(int sid) {
		List<StudentCourse> studentCourse = new ArrayList<StudentCourse>();
		String sql = "select id,sid,cid from studentcourse where sid=?";
		String sql1 = "select cname from courses where cid=?";
		Connection connection = null;
		PreparedStatement statement = null;
		int _cid=0;
		String cname=null;
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, sid);
			ResultSet resulitSet = statement.executeQuery();
			while(resulitSet.next()) {
				int id=resulitSet.getInt("id");
				int _sid=resulitSet.getInt("sid");
				_cid=resulitSet.getInt("cid");
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setInt(1, _cid);
				ResultSet resulitSet1 = statement1.executeQuery();
				
				while(resulitSet1.next()) {
					cname=resulitSet1.getString("cname");	
				}
				StudentCourse sc = new StudentCourse(id,_cid,sid,cname);
				studentCourse.add(sc);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if (statement!=null) {
					
					statement.close();
					
				}
				if (connection!=null) {
					connection.close();
					
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
		return studentCourse;
	}
	
			
	
	
	
	
	
	
	
	
	
	

}
