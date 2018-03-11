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
 *����һ�������࣬����ִ�и���ʵ�ʲ�����ֱ�Ӳ������ݿ�
 */

public class SqlHelper {

	//��static�ᵼ��ӵ�£��������ɾֲ�����
	
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

	
	//�������ݿ�Ĳ���
	private static String url="";
	private static String driver="";
	private static String user="";
	private static String passwd="";
	
	private static Properties pp=null;
	private static InputStream is=null;
	
	//����������ֻ��Ҫһ��
	static{
		try {
			//�������ļ��ж�ȡ����
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

	
	//ͳһ��select����ѯ���
	//�����汾����������ʹ����Դ������ر���Դ������resultsetת��allaylist�������
	//�����ͱ����ڱ����ȥ�ر�resultset.
	//������д��executeQuery2�������Դ�����executeQuery�����Ա�
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
				int col=rMetaData.getColumnCount();//��ȡ������м�����Ϣ
				//��rs���al
				
				while(rs.next()){
					Object[] objects=new Object[col];//һ���������� ������װһ������
					for(int i=0;i<col;i++){
						//��һ������װ��������
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
			//�ر�
			//SqlHelper.close(rs, ps, ct);
		}
		return rs;
	}
     
     //�ж��sql��䣬�迼������
     
     //һ����ɾ��sql���
     public static void executeUpdata(String sql,String []parameters) {
    	 Connection ct=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
    	 
    	 try {
			ct=DriverManager.getConnection(url,user,passwd);
			ps=ct.prepareStatement(sql);
			//������ֵ
			if(parameters!=null){
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i+1, parameters[i]);
				}
			}
			
			//ִ��
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//�ر���Դ
			
		}
		
	}
     
     //�ر���Դ�ĺ���
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
