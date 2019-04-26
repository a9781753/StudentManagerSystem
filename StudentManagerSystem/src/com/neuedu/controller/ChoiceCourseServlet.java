package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.neuedu.dao.impl.AdminDaoimpl;
import com.neuedu.dao.impl.StudentChoiceCourseDao;
import com.neuedu.pojo.Admin;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.Student;
import com.neuedu.service.IAdminService;
import com.neuedu.service.IStudentService;
import com.neuedu.service.StudentChoiseCourseService;
import com.neuedu.service.impl.AdminServiceImpl;
import com.neuedu.service.impl.CourseServiceimpl;
import com.neuedu.service.impl.StudentChoiseCourseServiceimpl;
import com.neuedu.service.impl.StudentServiceimpl;

/**
 * 学生选课
 */
@WebServlet("/choice.do")
public class ChoiceCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    boolean issucc;
    
    public ChoiceCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    	
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		
		//创建一个会话
		HttpSession session = request.getSession();
		List<Course> courses =  (List<Course>)session.getAttribute("cart");
  		Student student = (Student)session.getAttribute("student");
  		
  		
  		
  		
  		
  		
  		String operation = request.getParameter("operation");
  		int _operation = Integer.parseInt(operation);
		if(operation == null) {
			//返回客户端，必须要选择登录还是注册
			//{"status":3,"msg":"必须选择登录还是注册"}}
			String responseText =ServerResponse.ServerResponseByFail(3,"必须选择登录还是注册").obj2str();
			pw.write(responseText);
			pw.close();
			
			
		}
		try {
			String _cid= request.getParameter("cid");
	  		Integer cid = Integer.parseInt(_cid);
	  		String _sid= request.getParameter("sid");
	  		Integer sid = Integer.parseInt(_sid);
			
			if(_operation == lOrR.STUDENT_CHOICE_COURSE.getOperation_type()) {
					
					String responseText = StudentChoiseCourseServiceimpl.getstudentChoiseCourseServiceimpl().studentChoiceCourse(sid, cid).obj2str();
					pw.write(responseText);
					pw.close();
			
			
			}else if(_operation == lOrR.STUDENT_FIND_COURSE.getOperation_type()){
				
				String responseText =StudentChoiseCourseServiceimpl.getstudentChoiseCourseServiceimpl().studentFindCourse(sid).obj2str() ;
				pw.write(responseText);
				pw.close();
	
				
			}else if(_operation == lOrR.STUDENT_DEL_COURSE.getOperation_type()) {
				
						
						String responseText =StudentChoiseCourseServiceimpl.getstudentChoiseCourseServiceimpl().stuedntDeleteCourse(sid, cid).obj2str();
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
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
