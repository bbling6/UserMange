package com.dbb.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * 
 * @author bing
 *这是一个工具类，用来执行各种实际操作，直接操作数据库
 */

public class SqlHelper {

	//用static会导致拥堵，尽量做成局部变量
	
	private  Connection ct=null;
	private  PreparedStatement ps=null;
	private  ResultSet rs=null;
	private static CallableStatement cs=null;
	
	public  Connection getCt() {
		return ct;
	}
	public  PreparedStatement getPs() {
		return ps;
	}
	public  ResultSet getRs() {
		return rs;
	}

	
	//连接数据库的参数
	private static String url="";
	private static String driver="";
	private static String user="";
	private static String passwd="";
	
	private static Properties pp=null;
	private static InputStream is=null;
	
	//加载驱动，只需要一次
	static{
		try {
			//从配置文件中读取属性
			pp=new Properties();
			is=SqlHelper.class.getClassLoader().getResourceAsStream("mysql.properties");
			
			pp.load(is);
			driver=pp.getProperty("driver");
			url=pp.getProperty("url");
			user=pp.getProperty("user");
			passwd=pp.getProperty("passwd");
			Class.forName(driver);
			
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally{
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				is=null;
		     }
	    }

	
	//统一的select，查询语句
	//升级版本，将在哪里使用资源在哪里关闭资源，即将resultset转成allaylist再输出，
	//这样就避免在别的类去关闭resultset.
	//我重新写成executeQuery2函数，以此来和executeQuery函数对比
	public static ArrayList executeQuery2(String sql,String []parameters) {
	
		Connection ct=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  ArrayList arrayList=new ArrayList();
		  try {
				ct=DriverManager.getConnection(url,user,passwd);
				ps=ct.prepareStatement(sql);
				if(parameters!=null&&!parameters.equals("")){
					for (int i = 0; i < parameters.length; i++) {
						ps.setString(i+1, parameters[i]);
					}
				}
				rs=ps.executeQuery();
				ResultSetMetaData rMetaData=rs.getMetaData();
				int col=rMetaData.getColumnCount();//获取到结果有几列信息
				//将rs封成al
				
				while(rs.next()){
					Object[] objects=new Object[col];//一个对象数组 ，用来装一行数据
					for(int i=0;i<col;i++){
						//把一行数据装进数组中
						objects[i]=rs.getObject(i+1);
					}
					arrayList.add(objects);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally{
				close(rs, ps, ct);
			}
			return arrayList;
	}
     public static ResultSet executeQuery(String sql,String []parameters) {
    	 Connection ct=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  
		try {
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			if(parameters!=null&&!parameters.equals("")){
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i+1, parameters[i]);
				}
			}
			rs=ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally{
			//关闭
			//SqlHelper.close(rs, ps, ct);
		}
		return rs;
	}
     
     //有多个sql语句，需考虑事务
     
     //一个增删改sql语句
     public static void executeUpdata(String sql,String []parameters) {
    	 Connection ct=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
    	 
    	 try {
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			//给？赋值
			if(parameters!=null){
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i+1, parameters[i]);
				}
			}
			
			//执行
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//关闭资源
			
		}
		
	}
     
     //关闭资源的函数
     public static void close(ResultSet rs,PreparedStatement ps,Connection ct) {
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			rs=null;
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ps=null;
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ct=null;
		}
	}
}
