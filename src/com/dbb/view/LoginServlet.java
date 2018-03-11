package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String keepId="";
		String keepPasswd="";
		//查找保存了用户名额的cookie
		Cookie cookies[]=request.getCookies();
        if(cookies!=null){
		    //遍历cookies ,查找是否有
		    for(Cookie cookie:cookies){
		    	String name=cookie.getName();
		    	if("keepid".equals(name)){
		    		keepId=cookie.getValue();
		    	}if("keepPasswd".equals(name)){
		    		keepPasswd=cookie.getValue();
		    	}
		    }
        }
		//提交表单去验证
		out.print("<form action='/UserMange/LoginClServlet' method='post'>");
		out.print("i d  :<input type='text' name='username' value='"+keepId+"'/><br/>");
		out.print("密    码 :<input type='password' name='password' value='"+keepPasswd+"'/><br/>");
		out.print("验证码  ：<input type='text' name='code' /><img src='/UserMange/CreateCode'/><br/>");
		out.print("<input type='checkbox' name='iskeepinfo' value='keep'/>保存该用户名和密码<br/>");
		out.print("<input type='submit' value='login'/>");
		out.print("</form><br/>");
		
		String err=(String)request.getAttribute("error");
		if(err!=null){
			out.print("<font color=red>"+err+"</font>");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
