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
		
		//��֤�ύ�ı��е�¼��Ϣ�Ƿ���ȷ
		String id=request.getParameter("username");
		String password=request.getParameter("password");
		//ȡ����֤��
		HttpSession session=request.getSession();
		String checkcode=(String) session.getAttribute("checkcode");
		String inputcode=request.getParameter("code");
		
		//�ж���֤�������Ƿ���ȷ
		if(!inputcode.equals(checkcode))
		{
			String codeerr="��֤���������...";
		    request.setAttribute("error", codeerr);
		    request.getRequestDispatcher("/LoginServlet").forward(request, response);
		    return;
		    
		}

		//����UserService��һ������
		UserService userService=new UserService();
		//����User����
		User user=new User();
		if(id!=null&&!password.equals("")){
		   user.setId(Integer.parseInt(id));
		   user.setPasswd(password);
		}
		
		if(userService.checkUser(user)){
			
			//�Ϸ�,��ת��������.
			//1.�����û���������
			
			//ʹ��cookie�������û���������
			String keep=request.getParameter("iskeepinfo");
			if("keep".equals(keep)){
				//����˱����û���ѡ��
				Cookie cookie=new Cookie("keepid", id);
				Cookie cookie2=new Cookie("keepPasswd", id);
				cookie.setMaxAge(3600*24*7);
				cookie2.setMaxAge(3600*24*7);
				response.addCookie(cookie);
				response.addCookie(cookie2);

			}
			
			//2.�ϴε�¼ʱ��
			
			//��ʹ��cookieȥ�����ϴ�ʱ���¼
		    //��һ��cookie
			boolean b=false;
		    //����cookie
		    Cookie cookies[]=request.getCookies();
            if(cookies!=null){
            	
			    //����cookies ,�����Ƿ����ϴ�ʱ���¼
			    for(Cookie cookie:cookies){
			    	String name=cookie.getName();
			    	if("lasttime".equals(name)){
			    		
			    		//�ҵ�����lasttime
			    		String lasttime=cookie.getValue();
			    		
			    		//��ȡ����ʱ�䣬�����ϴμ�¼
			    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    		lasttime=sdf.format(new java.util.Date());
			    		cookie.setValue(lasttime);
			    		response.addCookie(cookie);
			    		b=true;
			    		
			    	}
			    }
            }
		    if(!b){
		    	
		    	//��û�ҵ����򴴽�һ��cookie
		    	//�ҵ���ǰʱ��
		    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String lasttime=sdf.format(new java.util.Date());
		    	Cookie cookie=new Cookie("lasttime", lasttime);
		    	response.addCookie(cookie);
		    	//�Ž�requeset,����������
	    		request.setAttribute("firsttime", "firsttime");
		    }
		    
		    //3.��վ����������
		    
		    //id�Ϸ�,�򽫷�������1�����ȴ��ļ���ȡ����ǰ�ķ�����

			//����������1��

			String visitorNum=(String) this.getServletContext().getAttribute("visitorNum");
			visitorNum=(Integer.parseInt(visitorNum)+1)+"";
			//�ѷ������ŵ�servletcontext
			this.getServletContext().setAttribute("visitorNum", visitorNum);

		    
		    //4.�����ڽ����ϵ�¼�û�����
			User user2=userService.getUserById(id);
			session.setAttribute("username", user2.getUsername());
		    
			request.getRequestDispatcher("/MainFrame").forward(request, response);
		}else{
			//���ص�¼
			request.setAttribute("error", "id ��������벻��ȷ  ");
			request.getRequestDispatcher("/LoginServlet").forward(request, response);
		}

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
		
	}

}
