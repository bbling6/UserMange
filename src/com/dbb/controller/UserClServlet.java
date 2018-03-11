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
		
		
		//��������type
		String type=request.getParameter("type");
		
		//����UserService����
		UserService userService=new UserService();
		
		//�ж��û���������ʲô
		//���������ɾ��
		if("del".equals(type))
		{
			//���մ�������Ҫɾ����id
			String id=request.getParameter("id");
			
			if(userService.delUser(id)){
				//����ok����
				request.getRequestDispatcher("/OkServlet?type=del").forward(request, response);
			}else{
				//
				request.getRequestDispatcher("/ErrServlet").forward(request, response);
			}
			
		}
		else if("goUpda".equals(type)){
			//ȥ���޸��û��Ľ���
			//���մ�������Ҫ�޸ĵ�id
			String id=request.getParameter("id");
			User user=new User();
			//����service�еķ����ͣ����ȥ�������ݿ�
			user=userService.getUserById(id);
			
			//��ת���޸Ľ��棬����Ҫ�޸� ���û���Ϣ������ֵ����ȥ
			request.setAttribute("user", user);
			request.getRequestDispatcher("/UpdaUserServlet").forward(request, response);
			
		}
		else if("update".equals(type)){
			//���ݴ��޸Ľ��淵�ص���Ϣȥ���ݿ����޸��û�
			//�Ȼ�ȡ��Ϣ,����֮����user��
			String id=request.getParameter("id");
			
			User user=new User();
			user.setId(Integer.parseInt(id));
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setGrade(Integer.parseInt(request.getParameter("grade")));
			user.setPasswd(request.getParameter("passwd"));
			
			//����service�ķ���ȥ�������ݿ�
			if(userService.updateUser(user))
			{
				//�޸ĳɹ�,��ת���ɹ�ҳ��
				request.getRequestDispatcher("/OkServlet?type=update").forward(request, response);
				
			}else{
				request.getRequestDispatcher("/ErrServlet").forward(request, response);
			}
		}else if("add".equals(type)){
			//����û�
			//ȡ��������Ϣ
			User user=new User();
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setGrade(Integer.parseInt(request.getParameter("grade")));
			user.setPasswd(request.getParameter("passwd"));
			
			//����service�ķ���ȥ����û�
			if(userService.addUser(user))
			{
				//��ӳɹ�,��ת���ɹ�ҳ��
				request.getRequestDispatcher("/OkServlet?type=add").forward(request, response);
				
			}else{
				request.getRequestDispatcher("/ErrServlet").forward(request, response);
			}
		}else if("find".equals(type)){
			//�����û�
			String idString=request.getParameter("findId");
			User user=userService.getUserById(idString);
			//ͨ�����ݿ��ѯ

				request.setAttribute("user", user);
				request.getRequestDispatcher("/FindUserServlet").forward(request, response);
			
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);
	}

}
