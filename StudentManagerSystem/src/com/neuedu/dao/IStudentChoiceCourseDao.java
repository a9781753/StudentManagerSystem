package com.neuedu.dao;

import java.util.List;

import com.neuedu.pojo.StudentCourse;

public interface IStudentChoiceCourseDao {
	
	/**
	 * �ж�ѧ���Ƿ��Ѿ�ѡ�����ſ�
	 * 
	 * @param return>0 �Ѿ�ѡ����
	 * */
	public int isChoice(int sid,int cid);
	
	
	
	
	/**
	 * ѧ��ѡ��
	 * 
	 * @param return>0   ѡ�γɹ�
	 * */
	public int ChoiceCourse(int sid,int cid);
	
	
	
	/**
	 * ѧ��kebiao
	 * 
	 * @param return ѧ���α���
	 * */
	public List<StudentCourse> findCoursesBySid(int sid);
	

	/**
	 * ȡ��ѡ��
	 * 
	 * @param return>0   ȡ���ɹ�
	 * */
	public int deleteSutdentCourse(int sid,int cid);




	
	
	
	
	
}
