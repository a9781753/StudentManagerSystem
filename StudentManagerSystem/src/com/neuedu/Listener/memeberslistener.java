package com.neuedu.Listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class memeberslistener
 *
 */
@WebListener
public class memeberslistener implements HttpSessionAttributeListener {

   
    public memeberslistener() {

    }

    //添加属性时调用
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	ServletContext servletContext = event.getSession().getServletContext();
	    if(event.getName().equals("user")) {
	    	//有用户登录
	    	//统计访问该servlet的用户
			Integer total = (Integer)servletContext.getAttribute("total");
			if(total==null) {
				total=1;
				servletContext.setAttribute("total",1);
			}else {
				servletContext.setAttribute("total", ++total);
			}
			
	    }
    }

    //移除属性时调用
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	ServletContext servletContext = event.getSession().getServletContext();
    	Integer num = (Integer)servletContext.getAttribute("total");
		if(num==null) {
			num=0;
		}else {
			num--;
		}
		servletContext.setAttribute("total", num);
    }

   
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
        
    }
	
}
