package com.dbb.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCode extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
	    //禁止浏览器缓存随机图片
	    response.setDateHeader("Expires", -1);
	    response.setHeader("Chche-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    
	    //通知客户机以图片方式打开发送过去的数据
	    response.setHeader("Content-Type", "image/jpeg");
	    
	    BufferedImage image=null;
	   
		    //在内存中创建一副图片
		    image=new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		    
		    //向图片中写数据
		    Graphics g=image.getGraphics();
		    
		    //设置背景色
		    g.setColor(Color.BLACK);
		    g.fillRect(0, 0, 50, 30);
		    
		    //设置画笔颜色和字体
		    g.setColor(Color.RED);
		    g.setFont(new Font(null,Font.BOLD,20));
		    
		    //向图片上写数据
		    String num=makeNum();
		   
		    //将随机生成的数据，保存到session
		    request.getSession().setAttribute("checkcode", num);
		    g.drawString(num, 0, 20);
		    
		    //把写好数据的图片输出给浏览器
		    ImageIO.write(image,"jpg",response.getOutputStream());
		    
	    
	}
	
	public String makeNum() {
		Random random=new Random();
		String num=random.nextInt(9999)+"";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<4-num.length();i++)
		{
			sb.append("0");
		}
		num=sb.toString()+num;
		return num;
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	this.doGet(request, response);
	
	}

}
