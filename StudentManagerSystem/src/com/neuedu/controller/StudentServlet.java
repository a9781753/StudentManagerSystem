package com.neuedu.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.IO;
import com.neuedu.common.ServerResponse;

import com.neuedu.common.lOrR;
import com.neuedu.dao.impl.StudentDaoimpl;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Student;
import com.neuedu.service.IAdminService;
import com.neuedu.service.IStudentService;
import com.neuedu.service.impl.AdminServiceImpl;
import com.neuedu.service.impl.StudentServiceimpl;


@WebServlet("/student.do")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean issucc;
    public StudentServlet() {
        super();
     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		
		
		
		PrintWriter pw = response.getWriter();
		String operation = request.getParameter("operation");
		if(operation == null) {
			//返回客户端，必须选择一个功能
			//{"status":3,"msg":"必须选择一个功能"}}
			ServerResponse serverResponse = ServerResponse.ServerResponseByFail(Const.NOT_NUM,"必须选择一个功能");
			Gson gson = new Gson();
			String responseText = gson.toJson(serverResponse);
			pw.write(responseText);
			pw.close();
			
			
		}
		try {
			int _operation = Integer.parseInt(operation);
			if(_operation == lOrR.ADD.getOperation_type()){
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String sex = request.getParameter("sex");
				String _age = request.getParameter("age");
				int age = Integer.parseInt(_age);
				String _class = request.getParameter("_class");
				String _score = request.getParameter("score");
				int score = Integer.parseInt(_score);
				String rgetime = null;
				String modtime = null;
				int id=0;
				String responseText = AdminServiceImpl.getInstance().addstudent(name, password, sex, age, _class, score, rgetime, modtime, id);
				pw.write(responseText);
				pw.close();
			}else if(_operation == lOrR.FINDALL.getOperation_type()) {
				//查看所有学生4
					String _pageNo = request.getParameter("pageNo");
					int pageNo = Integer.parseInt(_pageNo);
					String _pageSize = request.getParameter("pageSize");
					int pageSize = Integer.parseInt(_pageSize);
					String responseText = AdminServiceImpl.getInstance().findtudent(pageNo, pageSize);
					pw.write(responseText);
					pw.close();
				
			}else if(_operation == lOrR.FINDBYID.getOperation_type()) {
				//根据ID查看学生信息5
				String _id = request.getParameter("id");
				int id = Integer.parseInt(_id);
				
					String responseText =new StudentDaoimpl().findStudentByid(id).obj2str();
					pw.write(responseText);
					pw.close();
					return;
				
			}else if(_operation == lOrR.UPDATA.getOperation_type()) {
				//修改学生信息6
				String _id = request.getParameter("id");
				int id = Integer.parseInt(_id);
				String name = request.getParameter("name");
				String sex = request.getParameter("sex");
				String _age = request.getParameter("age");
				int age = Integer.parseInt(_age);
				String _class = request.getParameter("_class");
				String rgetime = request.getParameter("rgetime");
				String modtime = request.getParameter("modtime");
				String _score = request.getParameter("score");
				int score = Integer.parseInt(_score);
				String password = request.getParameter("password");
				String responseText = AdminServiceImpl.getInstance().updatastudent(name, password, sex, age, _class, score, rgetime, modtime, id);

					pw.write(responseText);
					pw.close();
				
			}else if(_operation == lOrR.DELETE.getOperation_type()) {
				//删除学生信息7
				String _id = request.getParameter("id");
				int id = Integer.parseInt(_id);
			
					new StudentDaoimpl().deleteStudent(id);
					StudentServiceimpl.delStudent(id);
					String responseText = ServerResponse.ServerResponseBySucess("删除成功",StudentServiceimpl.delStudent(id).getData().values()).obj2str();
					pw.write(responseText);
					pw.close();
					return;
			
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			//返回客户端，必须输入数字
			//{"status":4,"msg":"operation必须是数字"}
			String responseText = ServerResponse.ServerResponseByFail(Const.NOT_NUM, "非法输入").obj2str();
			
		}
		
	}
		
		
		
		
		
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
