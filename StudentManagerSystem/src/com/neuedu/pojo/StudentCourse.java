package com.neuedu.pojo;

import java.io.Serializable;

public class StudentCourse implements Serializable{

	private int id;
	private int cid;
	private int sid;
	private String cname;

	public StudentCourse(int id, int cid, int sid,String cname) {
		super();
		this.id = id;
		this.cid = cid;
		this.sid = sid;
		this.cname = cname;
	}
	public StudentCourse(int sid, int cid) {
		super();
		this.cid = cid;
		this.sid = sid;
	}
	public StudentCourse() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	
}
