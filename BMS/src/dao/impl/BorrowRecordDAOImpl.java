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
            // ��ȡ���ݿ�����
            conn = DatabaseBean.getConnection();
            // �����Զ��ύΪfalse�Ա��������
            conn.setAutoCommit(false);

            // ׼����ִ�и��¿���SQL���
            updateStmt = conn.prepareStatement(updateBookQuery);
            updateStmt.setString(1, bookId);
            int result = updateStmt.executeUpdate();
            System.out.println("2344");

            // ����Ƿ�ɹ����¿��
            if (result > 0) {
                // ��������³ɹ���������ļ�¼
                insertStmt = conn.prepareStatement(insertRecordQuery);
                insertStmt.setString(1, userId);
                insertStmt.setString(2, bookId);
                insertStmt.setString(3, title);
                insertStmt.executeUpdate();

                // �ύ����
                conn.commit();
                return true;
            } else {
                // ���������ʧ�ܣ��ع�����
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // �����쳣ʱ�ع�����
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // �ر���Դ
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
                // ���� BorrowRecord ����һ�����캯������ʼ�����������ֶ�
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
            // �ر���Դ
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
	        // ��ȡ���ݿ�����
	        conn = DatabaseBean.getConnection();
	        // �����Զ��ύΪfalse�Ա��������
	        conn.setAutoCommit(false);

	        // ׼����ִ�и��¿���SQL���
	        updateStmt = conn.prepareStatement(updateBookQuery);
	        updateStmt.setInt(1, recordId);
	        int result = updateStmt.executeUpdate();

	        // ����Ƿ�ɹ����¿��
	        if (result > 0) {
	            // ��������³ɹ������½��ļ�¼
	            updateRecordStmt = conn.prepareStatement(updateRecordQuery);
	            updateRecordStmt.setInt(1, recordId);
	            int recordResult = updateRecordStmt.executeUpdate();

	            // �ύ����
	            conn.commit();
	            return recordResult > 0;
	        } else {
	            // ���������ʧ�ܣ��ع�����
	            conn.rollback();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            // �����쳣ʱ�ع�����
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        // �ر���Դ
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
                // ���� BorrowRecord ����һ�����캯������ʼ�����������ֶ�
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
            // �ر���Դ
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
               // ���� BorrowRecord ����һ�����캯������ʼ�����������ֶ�
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
           // �ر���Դ
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
                // ���� BorrowRecord ����һ�����캯������ʼ�����������ֶ�
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
            // �ر���Դ
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

