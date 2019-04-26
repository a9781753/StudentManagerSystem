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
import com.neuedu.pojo.Student;

/**
 * Servlet Filter implementation class StudentLoginFilter
 */
@WebFilter("/choice.do")
public class StudentLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StudentLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request;
		HttpSession session1 = _request.getSession();
		Student student = (Student)session1.getAttribute("user1");
		if(student==null) {//学生未登录
			PrintWriter pw = response.getWriter();
			String operation = request.getParameter("operation");
			String responseText = ServerResponse.ServerResponseByFail(Const.NO_LOGIN,"学生未登录").obj2str();
			pw.write(responseText);
			pw.close();
			return;
		}
			
			
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
