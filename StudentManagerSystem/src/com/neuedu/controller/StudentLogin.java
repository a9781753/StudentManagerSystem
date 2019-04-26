package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.lOrR;
import com.neuedu.dao.impl.LoginDao;
import com.neuedu.dao.impl.StudentDaoimpl;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.service.IAdminService;
import com.neuedu.service.IStudentService;
import com.neuedu.service.impl.AdminServiceImpl;
import com.neuedu.service.impl.StudentServiceimpl;

/**
 * Servlet implementation class StudentLogin
 */
@WebServlet("/studentlogin.do")
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean issucc;
    public StudentLogin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
	

		//����һ���Ự
		HttpSession session1 = request.getSession();
  		
  		String operation = request.getParameter("operation");
		if(operation == null) {
			//���ؿͻ��ˣ�����Ҫѡ���¼����ע��
			//{"status":3,"msg":"����ѡ���¼����ע��"}}
			String responseText =ServerResponse.ServerResponseByFail(3,"����ѡ���¼����ע��").obj2str();
			pw.write(responseText);
			pw.close();
			
			
		}
		try {
			int _operation = Integer.parseInt(operation);
			if(_operation == lOrR.STUDENT_LOGIN.getOperation_type()) {
				
				String name = request.getParameter("id");
				String password = request.getParameter("password");
				ServerResponse<Student> serverResponse= new LoginDao().sutdentLogin(name, password);
				Cookie username_cookie = new Cookie("name", name);
				username_cookie.setMaxAge(7*24*3600);
				response.addCookie(username_cookie);
				//����passwordcookie
				Cookie password_cookie = new Cookie("password", password);
				password_cookie.setMaxAge(7*24*3600);
				response.addCookie(password_cookie);
				
				
				if(serverResponse!=null) {
					session1.setAttribute("user1", serverResponse.getData());
					//Ϊѧ������һ�����ﳵ
					session1.setAttribute("cart",new ArrayList<Course>());
					String responseText = serverResponse.obj2str();
					pw.write(responseText);
					pw.close();
				}else {
					String responseText = ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_EXISTS,"�˺Ż��������").obj2str();
					pw.write(responseText);
					pw.close();
				}
				
				
				
						
		
			}else if(_operation == lOrR.STUDENT_REGISTER.getOperation_type()) {//ѧ��ע��
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String sex = request.getParameter("sex");
				String _age = request.getParameter("age");
				int age = Integer.parseInt(_age);
				String _class = request.getParameter("_class");
				String rgetime = request.getParameter("rgetime");
				String modtime = request.getParameter("modtime");
				String _score = request.getParameter("score");
				int score = Integer.parseInt(_score);
				String responseText = new StudentDaoimpl().addcourse(name, password, sex, age, _class, score);
				pw.write(responseText);
				pw.close();

			
			}
		
		} catch (NumberFormatException e) {
			// TODO: handle exception
			//���ؿͻ��ˣ�������������
			//{"status":4,"msg":"operation����������"}
			String responseText = ServerResponse.ServerResponseByFail(Const.NOT_NUM, "��ѡ���¼����ע��").obj2str();
			pw.write(responseText);
			pw.close();
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
