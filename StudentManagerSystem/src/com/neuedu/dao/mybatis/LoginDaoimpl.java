package com.neuedu.dao.mybatis;

import java.io.IOException;
import java.io.Reader;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ILoginDao;
import com.neuedu.pojo.Admin;

public class LoginDaoimpl implements ILoginDao {

	@Override
	public  ServerResponse<Admin> login(String username, String password) {
		String resource = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession();
		Admin admin = new Admin(username,password);
		
		Admin admin1 = session.selectOne("login",admin);
		
		if (admin1!=null) {
			return  ServerResponse.ServerResponseBySucess("登录成功",admin);
		}
		
		return ServerResponse.ServerResponseByFail(Const.USERNAME_NOT_EXISTS,"用户名或密码错误");
	}

}
