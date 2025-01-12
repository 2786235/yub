package model;

public class Book {
    // ͼ��Ψһ��ʶ
    private String bookId;
    // ����
    private String title;
    // ����
    private String author;
    // ������
    private String publisher;
    // ISBN��
    private String ISBN;
    // ���
    private String category;
    // ����
    private int stock;

    // �޲ι��캯��
    public Book() {
        super();
    }

    // �������Ĺ��캯��
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

    // ��ȡͼ��ID
    public String getBookId() {
        return bookId;
    }

    // ����ͼ��ID
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    // ��ȡ����
    public String getTitle() {
        return title;
    }

    // ��������
    public void setTitle(String title) {
        this.title = title;
    }

    // ��ȡ����
    public String getAuthor() {
        return author;
    }

    // ��������
    public void setAuthor(String author) {
        this.author = author;
    }

    // ��ȡ������
    public String getPublisher() {
        return publisher;
    }

    // ���ó�����
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // ��ȡISBN��
    public String getISBN() {
        return ISBN;
    }

    // ����ISBN��
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    // ��ȡ���
    public String getCategory() {
        return category;
    }

    // �������
    public void setCategory(String category) {
        this.category = category;
    }

    // ��ȡ�������
    public int getStock() {
        return stock;
    }

    // ���ÿ������
    public void setStock(int stock) {
        this.stock = stock;
    }
}