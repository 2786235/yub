package service;

import java.util.List;

import model.Book;
import model.BorrowRecord;
import model.InRecords;
import model.PurchaseOrder;

public interface BookService {
	public List<Book> findAllBooks();//查看所有图书
	public List<Book> selectAllBooks(String title);//查找图书
	public boolean borrowBook(String userId, String bookId,String title);//借阅
	public List<BorrowRecord> findBorrow(String userId);//查看个人所有记录
	public List<BorrowRecord> selectBorrow(String userId);//查看个人未归还的借阅记录
	public boolean returnBook(int recordId);//归还图书
	public List<BorrowRecord> findAllBorrow();//查看所有借阅记录
	public List<BorrowRecord> select1Borrow(String userId);//查找某人借阅记录
	public boolean insertOrder(String bookId,String title,int quantity);//生成订单
	public List<PurchaseOrder> findAllOrder();//查看所有订单
	public boolean inRecords(int orderId,String bookId,int quantity);//入库操作
	public List<InRecords> findAll();//查看入库记录

}
