package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.UserDao;
import model.Book;
import model.user;
import util.DatabaseBean;
public class UserDaoImpl implements UserDao{
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;

	@Override
	public String getUser(String userId,String password) {
		String username=null;
		String sql="select username from users where userId=? and password=?";
		try {
			conn=DatabaseBean.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,userId);
			psmt.setString(2,password);
			rs=psmt.executeQuery();
			if(rs.next()) {
				username=rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DatabaseBean.close(rs, psmt, conn);
		}
		return username;
	}
	//得到用户role
	@Override
	public String getrole(String userId, String password) {
		String userrole=null;
		String sql="select username from users where userId=? and password=?";
		try {
			conn=DatabaseBean.getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,userId);
			psmt.setString(2,password);
			rs=psmt.executeQuery();
			if(rs.next()) {
				userrole=rs.getString("role");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DatabaseBean.close(rs, psmt, conn);
		}
		return userrole;
		
	}
	//查看所有用户
	@Override
	public List<user> findAlluser() {
		 List<user> users = new ArrayList<>();
	        String sql = "SELECT * FROM users";
	        try {
	        	conn=DatabaseBean.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();

	            	while (rs.next()) {
	                user ur = new user();
	                ur.setUserId(rs.getString("userId"));
	                ur.setUsername(rs.getString("username"));
	                ur.setPassword(rs.getString("password"));
	                ur.setRole(rs.getString("role"));
	                users.add(ur);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
				DatabaseBean.close(rs, psmt, conn);
			}
	        System.out.println("Total users Retrieved: " + users.size());
	        return users;
	}
	@Override
	public List<user> selectUser(String username) {
		List<user> users = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE username LIKE ?";
        try {
        	conn=DatabaseBean.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("Executing query: " + sql + " with title: " + username);
			psmt.setString(1, "%" + username + "%");
			rs = psmt.executeQuery();

            	while (rs.next()) {
            		user ur = new user();
	                ur.setUserId(rs.getString("userId"));
	                ur.setUsername(rs.getString("username"));
	                ur.setPassword(rs.getString("password"));
	                ur.setRole(rs.getString("role"));
	                users.add(ur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			DatabaseBean.close(rs, psmt, conn);
		}
        return users;
	}
	@Override
	public boolean deleteUser(String userId) {
		try {   
			conn=DatabaseBean.getConnection();
			String sql = "delete from users where userId=?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1,userId);

			int result = psmt.executeUpdate();
			//pstmt.close(); 
			//conn.close();
			if(result>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseBean.close(rs, psmt, conn);
		}
		return false;
	}
	@Override
	public boolean insertUser(String userId,String username,String password,String role) {
		try {   
			conn=DatabaseBean.getConnection();
			String sql = "INSERT INTO users (userId, username, password, role) VALUES (?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1,userId);
			psmt.setString(2,username);
			psmt.setString(3,password);
			psmt.setString(4,role);

			int result = psmt.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseBean.close(rs, psmt, conn);
		}
		return false;
	}
}
