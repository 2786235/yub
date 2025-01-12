package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BorrowRecordDAO;
import model.Book;
import model.BorrowRecord;
import util.DatabaseBean;

public class BorrowRecordDAOImpl implements BorrowRecordDAO{
	
	@Override
	public boolean borrowBook(String userId, String bookId,String title) {
        String updateBookQuery = "UPDATE Books SET Stock = Stock - 1 WHERE BookID = ? AND Stock > 0";
        String insertRecordQuery = "INSERT INTO BorrowRecords (UserID, BookID, BorrowDate, Status,title) VALUES (?, ?, CURDATE(), 'borrowed',?)";

        Connection conn = null;
        PreparedStatement updateStmt = null;
        PreparedStatement insertStmt = null;

        try {
            // 获取数据库连接
            conn = DatabaseBean.getConnection();
            // 设置自动提交为false以便管理事务
            conn.setAutoCommit(false);

            // 准备并执行更新库存的SQL语句
            updateStmt = conn.prepareStatement(updateBookQuery);
            updateStmt.setString(1, bookId);
            int result = updateStmt.executeUpdate();
            System.out.println("2344");

            // 检查是否成功更新库存
            if (result > 0) {
                // 如果库存更新成功，插入借阅记录
                insertStmt = conn.prepareStatement(insertRecordQuery);
                insertStmt.setString(1, userId);
                insertStmt.setString(2, bookId);
                insertStmt.setString(3, title);
                insertStmt.executeUpdate();

                // 提交事务
                conn.commit();
                return true;
            } else {
                // 如果库存更新失败，回滚事务
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 捕获异常时回滚事务
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 关闭资源
            try {
                if (updateStmt != null) {
                    updateStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

	@Override
	public List<BorrowRecord> findBorrow(String userId) {
		 Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
		List<BorrowRecord> borrow = new ArrayList<>();
		String sql = "SELECT BorrowRecords.*, Users.UserName " +
                "FROM BorrowRecords " +
                "INNER JOIN Users ON BorrowRecords.UserID = Users.UserID " +
                "WHERE BorrowRecords.UserID = ?";
		try {
            conn = DatabaseBean.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 假设 BorrowRecord 类有一个构造函数来初始化它的所有字段
                BorrowRecord record = new BorrowRecord(
                    rs.getInt("recordId"),
                    rs.getString("userId"),
                    rs.getString("UserName"),
                    rs.getString("bookId"),
                    rs.getDate("borrowDate"),
                    rs.getDate("returnDate"),
                    rs.getString("status"),
                    rs.getString("title")
                );
                borrow.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		System.out.println("Total Books Retrieved: " + borrow.size());
        return borrow;
	}

	@Override
	public boolean returnBook(int recordId) {
	    String updateBookQuery = "UPDATE Books SET Stock = Stock + 1 WHERE BookID = (SELECT BookID FROM BorrowRecords WHERE RecordID = ?)";
	    String updateRecordQuery = "UPDATE BorrowRecords SET ReturnDate = CURDATE(), Status = 'returned' WHERE RecordID = ?";
	    Connection conn = null;
	    PreparedStatement updateStmt = null;
	    PreparedStatement updateRecordStmt = null;

	    try {
	        // 获取数据库连接
	        conn = DatabaseBean.getConnection();
	        // 设置自动提交为false以便管理事务
	        conn.setAutoCommit(false);

	        // 准备并执行更新库存的SQL语句
	        updateStmt = conn.prepareStatement(updateBookQuery);
	        updateStmt.setInt(1, recordId);
	        int result = updateStmt.executeUpdate();

	        // 检查是否成功更新库存
	        if (result > 0) {
	            // 如果库存更新成功，更新借阅记录
	            updateRecordStmt = conn.prepareStatement(updateRecordQuery);
	            updateRecordStmt.setInt(1, recordId);
	            int recordResult = updateRecordStmt.executeUpdate();

	            // 提交事务
	            conn.commit();
	            return recordResult > 0;
	        } else {
	            // 如果库存更新失败，回滚事务
	            conn.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            // 捕获异常时回滚事务
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        // 关闭资源
	        try {
	            if (updateStmt != null) {
	                updateStmt.close();
	            }
	            if (updateRecordStmt != null) {
	                updateRecordStmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}

	@Override
	public List<BorrowRecord> selectBorrow(String userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BorrowRecord> borrowed = new ArrayList<>();
        String sql = "SELECT BorrowRecords.*, Users.UserName " +
                "FROM BorrowRecords " +
                "INNER JOIN Users ON BorrowRecords.UserID = Users.UserID " +
                "WHERE BorrowRecords.UserID = ? AND BorrowRecords.Status='borrowed'";

        try {
            conn = DatabaseBean.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 假设 BorrowRecord 类有一个构造函数来初始化它的所有字段
                BorrowRecord record = new BorrowRecord(
                    rs.getInt("recordId"),
                    rs.getString("userId"),
                    rs.getString("UserName"),
                    rs.getString("bookId"),
                    rs.getDate("borrowDate"),
                    rs.getDate("returnDate"),
                    rs.getString("status"),
                    rs.getString("title")
                );
                borrowed.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total selectBorrow Retrieved: " + borrowed.size());
        return borrowed;
    }

	@Override
	public List<BorrowRecord> findAllBorrow() {
		Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
		List<BorrowRecord> borrow = new ArrayList<>();
		String sql = "SELECT BorrowRecords.*, Users.UserName " +
	             "FROM BorrowRecords " +
	             "INNER JOIN Users ON BorrowRecords.UserID = Users.UserID";
		try {
           conn = DatabaseBean.getConnection();
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();

           while (rs.next()) {
               // 假设 BorrowRecord 类有一个构造函数来初始化它的所有字段
               BorrowRecord record = new BorrowRecord(
                   rs.getInt("recordId"),
                   rs.getString("userId"),
                   rs.getString("UserName"),
                   rs.getString("bookId"),
                   rs.getDate("borrowDate"),
                   rs.getDate("returnDate"),
                   rs.getString("status"),
                   rs.getString("title")
               );
               borrow.add(record);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           // 关闭资源
           try {
               if (rs != null) rs.close();
               if (pstmt != null) pstmt.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
		System.out.println("Total Books Retrieved: " + borrow.size());
       return borrow;
	}

	@Override
	public List<BorrowRecord> select1Borrow(String userId) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BorrowRecord> borrowed = new ArrayList<>();
        String sql = "SELECT * FROM BorrowRecords WHERE UserID = ?";

        try {
            conn = DatabaseBean.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // 假设 BorrowRecord 类有一个构造函数来初始化它的所有字段
                BorrowRecord record = new BorrowRecord(
                    rs.getInt("recordId"),
                    rs.getString("userId"),
                    rs.getString("UserName"),
                    rs.getString("bookId"),
                    rs.getDate("borrowDate"),
                    rs.getDate("returnDate"),
                    rs.getString("status"),
                    rs.getString("title")
                );
                borrowed.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total selectBorrow Retrieved: " + borrowed.size());
        return borrowed;
	}
	
}

