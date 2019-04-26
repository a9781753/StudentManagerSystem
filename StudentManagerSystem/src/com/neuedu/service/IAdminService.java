package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Admin;

public interface IAdminService {

	public ServerResponse<Admin> adminLogin(String username, String password);
	
	public boolean register(String username,String password);
	
}
