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
	    		" return window.confirm('��ȷ��ɾ�����û���?');}");
	    out.print("</script>");
	    out.print("<h2>�������</h2>");
	    out.print("<a href='/UserMange/LOginServlet'>  ��ȫ�˳�     </a>");
	    out.print("<a href='/UserMange/MainFrame'>   ���ص�������</a><hr/>");
	    
	    
	    
	    int pageNow=1;    //�����ǵڼ�ҳ
	    int pageSize=3;   //ÿҳ��ʾ����
	    int pageCount;  // ���м�ҳ
	    int rowCount;   //���ж�����
	    //���մ�������pageNow
	    String page=request.getParameter("pageNow");
	    if(page!=null){
	    	 pageNow=Integer.parseInt(page);
	    }
	   

		    //����һ������

			UserService userService=new UserService();
			ArrayList<User> arrayList=new ArrayList<User>();
			//����service�ķ��������ط�ҳ������
			arrayList=userService.getUserByPage(pageNow, pageSize);
			//�����ܹ���ҳ��
			pageCount=userService.getpageCount(pageSize);
			
		
		    //���ؽ��
			out.print("<table border=1 width=500px>");
			out.print("<tr><th>id</th><th>username</th><th>email</th><th>garde</th>" +
					"<th>ɾ���û�</th><th>�޸��û�</th></tr>");
			for( User u:arrayList){
				//����Ϣ���ɱ����ʽ
				out.print("<tr><td>"+u.getId()+
						"</td><td>"+u.getUsername()+
						"</td><td>"+u.getEmail()+
						"</td><td>"+u.getGrade()+
						"</td><td><a onClick='return delUser()' href='/UserMange/UserClServlet?type=del&id="+u.getId()+"'" +
								">ɾ���û�</a></td><td><a href='/UserMange/UserClServlet?type=goUpda&id="+u.getId()+"'>�޸��û�</a></td></tr>");
			}
			out.print("</table>");
			//��һҳ
			if(pageNow>1){
				out.print("<a href='/UserMange/ManagerUser?pageNow="+(pageNow-1)+"'>��һҳ&nbsp</a>");
			}
			
			//��ʾ�����ҳ����ʾ
			for(int i=1;i<=pageCount;i++)
			{
				//����ǵ�ǰҳ
				if(i==pageNow)
				{
					out.print("<a color=yellow href='/UserMange/ManagerUser?pageNow="+i+"'> <"+i+" ></a>");
				}else{
					out.print("<a href='/UserMange/ManagerUser?pageNow="+i+"'> <"+i+" ></a>");
				}
			}
			
			//��һҳ
			if(pageNow<pageCount){
				out.print("<a href='/UserMange/ManagerUser?pageNow="+(pageNow+1)+"'>&nbsp��һҳ</a>");
			}
			out.print("&nbsp;&nbsp;��ǰ��"+pageNow+"ҳ/��ҳ��"+pageCount+"</br>");
			out.print("&nbsp;��ת����<input type='text' id='pageNow' name='pageNow'/> " +
					"<input type='button' onclick='changepage()' value='��'/>");
			

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		this.doGet(request, response);

	}
}
