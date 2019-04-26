package com.neuedu.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.service.impl.StudentServiceimpl;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	public int id;
	public String  name;
	public String sex;
	public int age;
	public String _class;
	public int score;
	public String rgetime;
	public String modtime;
	public String password;
	
	
	//重写构造方法
	public Student(int id,String name,String password,String sex,int age,String _class,int score, String rgetime, String modtime) {
			this.id = id;
			this.name = name;
			this.password = password;
			this.sex = sex;
			this.age = age;
			this._class = _class;
			this.score = score;
			this.rgetime = rgetime;
			this.modtime = modtime;
	}
	public Student() {}
	



	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String get_class() {
		return _class;
	}


	public void set_class(String _class) {
		this._class = _class;
	}



	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getRgetime() {
		return rgetime;
	}


	public void setRgetime(String rgetime) {
		this.rgetime = rgetime;
	}


	public String getModtime() {
		return modtime;
	}


	public void setModtime(String modtime) {
		this.modtime = modtime;
	}


			public  int compareTo(Student o) {
				if(o == null) {
					return 1;
				}
				return this.score - o.getScore();
			}
			public  int compareTo1(Student o) {
				if(o == null) {
					return 1;
				}
				return o.getScore()-this.score;
			}
	

}
