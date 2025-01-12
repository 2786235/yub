package model;

import java.sql.Date;

public class InRecords {
    private int recordId;
    private int orderId;
    private String bookId;
    private String title;
    private Date outDate;
    private String status;

    // ���캯��
    public InRecords(int recordId, int orderId, String bookId,String title, Date outDate, String status) {
        this.recordId = recordId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.title = title;
        this.outDate = outDate;
        this.status = status;
    }

    // Getter �� Setter ����
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
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

    // ��������
    public void setTitle(String title) {
        this.title = title;
    }
}