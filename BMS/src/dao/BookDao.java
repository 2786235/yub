package dao;

import model.Book;
import java.util.List;

public interface BookDao {
    public List<Book> findAllBooks();
    public List<Book> selectAllBooks(String title);
}