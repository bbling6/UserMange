package com.dbb.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dbb.domain.User;
import com.dbb.util.SqlHelper;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

/**
 * 
 * @author bing
 *这是一个工具类，用来实施对数据的业务操作
 */

public class UserService {
	
	//添加用户
	public boolean addUser(User user) {
		boolean b=true;
		
		String sql="insert into users value(?,?,?,?,?)";
		
		String parameters[]={null,user.getUsername(),user.getEmail(),user.getGrade()+"",user.getPasswd()};
		
		SqlHelper sqlHelper=new SqlHelper();
		
		try {
			sqlHelper.executeUpdata(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		return b;
		
	}

	//修改用户信息
	public boolean updateUser(User user) {
		boolean b=true;
		String sql="update users set username=?,email=?,grade=?,passwd=? where id="+user.getId()+";";
		String parameters[]={user.getUsername(),user.getEmail(),user.getGrade()+"",user.getPasswd()};
		
		SqlHelper sqlHelper=new SqlHelper();
		
		try {
			sqlHelper.executeUpdata(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}
		return b;
		
	}
	
	//通过id查找用户
	
	//通过id获取用户的信息
	public User getUserById(String id) {
		
		User user=new User();
		String sql="Select * from users where id=?";
		String Parameter[]={id};

		SqlHelper sqlHelper=new SqlHelper();
		try {
			//改用升级版查询语句
			ArrayList aList=sqlHelper.executeQuery2(sql, Parameter);
			
			//判断该用户是否存在
			if(aList.size()>0){
				Object []objects=(Object[]) aList.get(0);
				
				user.setId(Integer.parseInt(objects[0].toString()));
				user.setUsername(objects[1].toString());
				user.setEmail(objects[2].toString());
				user.setGrade(Integer.parseInt(objects[3].toString()));
				user.setPasswd(objects[4].toString());
			}else {
				user=null;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
		
	}
	//删除用户
	public Boolean delUser(String id){
		boolean b=true;
		//利用sqlhelper去操作数据库实现
		String sql="delete from users where id=?";
		String Parameter[]={id};
		
		SqlHelper sqlHelper=new SqlHelper();
		try {
			sqlHelper.executeUpdata(sql, Parameter);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}	
		
		return b;
	}
	
	//计算获取总共页数
	public int getpageCount(int pageSize){
		int pageCount=0;
		int rowCount=0;
		//获取总共记录的条数
		String count="select count(*) from users";
		
		SqlHelper sqlHelper=new SqlHelper();
		
		//改用升级版查询语句
		ArrayList aList=sqlHelper.executeQuery2(count, null);
		try {
			Object []objects=(Object[]) aList.get(0);//获取总共条数
			rowCount=Integer.parseInt(objects[0].toString());
			//取得总共页数
			pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return pageCount;
	}
	
	
	//分页功能，获取分页结果(当前页数和每页显示的条数)
	public ArrayList<User> getUserByPage(int pageNow,int pageSize) {
		
		ArrayList<User> arrayList=new ArrayList<User>();
		//分页语句
		String sql="select * from users order by id limit "
			+(pageSize*(pageNow-1))+","+pageSize+";";
		SqlHelper sqlHelper=new SqlHelper();
		
		//用升级版的查询语句，接收一个arraylist集合，里面是对象数组
		//然后将对象数组——》User对象，再封装到arraylist中
		
		ArrayList aList=sqlHelper.executeQuery2(sql, null);
		try {
			//取出对象数组
			for(int i=0;i<aList.size();i++){
				Object []objects=(Object[]) aList.get(i);
				User user=new User();
				user.setId(Integer.parseInt(objects[0].toString()));
				user.setUsername(objects[1].toString());
				user.setEmail(objects[2].toString());
				user.setGrade(Integer.parseInt(objects[3].toString()));
				arrayList.add(user);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
		
	}
	
	//验证用户登录
	public boolean checkUser(User user){
		boolean b=false;
		
		//使用sqlhelper来操作
		String sql="select * from users where id=? and passwd=?";
		String parameters[]={user.getId()+"",user.getPasswd()};
		
		SqlHelper sqlHelper=new SqlHelper();
		
		ArrayList arrayList=sqlHelper.executeQuery2(sql, parameters);
	    
		//判断用户
		try {
			
			if(arrayList.size()==1){
				b=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
}
