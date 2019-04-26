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
	
	//学生选课
		public  ServerResponse studentChoiceCourse(int sid,int cid){
			if(new com.neuedu.dao.mybatis.studentDaoimpl().ChoiceCourse(sid, cid)>0) {

				return ServerResponse.ServerResponseBySucess("选课成功");					
				
			}else {
				return ServerResponse.ServerResponseByFail(Const.COURSE_NAME_EXISTS,"已经选过这门课了");
			}
		}
		
	
	
	//学生查课
	public  ServerResponse<List<StudentCourse>> studentFindCourse(int sid){
		
		
		
		
			return ServerResponse.ServerResponseBySucess("查询成功",new com.neuedu.dao.mybatis.studentDaoimpl().findCoursesBySid(sid));					
			

		
	}
	
	
	public ServerResponse stuedntDeleteCourse(int sid,int cid) {
		
		if(new com.neuedu.dao.mybatis.studentDaoimpl().deleteSutdentCourse(sid, cid)==1) {
			return ServerResponse.ServerResponseBySucess("删除成功");	
		}
		
		
		
		return ServerResponse.ServerResponseByFail(250,"删除失败");
	}
	
	
	
	
	
	
}
