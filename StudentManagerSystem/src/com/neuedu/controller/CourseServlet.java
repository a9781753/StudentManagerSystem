package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.IO;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.lOrR;
import com.neuedu.dao.impl.AdminDaoimpl;
import com.neuedu.dao.impl.jdbc;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.service.IAdminService;
import com.neuedu.service.ICourseService;
import com.neuedu.service.impl.AdminServiceImpl;
import com.neuedu.service.impl.CourseServiceimpl;
import com.neuedu.service.impl.StudentServiceimpl;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/course.do")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CourseServlet() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter pw = response.getWriter();
		String operation = request.getParameter("operation");
		if(operation == null) {
			//返回客户端，必须传参数
			//{"status":3,"msg":"必须传参数"}}
			String responseText =ServerResponse.ServerResponseByFail(Const.NOT_NUM,"必须传参数").obj2str();
			pw.write(responseText);
			pw.close();
			
			
		}
		try {
			int _operation = Integer.parseInt(operation);
			if(_operation == lOrR.ADMIN_ADD_COURSE.getOperation_type()){//添加课程
				String cname = request.getParameter("cname");
				if(cname != null) {
					String responseText =CourseServiceimpl.getCourseServiceimpl().addCourse(cname);
					pw.write(responseText);
					pw.close();
					}
			}else if(_operation == lOrR.ADMIN_FIND_COURSE.getOperation_type()) {//查看课程
					String responseText = CourseServiceimpl.getCourseServiceimpl().findCourse();
					pw.write(responseText);
					pw.close();				
			}else if(_operation == lOrR.ADMIN_UPDATA_COURSE.getOperation_type()) {//修改课程
				String _cid = request.getParameter("cid");
				int cid = Integer.parseInt(_cid);
				String cname = request.getParameter("cname");
			
					if(CourseServiceimpl.getCourseServiceimpl().updataCourse(cid, cname).getStatus()==0) {
					String responseText = ServerResponse.ServerResponseBySucess("修改成功").obj2str();
					pw.write(responseText);
					pw.close();
					}else {
				String responseText = ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"课程名重复").obj2str();
				pw.write(responseText);
				pw.close();}
			}else if(_operation == lOrR.ADMIN_DELETE_COURSE.getOperation_type()) {//删除课程
				String _cid = request.getParameter("cid");
				int cid = Integer.parseInt(_cid);
					new jdbc().deletecourse(cid);
					String responseText = ServerResponse.ServerResponseBySucess("删除成功").obj2str();
					pw.write(responseText);
					pw.close();
					return;
				}else if(_operation == lOrR.ADMIN_FINDCOURSEBYPAGE.getOperation_type()) {//分页查看课程
					String _pageSize = request.getParameter("pageSize");
					int pageSize = Integer.parseInt(_pageSize);
					String _pageNo = request.getParameter("pageNo");
					int pageNo = Integer.parseInt(_pageNo);
					String responseText = new jdbc().findCourseByPage(pageNo,pageSize);
					pw.write(responseText);
					pw.close();				
				}else if(_operation == lOrR.ADMIN_FINDCOURSEBYPAGE1.getOperation_type()) {//分页查看课程按钮板
					String _pageSize = request.getParameter("pageSize");
					int pageSize = Integer.parseInt(_pageSize);
					String _pageNo = request.getParameter("pageNo");
					int pageNo = Integer.parseInt(_pageNo);
					String responseText = CourseServiceimpl.getCourseServiceimpl().findCourseByPage(pageNo, pageSize);
					pw.write(responseText);
					pw.close();				
		}
						
		} catch (NumberFormatException e) {
			// TODO: handle exception
			//返回客户端，必须输入数字
			//{"status":4,"msg":"operation必须是数字"}
			String responseText = ServerResponse.ServerResponseByFail(Const.NOT_NUM, "非法输入...").obj2str();
			pw.write(responseText);
			pw.close();
		}
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
