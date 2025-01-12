package model;

import java.sql.Date;

public class BorrowRecord {
    // ���ļ�¼��Ψһ��ʶ
    private int recordId;
    
    // �����ߵ��û�ID
    private String userId;
    //�û�����
    private String username;
    // ���ĵ�ͼ��ID
    private String bookId;
    //����
    private String title;
    // ��������
    private Date borrowDate;
    
    // �黹���ڣ���ʼΪNULL��ʾ��δ�黹
    private Date returnDate;
    
    // ����״̬��ȡֵΪ 'borrowed' �� 'returned'
    private String status;

    // Getter �� Setter ����
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
