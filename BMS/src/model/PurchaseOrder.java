package model;

import java.sql.Date;


public class PurchaseOrder {
    private int orderId;
    private String bookId;
    private String title;
    private int quantity;
    private Date orderDate;
    private String status;

    // 构造函数
    public PurchaseOrder(int orderId, String bookId, String title, int quantity, Date orderDate, String status) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getter 和 Setter 方法
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}