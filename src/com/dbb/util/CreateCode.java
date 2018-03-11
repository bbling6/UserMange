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
	    //��ֹ������������ͼƬ
	    response.setDateHeader("Expires", -1);
	    response.setHeader("Chche-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    
	    //֪ͨ�ͻ�����ͼƬ��ʽ�򿪷��͹�ȥ������
	    response.setHeader("Content-Type", "image/jpeg");
	    
	    BufferedImage image=null;
	   
		    //���ڴ��д���һ��ͼƬ
		    image=new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		    
		    //��ͼƬ��д����
		    Graphics g=image.getGraphics();
		    
		    //���ñ���ɫ
		    g.setColor(Color.BLACK);
		    g.fillRect(0, 0, 50, 30);
		    
		    //���û�����ɫ������
		    g.setColor(Color.RED);
		    g.setFont(new Font(null,Font.BOLD,20));
		    
		    //��ͼƬ��д����
		    String num=makeNum();
		   
		    //��������ɵ����ݣ����浽session
		    request.getSession().setAttribute("checkcode", num);
		    g.drawString(num, 0, 20);
		    
		    //��д�����ݵ�ͼƬ����������
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
