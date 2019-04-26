package com.neuedu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.common.Const;
import com.neuedu.common.DBUtils;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.IStudentChoiceCourseDao;
import com.neuedu.pojo.StudentCourse;

public class StudentChoiceCourseDao implements IStudentChoiceCourseDao {

	@Override
	//学生是否已近选过这门课了
	public int isChoice(int sid, int cid) {
		String sql = "select count(id) from studentcourse where sid=? and cid=?";
		Connection connection = null;
		PreparedStatement statement = null;
		int a=0;
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, sid);
			statement.setInt(2, cid);
			ResultSet resulitSet = statement.executeQuery();
			while(resulitSet.next()) {
			a=resulitSet.getInt(1);
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
		return a;
	}

	@Override
	//学生选课
	public int ChoiceCourse(int sid, int cid) {
		if(isChoice(sid,cid)==0) {
			String sql = "insert into studentcourse(sid,cid) values(?,?)";
			Connection connection = null;
			PreparedStatement statement = null;
			PreparedStatement statement1 = null;
			int num;
			try {
				connection = DBUtils.getConnection();
				statement = connection.prepareStatement(sql);
				statement.setInt(1,sid);
				statement.setInt(2,cid);
				return statement.executeUpdate();	
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
			
		}
		return 0;
		
		
	}

	@Override
	//学生课表
	public List<StudentCourse> findCoursesBySid(int sid) {
		List<StudentCourse> studentCourse = new ArrayList<StudentCourse>();
		String sql = "select id,sid,cid from studentcourse where sid=?";
		String sql1 = "select cname from courses where cid=?";
		Connection connection = null;
		PreparedStatement statement = null;
		int _cid=0;
		String cname=null;
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, sid);
			ResultSet resulitSet = statement.executeQuery();
			while(resulitSet.next()) {
				int id=resulitSet.getInt("id");
				int _sid=resulitSet.getInt("sid");
				_cid=resulitSet.getInt("cid");
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setInt(1, _cid);
				ResultSet resulitSet1 = statement1.executeQuery();
				
				while(resulitSet1.next()) {
					cname=resulitSet1.getString("cname");	
				}
				StudentCourse sc = new StudentCourse(id,_cid,sid,cname);
				studentCourse.add(sc);
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
	
		return studentCourse;
	}
	@Override
	//取消选课
	public int deleteSutdentCourse(int sid,int cid) {
		String sql = "delete from studentcourse where sid=? and cid=?";
		Connection connection = null;
		PreparedStatement statement = null;
		int a=0;
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, sid);
			statement.setInt(2, cid);
			a = statement.executeUpdate();
	
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
	
		return a;
	}

}
