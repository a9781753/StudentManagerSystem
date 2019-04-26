package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.lOrR;
import com.neuedu.dao.impl.LoginDao;
import com.neuedu.dao.mybatis.LoginDaoimpl;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Student;
import com.neuedu.service.IAdminService;
import com.neuedu.service.impl.AdminServiceImpl;
import com.sun.corba.se.spi.servicecontext.SendingContextServiceContext;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;



@WebServlet("/admin.do")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	boolean issucc;
    public AdminServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		
		//创建一个全局域
		ServletContext servletContext = request.getServletContext();

		
		
		
		
		
		//创建一个会话
		HttpSession session = request.getSession();
		
		

		
		String operation = request.getParameter("operation");
		if(operation == null) {
			//返回客户端，必须要选择登录还是注册
			//{"status":3,"msg":"必须选择登录还是注册"}}
			String responseText =ServerResponse.ServerResponseByFail(3,"必须选择登录还是注册").obj2str();
			pw.write(responseText);
			pw.close();
			
			
		}
		try {
			int _operation = Integer.parseInt(operation);
			if(_operation == lOrR.ADMIN_LOGIN.getOperation_type()){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				ServerResponse<Admin> serverResponse= new LoginDaoimpl().login(username, password);
				if(serverResponse.getData()!=null) {
					String responseText = serverResponse.ServerResponseBySucess("登录成功",serverResponse.getData() ).obj2str();
					//创建usernamecookie
					Cookie username_cookie = new Cookie("username", username);
					username_cookie.setMaxAge(7*24*3600);
					response.addCookie(username_cookie);
					//创建passwordcookie
					Cookie password_cookie = new Cookie("password", password);
					password_cookie.setMaxAge(7*24*3600);
					response.addCookie(password_cookie);
					session.setAttribute("user", serverResponse.getData());
					pw.write(responseText);
					pw.close();
					return;
				}else { 
					String responseText = serverResponse.ServerResponseByFail(Const.USERNAME_NOT_EXISTS, "用户名或密码错误").obj2str();
					pw.write(responseText);
					pw.close();
					return;
				}
				
			}else if(_operation == lOrR.ADMIN_REGISTER.getOperation_type()) {
				Admin[] admins = AdminServiceImpl.getadmins();
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				Admin admin = new Admin(username,password);
				for(int i=1;i<admins.length;i++) {
					if(admins[i]==null) {
						admins[i] = admin;
						break;
						}
				}
				String responseText = ServerResponse.ServerResponseBySucess("注册成功！").obj2str();
				pw.write(responseText);
				pw.close();
				
			}else if(_operation == lOrR.ADMIN_LAYOUT.getOperation_type()) {
				session.removeAttribute("user");
				
				String responseText = ServerResponse.ServerResponseByFail(Const.NO_LOGIN,"管理员已退出登录").obj2str();
				pw.write(responseText);
				pw.close();
			}else if(_operation == lOrR.GET_TOTAL.getOperation_type()) {
				//获取在线人数
				Integer total = (Integer)servletContext.getAttribute("total");
				String responseText = ServerResponse.ServerResponseBySucess("获取成功", total).obj2str();
				pw.write(responseText);
				pw.close();
			}
						
		} catch (NumberFormatException e) {
			// TODO: handle exception
			//返回客户端，必须输入数字
			//{"status":4,"msg":"operation必须是数字"}
			String responseText = ServerResponse.ServerResponseByFail(Const.NOT_NUM, "请选择登录还是注册").obj2str();
			pw.write(responseText);
			pw.close();
		}
		
	}

}
