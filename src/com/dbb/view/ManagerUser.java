package com.dbb.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbb.domain.User;
import com.dbb.service.UserService;

public class ManagerUser extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    out.print("<script type='text/javascript' language='javascript'>");
	    out.print("function changepage(){" +
	    		"var pageNow=document.getElementById('pageNow');" +
	    		"window.open('/UserMange/ManagerUser?pageNow='+pageNow.value,'_self');}" +
	    		"function delUser(){" +
	    		" return window.confirm('你确定删除该用户吗?');}");
	    out.print("</script>");
	    out.print("<h2>管理界面</h2>");
	    out.print("<a href='/UserMange/LOginServlet'>  安全退出     </a>");
	    out.print("<a href='/UserMange/MainFrame'>   返回到主界面</a><hr/>");
	    
	    
	    
	    int pageNow=1;    //现在是第几页
	    int pageSize=3;   //每页显示几条
	    int pageCount;  // 共有几页
	    int rowCount;   //共有多少条
	    //接收传过来的pageNow
	    String page=request.getParameter("pageNow");
	    if(page!=null){
	    	 pageNow=Integer.parseInt(page);
	    }
	   

		    //创建一个对象

			UserService userService=new UserService();
			ArrayList<User> arrayList=new ArrayList<User>();
			//调用service的方法，返回分页的内容
			arrayList=userService.getUserByPage(pageNow, pageSize);
			//返回总共的页数
			pageCount=userService.getpageCount(pageSize);
			
		
		    //返回结果
			out.print("<table border=1 width=500px>");
			out.print("<tr><th>id</th><th>username</th><th>email</th><th>garde</th>" +
					"<th>删除用户</th><th>修改用户</th></tr>");
			for( User u:arrayList){
				//将信息做成表格形式
				out.print("<tr><td>"+u.getId()+
						"</td><td>"+u.getUsername()+
						"</td><td>"+u.getEmail()+
						"</td><td>"+u.getGrade()+
						"</td><td><a onClick='return delUser()' href='/UserMange/UserClServlet?type=del&id="+u.getId()+"'" +
								">删除用户</a></td><td><a href='/UserMange/UserClServlet?type=goUpda&id="+u.getId()+"'>修改用户</a></td></tr>");
			}
			out.print("</table>");
			//上一页
			if(pageNow>1){
				out.print("<a href='/UserMange/ManagerUser?pageNow="+(pageNow-1)+"'>上一页&nbsp</a>");
			}
			
			//显示下面的页数显示
			for(int i=1;i<=pageCount;i++)
			{
				//如果是当前页
				if(i==pageNow)
				{
					out.print("<a color=yellow href='/UserMange/ManagerUser?pageNow="+i+"'> <"+i+" ></a>");
				}else{
					out.print("<a href='/UserMange/ManagerUser?pageNow="+i+"'> <"+i+" ></a>");
				}
			}
			
			//下一页
			if(pageNow<pageCount){
				out.print("<a href='/UserMange/ManagerUser?pageNow="+(pageNow+1)+"'>&nbsp下一页</a>");
			}
			out.print("&nbsp;&nbsp;当前第"+pageNow+"页/总页数"+pageCount+"</br>");
			out.print("&nbsp;跳转到：<input type='text' id='pageNow' name='pageNow'/> " +
					"<input type='button' onclick='changepage()' value='跳'/>");
			

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);

	}
}
