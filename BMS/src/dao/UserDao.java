package dao;

import java.util.List;
import model.Book;
import model.user;

public interface UserDao {
	String getUser(String userId, String password);
	String getrole(String userId, String password);
	public List<user> findAlluser();
	public List<user> selectUser(String username);
	public boolean deleteUser(String userId);
	public boolean insertUser(String userId,String username,String password,String role);
}