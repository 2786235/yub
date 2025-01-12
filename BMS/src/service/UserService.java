package service;

import java.util.List;

import model.user;

public interface UserService {
	String getUser(String userId, String password);//检查用户
	public String getrole(String userId, String password);//得到用户权限
	public List<user> findAlluser();//查看所有用户
	public List<user> selectUser(String username);//搜索用户
	public boolean deleteUser(String userId);//删除用户
	public boolean insertUser(String userId,String username,String password,String role);//添加用户
}
