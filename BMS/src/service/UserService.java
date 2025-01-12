package service;

import java.util.List;

import model.user;

public interface UserService {
	String getUser(String userId, String password);//����û�
	public String getrole(String userId, String password);//�õ��û�Ȩ��
	public List<user> findAlluser();//�鿴�����û�
	public List<user> selectUser(String username);//�����û�
	public boolean deleteUser(String userId);//ɾ���û�
	public boolean insertUser(String userId,String username,String password,String role);//����û�
}
