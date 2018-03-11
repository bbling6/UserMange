package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    //����û�����
	    out.print("<h1>����û�</h1>");
	    out.print("<a href='/UserMange/MainFrame'>&nbsp&nbspȡ�����</a>");
		out.print("<form action='/UserMange/UserClServlet?type=add' method='post'>");
		out.print("<table padding=2px border=2px width=250px>");
		out.print("<tr><td>username</td><td><input type='text' name='username'/></td></tr>");
		out.print("<tr><td>email</td><td><input type='text' name='email' /></td></tr>");
		out.print("<tr><td>grade</td><td><input type='text' name='grade' /></td></tr>");
		out.print("<tr><td>passwd</td><td><input type='text' name='passwd' /></td></tr>");
		out.print("</table>");
		out.print("<input type='submit'  value='�ύ���'/>");
		out.print("<input type='reset' value='����'/>");
		out.print("</form>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doGet(request, response);
	}
}
