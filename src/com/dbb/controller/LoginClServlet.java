package com.dbb.controller;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbb.domain.User;
import com.dbb.service.UserService;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class LoginClServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//验证提交的表单中登录信息是否正确
		String id=request.getParameter("username");
		String password=request.getParameter("password");
		//取出验证码
		HttpSession session=request.getSession();
		String checkcode=(String) session.getAttribute("checkcode");
		String inputcode=request.getParameter("code");
		
		//判断验证码输入是否正确
		if(!inputcode.equals(checkcode))
		{
			String codeerr="验证码输入错误...";
		    request.setAttribute("error", codeerr);
		    request.getRequestDispatcher("/LoginServlet").forward(request, response);
		    return;
		    
		}

		//创建UserService的一个对象
		UserService userService=new UserService();
		//创建User对象
		User user=new User();
		if(id!=null&&!password.equals("")){
		   user.setId(Integer.parseInt(id));
		   user.setPasswd(password);
		}
		
		if(userService.checkUser(user)){
			
			//合法,跳转到主界面.
			//1.保存用户名和密码
			
			//使用cookie来保存用户名和密码
			String keep=request.getParameter("iskeepinfo");
			if("keep".equals(keep)){
				//点击了保存用户名选项
				Cookie cookie=new Cookie("keepid", id);
				Cookie cookie2=new Cookie("keepPasswd", id);
				cookie.setMaxAge(3600*24*7);
				cookie2.setMaxAge(3600*24*7);
				response.addCookie(cookie);
				response.addCookie(cookie2);

			}
			
			//2.上次登录时间
			
			//并使用cookie去更新上次时间记录
		    //做一个cookie
			boolean b=false;
		    //接收cookie
		    Cookie cookies[]=request.getCookies();
            if(cookies!=null){
            	
			    //遍历cookies ,查找是否有上次时间记录
			    for(Cookie cookie:cookies){
			    	String name=cookie.getName();
			    	if("lasttime".equals(name)){
			    		
			    		//找到了有lasttime
			    		String lasttime=cookie.getValue();
			    		
			    		//获取现在时间，更新上次记录
			    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    		lasttime=sdf.format(new java.util.Date());
			    		cookie.setValue(lasttime);
			    		response.addCookie(cookie);
			    		b=true;
			    		
			    	}
			    }
            }
		    if(!b){
		    	
		    	//若没找到，则创建一个cookie
		    	//找到当前时间
		    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String lasttime=sdf.format(new java.util.Date());
		    	Cookie cookie=new Cookie("lasttime", lasttime);
		    	response.addCookie(cookie);
		    	//放进requeset,传给主界面
	    		request.setAttribute("firsttime", "firsttime");
		    }
		    
		    //3.网站访问量设置
		    
		    //id合法,则将访问量加1。首先从文件中取出以前的访问量

			//将访问量加1，

			String visitorNum=(String) this.getServletContext().getAttribute("visitorNum");
			visitorNum=(Integer.parseInt(visitorNum)+1)+"";
			//把访问量放到servletcontext
			this.getServletContext().setAttribute("visitorNum", visitorNum);

		    
		    //4.设置在界面上登录用户名字
			User user2=userService.getUserById(id);
			session.setAttribute("username", user2.getUsername());
		    
			request.getRequestDispatcher("/MainFrame").forward(request, response);
		}else{
			//返回登录
			request.setAttribute("error", "id 有误或密码不正确  ");
			request.getRequestDispatcher("/LoginServlet").forward(request, response);
		}

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
		
	}

}
