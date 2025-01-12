package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.Book;
import model.user;
import service.BookService;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.UserServletImpl;
import util.DaoFactory;

@WebServlet("/User/*")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//登录user
	public void userLoginServlet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		UserService userService =new UserServletImpl();
		String username=userService.getUser(userId, password);
		String role=userService.getrole(userId, password);
		System.out.println("role"+role);
		if(username!=null) {
			request.setAttribute("username", username);
			 HttpSession session = request.getSession();
	         session.setAttribute("userId", userId);
	         request.getSession().setAttribute("msg", "");
	         if ("admin".equals(role)) {
	                request.getRequestDispatcher("/admin.jsp").forward(request, response);
	            } else {
	                request.getRequestDispatcher("/student.jsp").forward(request, response);
	            }
		}
		else {
			request.getSession().setAttribute("msg", "你的学号或者密码有误，请重新输入");
			response.sendRedirect("../index.jsp");
		}
	}
	
	public void findAlluser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserServletImpl();
        List<user> users = userService.findAlluser();
        System.out.println("Total users Retrieved: " + users.size());
        request.setAttribute("users", users);
        request.getRequestDispatcher("/showuser.jsp").forward(request, response);
    }
	
	public void selectUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserServletImpl();
        String username=request.getParameter("username");
        System.out.println("username parameter: " + username);
        List<user> users = userService.selectUser(username);
        System.out.println("Total users Retrieved: " + users.size());
        request.setAttribute("users", users);
        request.getRequestDispatcher("/showuser.jsp").forward(request, response);
    }
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserService userService=new UserServletImpl();
        String userId=request.getParameter("userId");
        System.out.println("userid parameter: " + userId);
        boolean flag = userService.deleteUser(userId);
        if(flag) {
        	List<user> users = userService.findAlluser();
        	request.setAttribute("users", users);
        	request.getRequestDispatcher("/showuser.jsp").forward(request, response);
        }
    }
	
	public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserService userService=new UserServletImpl();
        String userId=request.getParameter("userId");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        
        System.out.println("userid parameter: " + userId);
        boolean flag = userService.insertUser(userId, username, password, role);
        if(flag) {
        	List<user> users = userService.findAlluser();
        	request.setAttribute("users", users);
        	request.getRequestDispatcher("/showuser.jsp").forward(request, response);
        }
    }
}
