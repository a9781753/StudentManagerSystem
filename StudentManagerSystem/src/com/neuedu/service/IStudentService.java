package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Student;

public interface IStudentService {

	ServerResponse<Student> studentlogin(int id, String password);




}
