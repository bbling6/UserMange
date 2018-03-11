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
 *����һ�������࣬����ʵʩ�����ݵ�ҵ�����
 */

public class UserService {
	
	//����û�
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

	//�޸��û���Ϣ
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
	
	//ͨ��id�����û�
	
	//ͨ��id��ȡ�û�����Ϣ
	public User getUserById(String id) {
		
		User user=new User();
		String sql="Select * from users where id=?";
		String Parameter[]={id};

		SqlHelper sqlHelper=new SqlHelper();
		try {
			//�����������ѯ���
			ArrayList aList=sqlHelper.executeQuery2(sql, Parameter);
			
			//�жϸ��û��Ƿ����
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
	//ɾ���û�
	public Boolean delUser(String id){
		boolean b=true;
		//����sqlhelperȥ�������ݿ�ʵ��
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
	
	//�����ȡ�ܹ�ҳ��
	public int getpageCount(int pageSize){
		int pageCount=0;
		int rowCount=0;
		//��ȡ�ܹ���¼������
		String count="select count(*) from users";
		
		SqlHelper sqlHelper=new SqlHelper();
		
		//�����������ѯ���
		ArrayList aList=sqlHelper.executeQuery2(count, null);
		try {
			Object []objects=(Object[]) aList.get(0);//��ȡ�ܹ�����
			rowCount=Integer.parseInt(objects[0].toString());
			//ȡ���ܹ�ҳ��
			pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return pageCount;
	}
	
	
	//��ҳ���ܣ���ȡ��ҳ���(��ǰҳ����ÿҳ��ʾ������)
	public ArrayList<User> getUserByPage(int pageNow,int pageSize) {
		
		ArrayList<User> arrayList=new ArrayList<User>();
		//��ҳ���
		String sql="select * from users order by id limit "
			+(pageSize*(pageNow-1))+","+pageSize+";";
		SqlHelper sqlHelper=new SqlHelper();
		
		//��������Ĳ�ѯ��䣬����һ��arraylist���ϣ������Ƕ�������
		//Ȼ�󽫶������顪����User�����ٷ�װ��arraylist��
		
		ArrayList aList=sqlHelper.executeQuery2(sql, null);
		try {
			//ȡ����������
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
	
	//��֤�û���¼
	public boolean checkUser(User user){
		boolean b=false;
		
		//ʹ��sqlhelper������
		String sql="select * from users where id=? and passwd=?";
		String parameters[]={user.getId()+"",user.getPasswd()};
		
		SqlHelper sqlHelper=new SqlHelper();
		
		ArrayList arrayList=sqlHelper.executeQuery2(sql, parameters);
	    
		//�ж��û�
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
