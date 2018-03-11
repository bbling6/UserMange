package com.dbb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbb.domain.User;
import com.dbb.service.UserService;
import com.dbb.util.SqlHelper;

public class UserClServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		//接收请求type
		String type=request.getParameter("type");
		
		//创建UserService对象
		UserService userService=new UserService();
		
		//判断用户的请求是什么
		//如果请求是删除
		if("del".equals(type))
		{
			//接收传过来的要删除的id
			String id=request.getParameter("id");
			
			if(userService.delUser(id)){
				//进到ok界面
				request.getRequestDispatcher("/OkServlet?type=del").forward(request, response);
			}else{
				//
				request.getRequestDispatcher("/ErrServlet").forward(request, response);
			}
			
		}
		else if("goUpda".equals(type)){
			//去往修改用户的界面
			//接收传过来的要修改的id
			String id=request.getParameter("id");
			User user=new User();
			//调用service中的方法就，间接去操作数据库
			user=userService.getUserById(id);
			
			//跳转到修改界面，并将要修改 的用户信息用属性值传过去
			request.setAttribute("user", user);
			request.getRequestDispatcher("/UpdaUserServlet").forward(request, response);
			
		}
		else if("update".equals(type)){
			//根据从修改界面返回的信息去数据库中修改用户
			//先获取信息,并将之存在user中
			String id=request.getParameter("id");
			
			User user=new User();
			user.setId(Integer.parseInt(id));
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setGrade(Integer.parseInt(request.getParameter("grade")));
			user.setPasswd(request.getParameter("passwd"));
			
			//调用service的方法去操作数据库
			if(userService.updateUser(user))
			{
				//修改成功,跳转到成功页面
				request.getRequestDispatcher("/OkServlet?type=update").forward(request, response);
				
			}else{
				request.getRequestDispatcher("/ErrServlet").forward(request, response);
			}
		}else if("add".equals(type)){
			//添加用户
			//取出新增信息
			User user=new User();
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setGrade(Integer.parseInt(request.getParameter("grade")));
			user.setPasswd(request.getParameter("passwd"));
			
			//调用service的方法去添加用户
			if(userService.addUser(user))
			{
				//添加成功,跳转到成功页面
				request.getRequestDispatcher("/OkServlet?type=add").forward(request, response);
				
			}else{
				request.getRequestDispatcher("/ErrServlet").forward(request, response);
			}
		}else if("find".equals(type)){
			//查找用户
			String idString=request.getParameter("findId");
			User user=userService.getUserById(idString);
			//通过数据库查询

				request.setAttribute("user", user);
				request.getRequestDispatcher("/FindUserServlet").forward(request, response);
			
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);
	}

}
