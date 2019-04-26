package com.neuedu.dao;

import java.util.List;

import com.neuedu.pojo.StudentCourse;

public interface IStudentChoiceCourseDao {
	
	/**
	 * 判断学生是否已经选了这门课
	 * 
	 * @param return>0 已经选过了
	 * */
	public int isChoice(int sid,int cid);
	
	
	
	
	/**
	 * 学生选课
	 * 
	 * @param return>0   选课成功
	 * */
	public int ChoiceCourse(int sid,int cid);
	
	
	
	/**
	 * 学生kebiao
	 * 
	 * @param return 学生课表集合
	 * */
	public List<StudentCourse> findCoursesBySid(int sid);
	

	/**
	 * 取消选课
	 * 
	 * @param return>0   取消成功
	 * */
	public int deleteSutdentCourse(int sid,int cid);




	
	
	
	
	
}
