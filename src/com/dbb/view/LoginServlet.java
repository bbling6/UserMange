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
		//���ұ������û������cookie
		Cookie cookies[]=request.getCookies();
        if(cookies!=null){
		    //����cookies ,�����Ƿ���
		    for(Cookie cookie:cookies){
		    	String name=cookie.getName();
		    	if("keepid".equals(name)){
		    		keepId=cookie.getValue();
		    	}if("keepPasswd".equals(name)){
		    		keepPasswd=cookie.getValue();
		    	}
		    }
        }
		//�ύ��ȥ��֤
		out.print("<form action='/UserMange/LoginClServlet' method='post'>");
		out.print("i d  :<input type='text' name='username' value='"+keepId+"'/><br/>");
		out.print("��    �� :<input type='password' name='password' value='"+keepPasswd+"'/><br/>");
		out.print("��֤��  ��<input type='text' name='code' /><img src='/UserMange/CreateCode'/><br/>");
		out.print("<input type='checkbox' name='iskeepinfo' value='keep'/>������û���������<br/>");
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
