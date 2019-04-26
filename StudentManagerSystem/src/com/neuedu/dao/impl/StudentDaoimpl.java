package com.neuedu.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.pageModel;
import com.neuedu.dao.IStudentsDao;
import com.neuedu.pojo.Student;
import com.neuedu.service.impl.StudentServiceimpl;

public class StudentDaoimpl implements IStudentsDao {
	
	
	
	public static void main(String[] s) {
		
		new StudentDaoimpl().addcourse("stu","sut", "man", 1, "zhongji", 99);
		
		
	}
	
	//添加学生
		public String addcourse(String name,String password,String sex,int age,String _class,int score) {
			String sql = "insert into students(name,sex,age,_class,score,regtime,modtime,password) values('"+name+"','"+sex+"',"+age+",'"+_class+"',"+score+",now(),now(),'"+password+"')";
			Connection connection = null;
			Statement statement = null;
			try {
				connection = DBUtils.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					if (statement!=null) {
						statement.close();
					}
					if (connection!=null) {
						connection.close();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return ServerResponse.ServerResponseBySucess("添加成功").obj2str();
			
		}
		
		//分页查看学生
		public pageModel<Student> findstudentBypage(int pageNo,int pageSize) {
			
			String sql = "select count(id) from students";
			System.out.println(sql);
			Connection connection = null;
			Statement statement = null;
			Statement statement1 = null;
			List<Student>  students = new ArrayList<Student>();
			pageModel<Student> pagemodel = new pageModel<Student>();
			int id = 0;
			try {
				connection = DBUtils.getConnection();
				statement = connection.createStatement();
				ResultSet resulitSet = statement.executeQuery(sql);	
				if(resulitSet.first()) {
					int record = resulitSet.getInt(1);	
					int totalPage = 0;
					if(record%pageSize == 0) {
						totalPage = record/pageSize;
					}else {
						totalPage = record/pageSize+1;
					}
					
					pagemodel.setTotalPage(totalPage);
					pagemodel.setCurrentPage(pageNo);
					pagemodel.setHasbefore(pageNo>1);
					pagemodel.setHasnext(pageNo<totalPage);
				
				}
				
				
				String sql1 = "select * from students limit "+((pageNo-1)*pageSize)+","+pageSize+";";
				statement1 = connection.createStatement();
				ResultSet set = statement1.executeQuery(sql1);
				
				while(set.next()) {
					id = set.getInt("id");
					String name = set.getString("name");
					String sex = set.getString("sex");
					int age = set.getInt("age");
					String _class = set.getString("_class");
					int score = set.getInt("score");
					String regtime = set.getString("regtime");
					String modtime = set.getString("modtime");
					String password = set.getString("password");
					Student student = new Student(id,name,password,sex,age,_class,score,regtime,modtime);
					students.add(student);
				}
				
				pagemodel.setData(students);
				
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					if (statement1!=null) {
						
						statement.close();
						
					}
					
					if (statement!=null) {
						
						statement.close();
						
					}
					if (connection!=null) {
						connection.close();
						
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return pagemodel;
			
			
			
		}

		
	
		//查看全体学生
				public String findstudent() {
					Map<Integer,Student> students = StudentServiceimpl.getstudentServiceimpl().getstudents();
					String sql = "select * from students;";
					Connection connection = null;
					Statement statement = null;
					int id = 0;
					try {
						connection = DBUtils.getConnection();
						statement = connection.createStatement();
						ResultSet resulitSet = statement.executeQuery(sql);	
						while(resulitSet.next()) {
							id = resulitSet.getInt("id");
							String name = resulitSet.getString("name");
							String sex = resulitSet.getString("sex");
							int age = resulitSet.getInt("age");
							String _class = resulitSet.getString("_class");
							int score = resulitSet.getInt("score");
							String regtime = resulitSet.getString("regtime");
							String modtime = resulitSet.getString("modtime");
							String password = resulitSet.getString("password");
							Student student = new Student(id,name,password,sex,age,_class,score,regtime,modtime);
							students.put(id,student);
						}
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}finally {
						try {
							if (statement!=null) {
								
								statement.close();
								
							}
							if (connection!=null) {
								connection.close();
								
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					return ServerResponse.ServerResponseBySucess("查看成功",students.values()).obj2str();
					
					
				}
				
				
				
				//根据id查看学生
				public ServerResponse<Student> findStudentByid(int id) {
					Map<Integer,Student> students = StudentServiceimpl.getstudentServiceimpl().getstudents();
					String sql = "select * from students where id="+id+";";
					Connection connection = null;
					Statement statement = null;
					try {
						connection = DBUtils.getConnection();
						statement = connection.createStatement();
						ResultSet resulitSet = statement.executeQuery(sql);	
						while(resulitSet.next()) {
							id = resulitSet.getInt("id");
							String name = resulitSet.getString("name");
							String sex = resulitSet.getString("sex");
							int age = resulitSet.getInt("age");
							String _class = resulitSet.getString("_class");
							int score = resulitSet.getInt("score");
							String regtime = resulitSet.getString("regtime");
							String modtime = resulitSet.getString("modtime");
							String password = resulitSet.getString("password");
							Student student = new Student(id,name,password,sex,age,_class,score,regtime,modtime);
							students.put(id,student);
						}
						if(resulitSet.first()) {
							
							return ServerResponse.ServerResponseBySucess("查找成功", students.get(id));
						}
						
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}finally {
						try {
							if (statement!=null) {
								
								statement.close();
								
							}
							if (connection!=null) {
								connection.close();
								
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					return ServerResponse.ServerResponseByFail(Const.STUDENT_ID__NOT_EXISTS,"学生ID不存在");
										
				}
				
				
				
				
				
				
				
				//修改学生信息
				public String updatastudent(int id,String name,String password,String sex,int age,String _class,int score) {
					String sql = "update students set name=?,sex=?,age=?,_class=?,score=?,modtime=now(),password=?  where id="+id+";";
					System.out.println(sql);
					Connection connection = null;
					PreparedStatement statement = null;
					try {
						connection = DBUtils.getConnection();
						statement = connection.prepareStatement(sql);
						statement.setString(1, name);
						statement.setString(2, sex);
						statement.setInt(3, age);
						statement.setString(4, _class);
						statement.setInt(5, score);
						statement.setString(6,password);
						statement.executeUpdate();
						
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}finally {
						try {
							if (statement!=null) {
								statement.close();
							}
							if (connection!=null) {
								connection.close();
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					return ServerResponse.ServerResponseBySucess("修改成功").obj2str();
					
				}
				
	
	
				
				//删除学生
				public String deleteStudent(int id) {
					String sql = "delete from students where id="+id+";";
					Connection connection = null;
					Statement statement = null;
					try {
						connection = DBUtils.getConnection();
						statement = connection.createStatement();
						statement.executeUpdate(sql);
						
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}finally {
						try {
							if (statement!=null) {
								statement.close();
							}
							if (connection!=null) {
								connection.close();
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
					}
					return ServerResponse.ServerResponseBySucess("删除成功").obj2str();
					
				}
				
	
	
	
	
	
	
	
	
	

}
