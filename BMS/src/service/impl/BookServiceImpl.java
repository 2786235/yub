package service.impl;

import java.util.List;

import dao.BookDao;
import dao.BorrowRecordDAO;
import dao.InRecordsDao;
import dao.PurchaseOrderDao;
import dao.impl.BookDaoImpl;
import dao.impl.BorrowRecordDAOImpl;
import dao.impl.InRecordsDaoImpl;
import dao.impl.PurchaseOrderDaoimpl;
import model.Book;
import model.BorrowRecord;
import model.InRecords;
import model.PurchaseOrder;
import service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> findAllBooks() {
		BookDao bookDao =new BookDaoImpl();
		List<Book> books= bookDao.findAllBooks();
		return books;
	}

	@Override
	public List<Book> selectAllBooks(String title) {
		BookDao bookDao =new BookDaoImpl();
		List<Book> books= bookDao.selectAllBooks(title);
		return books;
	}

	@Override
	public boolean borrowBook(String userId, String bookId,String title) {
		BorrowRecordDAO borrowRecordDAO =new BorrowRecordDAOImpl();	
		return borrowRecordDAO.borrowBook(userId, bookId,title);
	}

	@Override
	public List<BorrowRecord> findBorrow(String userId) {
		BorrowRecordDAO borrowRecordDAO =new BorrowRecordDAOImpl();
		return borrowRecordDAO.findBorrow(userId);
	}

	@Override
	public boolean returnBook(int recordId) {
		BorrowRecordDAO borrowRecordDAO =new BorrowRecordDAOImpl();
		return borrowRecordDAO.returnBook(recordId);
	}

	@Override
	public List<BorrowRecord> selectBorrow(String userId) {
		BorrowRecordDAO borrowRecordDAO =new BorrowRecordDAOImpl();
		return borrowRecordDAO.selectBorrow(userId);
	}

	@Override
	public List<BorrowRecord> findAllBorrow() {
		BorrowRecordDAO borrowRecordDAO =new BorrowRecordDAOImpl();
		return borrowRecordDAO.findAllBorrow();
	}

	@Override
	public List<BorrowRecord> select1Borrow(String userId) {
		BorrowRecordDAO borrowRecordDAO =new BorrowRecordDAOImpl();
		return borrowRecordDAO.selectBorrow(userId);
	}

	@Override
	public boolean insertOrder(String bookId, String title, int quantity) {
		PurchaseOrderDao purchaseOrder=new PurchaseOrderDaoimpl();
		return purchaseOrder.insertOrder(bookId, title, quantity);
	}

	@Override
	public List<PurchaseOrder> findAllOrder() {
		PurchaseOrderDao purchaseOrder=new PurchaseOrderDaoimpl();
		return purchaseOrder.findAllOrder();
	}

	@Override
	public boolean inRecords(int orderId, String bookId, int quantity) {
		InRecordsDao inRecords=new InRecordsDaoImpl();
		return inRecords.inRecords(orderId, bookId, quantity);
	}

	@Override
	public List<InRecords> findAll() {
		InRecordsDao inRecords=new InRecordsDaoImpl();
		return inRecords.findAll();
	}

}
