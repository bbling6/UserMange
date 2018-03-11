package com.dbb.init;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;


public class InitServlet extends HttpServlet implements Servlet{
	

	public void destroy() {
		// TODO Auto-generated method stub
		/*
		FileWriter fileWriter=null;
	    BufferedWriter bufferedWriter=null;
	    
	    try {
			//获取文件全路径
			String filepath=this.getServletContext().getRealPath("recoder.txt");
			
			
			//写回到文件中
			fileWriter=new FileWriter(filepath);
			bufferedWriter=new BufferedWriter(fileWriter);
			
			//获取访问量
			String numsString=(String) this.getServletContext().getAttribute("visitorNum");
			bufferedWriter.write(numsString);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}*/
	    
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		FileReader fileReader=null;
	    BufferedReader bufferedReader=null;
	    
	    InputStream inputStream=null;
	    Properties properties=null;
	    
	    try {
			//获取文件全路径
			//System.out.print(this.getServletContext().getRealPath("recoder.ini"));
			inputStream=Servlet.class.getClassLoader().getResourceAsStream("recoder.properties");
			properties=new Properties();
			properties.load(inputStream);
			//读取属性
			String visitorNum=properties.getProperty("visitornum");
			System.out.print(visitorNum);
			
			
//			fileReader=new FileReader(filepath);
//			
//			//转为bufferedread
//			bufferedReader=new BufferedReader(fileReader);
//			
//			//读取访问量
//			String numsString=bufferedReader.readLine();
			//把访问量放到servletcontext
			this.getServletContext().setAttribute("visitorNum", visitorNum);
			
	    }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭
			
//			try {
//				
//				inputStream.close();
//				bufferedReader.close();
//				fileReader.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
	}

	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
