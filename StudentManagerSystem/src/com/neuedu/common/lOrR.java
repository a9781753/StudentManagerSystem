package com.neuedu.common;

public enum lOrR {
	ADMIN_LOGIN(1,"����Ա��¼"),
	ADMIN_REGISTER(2,"����Աע��"),
	ADMIN_LAYOUT(3,"����Ա�˳���¼"),
	ADD(4,"���ѧ��"),
	FINDALL(5,"�鿴����ѧ����Ϣ"),
	FINDBYID(6,"����ID�鿴ѧ����Ϣ"),
	UPDATA(7,"�޸�ѧ����Ϣ"),
	DELETE(8,"ɾ��ѧ��"),
	GET_TOTAL(9,"��ȡ��������"),
	ADMIN_ADD_COURSE(10,"����Ա��ӿγ�"),
	ADMIN_FIND_COURSE(11,"����Ա�鿴�γ�"),
	ADMIN_UPDATA_COURSE(12,"����Ա�޸Ŀγ�"),
	ADMIN_DELETE_COURSE(13,"����Աɾ���γ�"),
	STUDENT_REGISTER(14,"ѧ��ע��"),
	STUDENT_LOGIN(15,"ѧ����¼"),
	STUDENT_CHOICE_COURSE(16,"ѧ��ѡ��"),
	STUDENT_FIND_COURSE(18,"ѧ�����"),
	STUDENT_DEL_COURSE(19,"ѧ��ȡ��ѡ��"),
	ADMIN_FINDCOURSEBYPAGE(20,"����Ա��ҳ���"),
	ADMIN_FINDCOURSEBYPAGE1(21,"����Ա��ҳ��ΰ�ť��")
	;
	private int operation_type;//����
	private String  operation_desc;//����
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
