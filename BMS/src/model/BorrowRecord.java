package model;

import java.sql.Date;

public class BorrowRecord {
    // 借阅记录的唯一标识
    private int recordId;
    
    // 借阅者的用户ID
    private String userId;
    //用户姓名
    private String username;
    // 借阅的图书ID
    private String bookId;
    //书名
    private String title;
    // 借阅日期
    private Date borrowDate;
    
    // 归还日期，初始为NULL表示尚未归还
    private Date returnDate;
    
    // 借阅状态，取值为 'borrowed' 或 'returned'
    private String status;

    // Getter 和 Setter 方法
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    	this.title=title;
    }
   	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    public BorrowRecord(int recordId, String userId,String username, String bookId, Date borrowDate, Date returnDate, String status,String title) {
        this.recordId = recordId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.title=title;
        this.username=username;
    }
}
