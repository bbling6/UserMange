package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class MainFrame extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    String note=null;
	    String lasttime=null;
	    //接收cookie
	    Cookie cookies[]=request.getCookies();
	    
	    	
	    //遍历cookies ,查找是否有上次时间记录
	    for(Cookie cookie:cookies){
	    	String name=cookie.getName();
	    	if("lasttime".equals(name)){
	    		//找到了有lasttime
	    		 lasttime=cookie.getValue();
	    	}
	    }
	    //判断是否是第一次
	    String tempString=(String)request.getAttribute("firsttime");
	    	if(!"firsttime".equals(tempString))
	    	{
	    		//即不是第一次登录
	    		
	    		note="您好，您上次 登录的时间是："+lasttime;
	    	}else {
				note="您好，这是您第一次登录";
			}
	    
	    //通过servletContext来获取访问量
	    String visitorNum=(String) this.getServletContext().getAttribute("visitorNum");
	    
	    //显示用户名
	    //接收session
	    HttpSession session=request.getSession();
	    String username=(String) session.getAttribute("username");
	    	
	    out.print("<h3>欢迎  "+username+" 登录</h3>");
	    out.print("<h3>网站访问量：&nbsp"+visitorNum+"&nbsp</h3>");
	    out.print(note);
	    out.print("<a href='/UserMange/LoginServlet'>返回重新登录</a><hr/>");
	    out.print("<h1>请选择你需要的操作..</h1></br>");
	    out.print("<a href='/UserMange/ManagerUser'>管理用户</a></br>");
	    out.print("<a href='/UserMange/AddUserServlet'>添加用户</a></br>");
	    out.print("<a href='/UserMange/FindUserServlet'>查找用户</a></br>");
	    out.print("<a href='/UserMange/ManagerUser'>退出系统</a></br>");
	    
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	this.doGet(request, response);
	
	}
}
