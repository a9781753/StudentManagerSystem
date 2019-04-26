package com.neuedu.service.impl;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.impl.StudentChoiceCourseDao;
import com.neuedu.dao.mybatis.studentDaoimpl;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.pojo.StudentCourse;
import com.neuedu.service.StudentChoiseCourseService;
import com.neuedu.test.test;

public class StudentChoiseCourseServiceimpl implements StudentChoiseCourseService {

	private static StudentChoiseCourseServiceimpl studentChoiseCourseServiceimpl;
	private StudentChoiseCourseServiceimpl() {}
	public static StudentChoiseCourseServiceimpl getstudentChoiseCourseServiceimpl() {
		if(studentChoiseCourseServiceimpl == null) {
			return new StudentChoiseCourseServiceimpl();
		}
		return studentChoiseCourseServiceimpl;
	}
	
	//ѧ��ѡ��
		public  ServerResponse studentChoiceCourse(int sid,int cid){
			if(new com.neuedu.dao.mybatis.studentDaoimpl().ChoiceCourse(sid, cid)>0) {

				return ServerResponse.ServerResponseBySucess("ѡ�γɹ�");					
				
			}else {
				return ServerResponse.ServerResponseByFail(Const.COURSE_NAME_EXISTS,"�Ѿ�ѡ�����ſ���");
			}
		}
		
	
	
	//ѧ�����
	public  ServerResponse<List<StudentCourse>> studentFindCourse(int sid){
		
		
		
		
			return ServerResponse.ServerResponseBySucess("��ѯ�ɹ�",new com.neuedu.dao.mybatis.studentDaoimpl().findCoursesBySid(sid));					
			

		
	}
	
	
	public ServerResponse stuedntDeleteCourse(int sid,int cid) {
		
		if(new com.neuedu.dao.mybatis.studentDaoimpl().deleteSutdentCourse(sid, cid)==1) {
			return ServerResponse.ServerResponseBySucess("ɾ���ɹ�");	
		}
		
		
		
		return ServerResponse.ServerResponseByFail(250,"ɾ��ʧ��");
	}
	
	
	
	
	
	
}
