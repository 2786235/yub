package model;

public class Book {
    // 图书唯一标识
    private String bookId;
    // 书名
    private String title;
    // 作者
    private String author;
    // 出版社
    private String publisher;
    // ISBN码
    private String ISBN;
    // 类别
    private String category;
    // 数量
    private int stock;

    // 无参构造函数
    public Book() {
        super();
    }

    // 带参数的构造函数
    public Book(String bookId, String title, String author, String publisher, String ISBN, String category, int stock) {
        super();
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.category = category;
        this.stock = stock;
    }

    // 获取图书ID
    public String getBookId() {
        return bookId;
    }

    // 设置图书ID
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    // 获取书名
    public String getTitle() {
        return title;
    }

    // 设置书名
    public void setTitle(String title) {
        this.title = title;
    }

    // 获取作者
    public String getAuthor() {
        return author;
    }

    // 设置作者
    public void setAuthor(String author) {
        this.author = author;
    }

    // 获取出版社
    public String getPublisher() {
        return publisher;
    }

    // 设置出版社
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // 获取ISBN码
    public String getISBN() {
        return ISBN;
    }

    // 设置ISBN码
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    // 获取类别
    public String getCategory() {
        return category;
    }

    // 设置类别
    public void setCategory(String category) {
        this.category = category;
    }

    // 获取库存数量
    public int getStock() {
        return stock;
    }

    // 设置库存数量
    public void setStock(int stock) {
        this.stock = stock;
    }
}