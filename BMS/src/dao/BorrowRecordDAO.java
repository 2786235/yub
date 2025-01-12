package dao;

import java.util.List;

import model.Book;
import model.BorrowRecord;

public interface BorrowRecordDAO {
	public boolean borrowBook(String userId, String bookId,String title);
	public List<BorrowRecord> findBorrow(String userId);
	public List<BorrowRecord> selectBorrow(String userId);
	public boolean returnBook(int recordId);//归还图书
	public List<BorrowRecord> findAllBorrow();//查看所有借阅记录
	public List<BorrowRecord> select1Borrow(String userId);//查找某人借阅记录
}
