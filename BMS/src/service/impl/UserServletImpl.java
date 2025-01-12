package service.impl;

import java.util.List;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.Book;
import model.user;
import service.UserService;

public class UserServletImpl implements UserService{
	@Override
	public String getUser(String userId,String password) {
		UserDao userDao = new UserDaoImpl();
		String username=userDao.getUser(userId, password);
		return username;		
	}

	@Override
	public String getrole(String userId, String password) {
		UserDao userDao = new UserDaoImpl();
		String role=userDao.getUser(userId, password);
		return role;	
	}

	@Override
	public List<user> findAlluser() {
		UserDao userDao = new UserDaoImpl();
		List<user> users=userDao.findAlluser();
		return users;
	}

	@Override
	public List<user> selectUser(String username) {
		UserDao userDao = new UserDaoImpl();
		List<user> users=userDao.selectUser(username);
		return users;
	}

	@Override
	public boolean deleteUser(String userId) {
		UserDao userDao = new UserDaoImpl();
		return userDao.deleteUser(userId);
	}

	@Override
	public boolean insertUser(String userId, String username, String password, String role) {
		UserDao userDao = new UserDaoImpl();
		return userDao.insertUser(userId, username, password, role);
	}
}
