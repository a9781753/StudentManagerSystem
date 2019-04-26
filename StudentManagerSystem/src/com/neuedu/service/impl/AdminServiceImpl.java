package com.neuedu.service.impl;



import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.impl.AdminDaoimpl;
import com.neuedu.pojo.Admin;
import com.neuedu.service.IAdminService;



public  class AdminServiceImpl implements IAdminService {
	private static AdminServiceImpl instance;
	public static Admin[] admins = new Admin[50];
	public static Admin admin = new Admin("admin", "admin");
	public static Admin[] getadmins() {
		return admins;
	}
	private AdminServiceImpl() {
	
	admins[0] = admin;
	}
	public static Admin getAdmin() {
		return admin;
	}
	public static AdminServiceImpl getInstance() {
		if(instance==null) {
			return new AdminServiceImpl();
		}
		return instance;
	}
	
	
	public ServerResponse<Admin> adminLogin(String username, String password) {
		// TODO Auto-generated method stub
		Admin user;
		//step1:�жϲ����Ƿ����
		if(username==null||username.equals("")) {
			return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_NULL, "�û�������Ϊ��");
		}
		
		if(password==null||password.equals("")) {
			return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_NULL, "�û�������Ϊ��");
		}
		
		for(int i=0;i<admins.length;i++) {
					
					 user=admins[i];
							if(user==null) {
						continue;
					}
						String _username=user.getUsername();
						String _password=user.getPassword();
					
					
					
					if(_username.equals(username)&&password.equals(_password)) {//��¼�ɹ�
						return ServerResponse.ServerResponseBySucess("��¼�ɹ�",user);
					 
					}
					
				}
					return ServerResponse.ServerResponseByFail(Const.PASSWORD_ERROR, "��¼ʧ�ܣ������û���������");
	
	}
	
	
	//���ѧ��
	public String addstudent(String name,String password,String sex,int age,String _class,int score,String rgetime,String modtime,int id) {
		
			return new com.neuedu.dao.mybatis.AdminDaoimpl().addstudent(name, password, sex, age, _class, score, rgetime, modtime, id).obj2str();
		}

	//�޸�ѧ��
	public String updatastudent(String name,String password,String sex,int age,String _class,int score,String rgetime,String modtime,int id) {
		int a = new com.neuedu.dao.mybatis.AdminDaoimpl().updatastudent(name, password, sex, age, _class, score, rgetime, modtime, id);
		if(a>0) {
			return ServerResponse.ServerResponseBySucess("�޸ĳɹ�").obj2str();
		}
		return null;
	}
	
	
	//��ҳ�鿴ѧ��
		public String findtudent(int pageNo,int pageSize) {
			return new com.neuedu.dao.mybatis.AdminDaoimpl().findstudentBypage(pageNo, pageSize).obj2str();

		}
	
	

	public boolean register(String username, String password) {
			
		return false;
		
	}

}
