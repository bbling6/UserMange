package com.dbb.domain;
/**
 * ����һ������ģ�ͣ�ÿ���������һ����¼
 * @author bing
 *
 */
public class User {
	public  int id;
	public  String username;
	public  String email;
	public  int grade;
	public  String passwd;
	
	public  int getId() {
		return id;
	}
	public  void setId(int id) {
		this.id = id;
	}
	public  String getUsername() {
		return username;
	}
	public  void setUsername(String username) {
		this.username = username;
	}
	public  String getEmail() {
		return email;
	}
	public  void setEmail(String email) {
		this.email = email;
	}
	public  int getGrade() {
		return grade;
	}
	public  void setGrade(int grade) {
		this.grade = grade;
	}
	public  String getPasswd() {
		return passwd;
	}
	public  void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
