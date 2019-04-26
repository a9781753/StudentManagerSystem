package com.neuedu.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Admin;


@WebFilter({"/student.do","/course.do"})
public class loginfilter implements Filter {

    public loginfilter() {

    }

	
	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request;
		HttpSession session = _request.getSession();
		Admin admin = (Admin)session.getAttribute("user");
		if(admin==null) {//管理员未登录
			PrintWriter pw = response.getWriter();
			String operation = request.getParameter("operation");
			String responseText = ServerResponse.ServerResponseByFail(Const.NO_LOGIN,"管理员未登录").obj2str();
			pw.write(responseText);
			pw.close();
			return;
		}
		
		
		
		
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
