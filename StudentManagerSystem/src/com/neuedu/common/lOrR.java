package com.neuedu.common;

public enum lOrR {
	ADMIN_LOGIN(1,"管理员登录"),
	ADMIN_REGISTER(2,"管理员注册"),
	ADMIN_LAYOUT(3,"管理员退出登录"),
	ADD(4,"添加学生"),
	FINDALL(5,"查看所有学生信息"),
	FINDBYID(6,"根据ID查看学生信息"),
	UPDATA(7,"修改学生信息"),
	DELETE(8,"删除学生"),
	GET_TOTAL(9,"获取在线人数"),
	ADMIN_ADD_COURSE(10,"管理员添加课程"),
	ADMIN_FIND_COURSE(11,"管理员查看课程"),
	ADMIN_UPDATA_COURSE(12,"管理员修改课程"),
	ADMIN_DELETE_COURSE(13,"管理员删除课程"),
	STUDENT_REGISTER(14,"学生注册"),
	STUDENT_LOGIN(15,"学生登录"),
	STUDENT_CHOICE_COURSE(16,"学生选课"),
	STUDENT_FIND_COURSE(18,"学生查课"),
	STUDENT_DEL_COURSE(19,"学生取消选课"),
	ADMIN_FINDCOURSEBYPAGE(20,"管理员分页查课"),
	ADMIN_FINDCOURSEBYPAGE1(21,"管理员分页查课按钮版")
	;
	private int operation_type;//类型
	private String  operation_desc;//描述
	lOrR(int operation_type,String operation_desc){
		this.operation_type = operation_type;
		this.operation_desc = operation_desc;
	}
	public int getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(int operation_type) {
		this.operation_type = operation_type;
	}
	public String getOperation_desc() {
		return operation_desc;
	}
	public void setOperation_desc(String operation_desc) {
		this.operation_desc = operation_desc;
	}
	
	
}
