package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.BookDao;
import model.Book;
import util.DatabaseBean;

public class BookDaoImpl implements BookDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try {
        	conn=DatabaseBean.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

            	while (rs.next()) {
                Book bk = new Book();
                bk.setBookId(rs.getString("bookid"));
                bk.setTitle(rs.getString("title"));
                bk.setAuthor(rs.getString("author"));
                bk.setPublisher(rs.getString("publisher"));
                bk.setISBN(rs.getString("isbn"));
                bk.setCategory(rs.getString("category"));
                bk.setStock(rs.getInt("stock"));
                books.add(bk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			DatabaseBean.close(rs, pstmt, conn);
		}
        System.out.println("Total Books Retrieved: " + books.size());
        return books;
    }

	@Override
	public List<Book> selectAllBooks(String title) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books WHERE title LIKE ?";
        try {
        	conn=DatabaseBean.getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("Executing query: " + sql + " with title: " + title);
			pstmt.setString(1, "%" + title + "%");
			rs = pstmt.executeQuery();

            	while (rs.next()) {
                Book bk = new Book();
                bk.setBookId(rs.getString("bookid"));
                bk.setTitle(rs.getString("title"));
                bk.setAuthor(rs.getString("author"));
                bk.setPublisher(rs.getString("publisher"));
                bk.setISBN(rs.getString("isbn"));
                bk.setCategory(rs.getString("category"));
                bk.setStock(rs.getInt("stock"));
                books.add(bk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			DatabaseBean.close(rs, pstmt, conn);
		}
        return books;
	}
}