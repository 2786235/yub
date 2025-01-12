package service;

import java.util.List;

import model.Book;
import model.BorrowRecord;
import model.InRecords;
import model.PurchaseOrder;

public interface BookService {
	public List<Book> findAllBooks();//�鿴����ͼ��
	public List<Book> selectAllBooks(String title);//����ͼ��
	public boolean borrowBook(String userId, String bookId,String title);//����
	public List<BorrowRecord> findBorrow(String userId);//�鿴�������м�¼
	public List<BorrowRecord> selectBorrow(String userId);//�鿴����δ�黹�Ľ��ļ�¼
	public boolean returnBook(int recordId);//�黹ͼ��
	public List<BorrowRecord> findAllBorrow();//�鿴���н��ļ�¼
	public List<BorrowRecord> select1Borrow(String userId);//����ĳ�˽��ļ�¼
	public boolean insertOrder(String bookId,String title,int quantity);//���ɶ���
	public List<PurchaseOrder> findAllOrder();//�鿴���ж���
	public boolean inRecords(int orderId,String bookId,int quantity);//������
	public List<InRecords> findAll();//�鿴����¼

}
