package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbb.domain.User;

public class UpdaUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//将上一页面的传值取出来
		User user=new User();
		user=(User)request.getAttribute("user");
		out.print("<script type='text/javascript' language='javascript'>");
		out.print("function getUpdate(){" +
				"return window.confirm('你确定修改该用户吗？');}");
		out.print("</script>");
		
		//修改用户的表格
		out.print("<h1>修改用户</h1></br><a href='/UserMange/ManagerUser'>返回管理用户</a>");
		out.print("<form action='/UserMange/UserClServlet?type=update' method='post'>");
		out.print("<table padding=2px border=2px width=250px>");
		out.print("<tr><td>id</td><td><input type='text' readonly='readonly' name='id' value='"+user.getId()+"'/></td></tr>");
		out.print("<tr><td>username</td><td><input type='text' name='username' value='"+user.getUsername()+"'/></td></tr>");
		out.print("<tr><td>email</td><td><input type='text' name='email' value='"+user.getEmail()+"'/></td></tr>");
		out.print("<tr><td>grade</td><td><input type='text' name='grade' value='"+user.getGrade()+"'/></td></tr>");
		out.print("<tr><td>passwd</td><td><input type='text' name='passwd' value='"+user.getPasswd()+"'/></td></tr>");
		out.print("</table>");
		out.print("<input type='submit' onClick='return getUpdate()' value='提交修改'/>");
		out.print("<input type='reset' value='重置'/>");
		out.print("</form>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);
	}
}
