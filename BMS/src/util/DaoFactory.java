package util;

import dao.UserDao;
import dao.impl.UserDaoImpl;;

public class DaoFactory {
	public static UserDao getUserDaoImpl() {
		return new UserDaoImpl(); 
	}
}
