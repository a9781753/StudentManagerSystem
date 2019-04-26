package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.service.impl.AdminServiceImpl;

@WebServlet("/session.do")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SessionServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		boolean issucc;
		
		//会话
		HttpSession session = request.getSession();
		String JESSIONID = session.getId();
		System.out.println("session"+JESSIONID);
		
		
		//判断用户是否登陆过
		
		Object o = session.getAttribute("user");
		System.out.print(o);
		
		//获取cookie
		Cookie[] cookies = request.getCookies();
		String username=null;
		String password=null;
		if(cookies!=null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cookie_name = cookie.getName();
				String cookie_value = cookie.getValue();
				if(cookie_name.equals("username")) {
					username = cookie_value;
				}
				if(cookie_name.equals("password")) {
					password = cookie_value;
				}
			}
			
			
		}
		
		if(username!=null&&password!=null) {
			PrintWriter pw = response.getWriter();
			String operation = request.getParameter("operation");
			String issucc1 = AdminServiceImpl.getInstance().adminLogin(username, password).getMsg();
			if(issucc1.equals("登录成功")) {
				String responseText = ServerResponse.ServerResponseBySucess("登录成功").obj2str();
				pw.write(responseText);
				pw.close();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
