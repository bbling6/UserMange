package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbb.domain.User;

import sun.net.www.content.text.plain;

public class FindUserServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//���մ����Ĳ�ѯ��Ϣ
		User user=(User) request.getAttribute("user");
		String id=null;
		String usernameString=null;
		String emailString=null;
		String gradeString=null;
		if(user!=null){
			id=user.getId()+"";
			usernameString=user.getUsername();
			emailString=user.getEmail();
			gradeString=user.getGrade()+"";
		}
		out.print("<h2>�����û�</h2></br>");
		out.print("<a href='/UserMange/ManagerUser'>���ع����û�</a>");
		out.print("<form action='/UserMange/UserClServlet?type=find' method='post'>");
		out.print("�������û�id:<input type='text' name='findId'></input>");
		out.print("<input type='submit' value='��ѯ'></input>");
		out.print("</form>");
		
		//��ʾ��ѯ���
		out.print("</br><table border=1 width=500px >");
		out.print("<tr><th>id</th><th>username</th><th>email</th><th>garde</th></tr>");
		out.print("<tr><td>"+id+"</td><td>"+usernameString+"</td><td>"
				+emailString+"</td><td>"+gradeString+"</td></tr>");
		out.print("</table>");
		
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);
	}

}
