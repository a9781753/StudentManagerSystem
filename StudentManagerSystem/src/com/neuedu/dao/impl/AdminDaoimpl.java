package com.neuedu.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IAdminDao;
import com.neuedu.pojo.Course;
import com.neuedu.service.impl.CourseServiceimpl;

public class AdminDaoimpl implements IAdminDao {

	//ѧ��ѡ��
		public String choiceCourse(int cid) {
			String cname = null;
			
			String sql1 = "select * from courses where cid='"+cid+"';";
			Connection connection = null;
			Statement statement = null;
			try {
				connection = DBUtils.getConnection();
				statement = connection.createStatement();
				ResultSet res =statement.executeQuery(sql1);
				res.next();
				cname = res.getString("cname");
			
				String sql = "insert into studentCourses (cid,cname) values('"+cid+"','"+cname+"')";
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
		
	
	
		//�鿴��ѡ�γ�
				public String findChoiceCourse() {
					Map<Integer,Course> courses = CourseServiceimpl.getCourseServiceimpl().getCourses();
					String sql = "select * from studentCourses;";
					Connection connection = null;
					Statement statement = null;
					int cid = 0;
					courses.clear();
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
				
				
				
				
				//ȡ��ѡ��
				public String deleteStudentCourse(int cid) {
					String sql = "delete from studentCourses where cid="+cid+";";
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
				
				
				
				
				
				
	
	
	
	
	
}
