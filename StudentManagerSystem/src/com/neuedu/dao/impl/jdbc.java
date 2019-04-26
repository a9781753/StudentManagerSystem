package com.neuedu.dao.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Driver;
import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.pageModel;
import com.neuedu.pojo.Course;
import com.neuedu.pojo.StudentCourse;
import com.neuedu.service.impl.CourseServiceimpl;


public class jdbc {

	public static void main(String[] args) {
//		System.out.println(new jdbc().updatacourse(23,"java"));
//		System.out.println(new jdbc().deletecourse(22));
//		System.out.println(new jdbc().addcourse("jsp"));
//		 System.out.println(new jdbc().findcourse());
//		System.out.println(new jdbc().findCourseByPage(1,2));
	}
	
	

	
	
	//��ӿγ�
	public String addcourse(String cname) {
		String sql = "insert into courses(cname) values('"+cname+"')";
		String sql1 = "select * from courses;";
		Connection connection = null;
		Statement statement = null;
		int cid = 0;
		try {
			connection = DBUtils.getConnection();
			statement = connection.createStatement();
			ResultSet resulitSet = statement.executeQuery(sql1);
			while(resulitSet.next()) {
				cid = resulitSet.getInt("cid");
				String cname1 = resulitSet.getString("cname");
				if(cname1.equals(cname)) {
					return ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"�γ����Ѵ���").obj2str();
				}
			}
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
		return ServerResponse.ServerResponseBySucess("��ӳɹ�").obj2str();
		
	}
	
	
	
	//�鿴�γ�
		public String findcourse() {
			Map<Integer,Course> courses = CourseServiceimpl.getCourseServiceimpl().getCourses();
			String sql = "select * from courses;";
			Connection connection = null;
			Statement statement = null;
			int cid = 0;
			try {
				connection = DBUtils.getConnection();
				statement = connection.createStatement();
				ResultSet resulitSet = statement.executeQuery(sql);	
				while(resulitSet.next()) {
					cid = resulitSet.getInt("cid");
					String cname = resulitSet.getString("cname");
					Course course = new Course(cid,cname);
					courses.put(cid,course);
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
					if(cid==0){
						return ServerResponse.ServerResponseByFail(Const.STUDENT_COURSE_NULL,"�γ��б�Ϊ��").obj2str();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return ServerResponse.ServerResponseBySucess("��γɹ�",courses.values()).obj2str();
			
			
		}
		
		
		
		
		
		//�޸Ŀγ�
		public ServerResponse updatacourse(int cid,String cname) {
			String sql = "update courses set cname='"+cname+"' where cid="+cid+";";
			Connection connection = null;
			Statement statement = null;
			String sql1 = "select * from courses;";
			try {
				connection = DBUtils.getConnection();
				statement = connection.createStatement();
				ResultSet resulitSet = statement.executeQuery(sql1);
			
				while(resulitSet.next()) {
					System.out.println("1111111111111");
					cid = resulitSet.getInt("cid");
					String cname1 = resulitSet.getString("cname");
					if(!cname1.equals(cname)) {
						
						statement.executeUpdate(sql);
						return ServerResponse.ServerResponseBySucess("�޸ĳɹ�");
					}
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
			System.out.println("22222222222");
			return ServerResponse.ServerResponseByFail(Const.COURSE_ID_EXISTS,"�γ����Ѵ���");
			
			
		}
		
		
		
		
		//ɾ���γ�
				public String deletecourse(int cid) {
					String sql = "delete from courses where cid="+cid+";";
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
					return ServerResponse.ServerResponseBySucess("ɾ���ɹ�").obj2str();
					
				}
				
				
				
				
				//��ҳ�鿴�γ�
				public String findCourseByPage(int pageNo,int pageSize) {
					
					Map<Integer,Course> courses = CourseServiceimpl.getCourseServiceimpl().getCourses();
					String sql = "select * from courses limit "+((pageNo-1)*pageSize)+","+pageSize+";";
					System.out.println(sql);
					Connection connection = null;
					Statement statement = null;
					int cid = 0;
					try {
						connection = DriverManager.getConnection("jdbc:mysql://134.175.125.228:3306/management_system","root","Zhang19941020*");
						statement = connection.createStatement();
						ResultSet resulitSet = statement.executeQuery(sql);	
						courses.clear();
						while(resulitSet.next()) {
							cid = resulitSet.getInt("cid");
							String cname = resulitSet.getString("cname");
							Course course = new Course(cid,cname);
							courses.put(cid,course);
						}
						
					} catch (SQLException e) {
						
						e.printStackTrace();
					}finally {
						try {
							if (statement!=null) {
								
								statement.close();
								
							}
							if (statement!=null) {
								connection.close();
								
							}
							if(cid==0){
								return ServerResponse.ServerResponseByFail(Const.STUDENT_COURSE_NULL,"��ǰҳû�пγ�").obj2str();
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					return ServerResponse.ServerResponseBySucess("��γɹ�",courses.values()).obj2str();
					
					
				}
				
				
				
				
				//��ҳ�鿴�γ�
				public pageModel<Course> FindCourseByPage(int pageNo,int pageSize) {
					
					String sql = "select count(cid) from courses";
					System.out.println(sql);
					Connection connection = null;
					Statement statement = null;
					Statement statement1 = null;
					List<Course>  courses = new ArrayList<Course>();
					pageModel<Course> pagemodel = new pageModel<Course>();
					try {
						connection = DriverManager.getConnection("jdbc:mysql://134.175.125.228:3306/management_system","root","Zhang19941020*");
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
						
						
						String sql1 = "select * from courses limit "+((pageNo-1)*pageSize)+","+pageSize+";";
						statement1 = connection.createStatement();
						ResultSet set = statement1.executeQuery(sql1);
						
						while(set.next()) {
							int cid = set.getInt("cid");
							String cname = set.getString("cname");
							Course course = new Course(cid,cname);
							courses.add(course);
						}
						
						pagemodel.setData(courses);
						
						
						
						
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
				
				
				
				
	
		}





