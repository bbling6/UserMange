package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OkServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String type=request.getParameter("type");
		if("update".equals(type)){
			out.print("恭喜你，修改成功....</br>");
			out.print("<a href='/UserMange/ManagerUser'>返回用户管理界面</a>");
		}else if("del".equals(type)){
			out.print("恭喜你，删除成功....</br>");
			out.print("<a href='/UserMange/ManagerUser'>返回用户管理界面</a>");
		}else if("add".equals(type)){
			out.print("恭喜你，添加成功....</br>");
			out.print("<a href='/UserMange/ManagerUser'>返回用户管理界面</a>");
		}
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);
	}

}
