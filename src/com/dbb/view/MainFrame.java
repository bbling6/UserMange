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
	    //����cookie
	    Cookie cookies[]=request.getCookies();
	    
	    	
	    //����cookies ,�����Ƿ����ϴ�ʱ���¼
	    for(Cookie cookie:cookies){
	    	String name=cookie.getName();
	    	if("lasttime".equals(name)){
	    		//�ҵ�����lasttime
	    		 lasttime=cookie.getValue();
	    	}
	    }
	    //�ж��Ƿ��ǵ�һ��
	    String tempString=(String)request.getAttribute("firsttime");
	    	if(!"firsttime".equals(tempString))
	    	{
	    		//�����ǵ�һ�ε�¼
	    		
	    		note="���ã����ϴ� ��¼��ʱ���ǣ�"+lasttime;
	    	}else {
				note="���ã���������һ�ε�¼";
			}
	    
	    //ͨ��servletContext����ȡ������
	    String visitorNum=(String) this.getServletContext().getAttribute("visitorNum");
	    
	    //��ʾ�û���
	    //����session
	    HttpSession session=request.getSession();
	    String username=(String) session.getAttribute("username");
	    	
	    out.print("<h3>��ӭ  "+username+" ��¼</h3>");
	    out.print("<h3>��վ��������&nbsp"+visitorNum+"&nbsp</h3>");
	    out.print(note);
	    out.print("<a href='/UserMange/LoginServlet'>�������µ�¼</a><hr/>");
	    out.print("<h1>��ѡ������Ҫ�Ĳ���..</h1></br>");
	    out.print("<a href='/UserMange/ManagerUser'>�����û�</a></br>");
	    out.print("<a href='/UserMange/AddUserServlet'>����û�</a></br>");
	    out.print("<a href='/UserMange/FindUserServlet'>�����û�</a></br>");
	    out.print("<a href='/UserMange/ManagerUser'>�˳�ϵͳ</a></br>");
	    
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	this.doGet(request, response);
	
	}
}
