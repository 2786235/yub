package dao;

import java.util.List;

import model.Book;
import model.BorrowRecord;

public interface BorrowRecordDAO {
	public boolean borrowBook(String userId, String bookId,String title);
	public List<BorrowRecord> findBorrow(String userId);
	public List<BorrowRecord> selectBorrow(String userId);
	public boolean returnBook(int recordId);//�黹ͼ��
	public List<BorrowRecord> findAllBorrow();//�鿴���н��ļ�¼
	public List<BorrowRecord> select1Borrow(String userId);//����ĳ�˽��ļ�¼
}
