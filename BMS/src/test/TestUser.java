package test;

import util.DaoFactory;

public class TestUser {
	public static void main(String[] args) {
		String username=DaoFactory.getUserDaoImpl().getUser("1001", "1001");
		if(username!=null) {
			System.out.println(username+"pass");
		}else {
			System.out.println("lose");
		}
	}
}
