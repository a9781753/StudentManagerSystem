package com.neuedu.dao;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Admin;

public interface ILoginDao {
	public   ServerResponse<Admin> login(String username,String password);
}
