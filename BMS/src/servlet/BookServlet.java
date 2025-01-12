package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import dao.impl.BookDaoImpl;
import model.Book;
import model.BorrowRecord;
import model.InRecords;
import model.PurchaseOrder;
import model.user;
import service.BookService;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.UserServletImpl;

@WebServlet("/Book/*")
public class BookServlet extends  BaseServlet {
	private static final long serialVersionUID = 1L; 
	//�鿴�����鼮	
	public void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        List<Book> books = bookService.findAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/showbooks.jsp").forward(request, response);
    }
	//�����鼮
	public void selectAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        String title = request.getParameter("title");
        System.out.println("Book name parameter: " + title);
        List<Book> books = bookService.selectAllBooks(title);
        request.setAttribute("books", books);
        request.getRequestDispatcher("/showbooks.jsp").forward(request, response);
    }
	//�����鼮
	public void borrowBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String bookId = request.getParameter("bookId");
        String title = request.getParameter("title");
        System.out.println("userId"+userId);
        System.out.println("bookId"+bookId);
        System.out.println("title"+title);
        if (userId == null || userId.isEmpty() || bookId == null || bookId.isEmpty()) {
            response.sendRedirect("borrowFail.jsp");
            return;
        }
        boolean success = bookService.borrowBook(userId, bookId,title);
        if (success) {
            response.sendRedirect("/BMS/Book/findAllBooks");
        } else {
            response.sendRedirect("../borrowFail.jsp");
        }
    } 
	//�鿴�Լ��ļ�¼
	public void findBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        System.out.println("userId"+userId);
        List<BorrowRecord> borrow = bookService.findBorrow(userId);
        request.setAttribute("borrow",borrow);
        request.getRequestDispatcher("/showBorrow.jsp").forward(request, response);
	}
	//��ѯ�Լ���δ�黹�ļ�¼
	public void selectBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        System.out.println("userId"+userId);
        List<BorrowRecord> borrow = bookService.selectBorrow(userId);
        request.setAttribute("borrow",borrow);
        request.getRequestDispatcher("/showBorrow.jsp").forward(request, response);
	}
	//�黹�鼮
	public void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        HttpSession session = request.getSession();
        int recordId = Integer.parseInt(request.getParameter("recordId"));
        System.out.println("userId"+recordId);
        boolean success = bookService.returnBook(recordId);
        if (success) {
            response.sendRedirect("/BMS/Book/findBorrow");
        } else {
            response.sendRedirect("../borrowFail.jsp");
        }
    } 
	//�鿴���н��ļ�¼
	public void findAllBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        List<BorrowRecord> borrow = bookService.findAllBorrow();
        request.setAttribute("borrow",borrow);
        request.getRequestDispatcher("/showBorrow1.jsp").forward(request, response);
	}
	
	//�鿴ĳ�˵Ľ��ļ�¼
	public void select1Borrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        String userId =request.getParameter("userId");
        System.out.println("userId"+userId);
        List<BorrowRecord> borrow = bookService.selectBorrow(userId);
        request.setAttribute("borrow",borrow);
        request.getRequestDispatcher("/showBorrow1.jsp").forward(request, response);
	}
	//�鿴����
	public void findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        List<PurchaseOrder> order = bookService.findAllOrder();
        request.setAttribute("order",order);
        request.getRequestDispatcher("/showorder.jsp").forward(request, response);
	}
	//���ɶ���
	public void insertOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService=new BookServiceImpl();
        String bookId=request.getParameter("bookId");
        String title=request.getParameter("title");
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        
        System.out.println("��ȡ�� " + bookId+title+quantity);
        boolean flag = bookService.insertOrder(bookId, title, quantity);
        if(flag) {
        	List<PurchaseOrder> order = bookService.findAllOrder();
        	request.setAttribute("order",order);
        	request.getRequestDispatcher("/showorder.jsp").forward(request, response);
        }
    }
	//���
	public void inRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        BookService bookService = new BookServiceImpl();
        //HttpSession session = request.getSession();
        int orderId =Integer.parseInt(request.getParameter("orderId"));
        System.out.println(orderId);
        String bookId = request.getParameter("bookId");
        System.out.println(bookId);
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        System.out.println(quantity);
        boolean success = bookService.inRecords(orderId, bookId, quantity);
        if (success) {
            response.sendRedirect("/BMS/Book/findAllOrder");
        } else {
            response.sendRedirect("../borrowFail.jsp");
        }
    }
	//�鿴����¼
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        List<InRecords> recordsList =bookService.findAll(); 
        System.out.println("findAll: " + recordsList.size());
        request.setAttribute("recordsList", recordsList);
        request.getRequestDispatcher("/showRecords.jsp").forward(request, response);
    }
};
