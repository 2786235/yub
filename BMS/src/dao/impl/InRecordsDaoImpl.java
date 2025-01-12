package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.InRecordsDao;
import model.BorrowRecord;
import model.InRecords;
import util.DatabaseBean;

public class InRecordsDaoImpl implements InRecordsDao{

	@Override
	public boolean inRecords(int orderId, String bookId, int quantity) {
		String updateBookQuery = "UPDATE Books SET Stock = Stock + ? WHERE BookID = ? AND Stock >= ?";
		String updateOrderQuery = "UPDATE PurchaseOrders SET Status = 'completed' WHERE OrderID = ?";
        String insertRecordQuery = "INSERT INTO inRecords (OrderID, BookID, OutDate, Status) VALUES (?, ?, CURDATE(), 'pending')";

        Connection conn = null;
        PreparedStatement updateStmt = null;
        PreparedStatement insertStmt = null;
        PreparedStatement orderStmt = null;

        try {
            // ��ȡ���ݿ�����
            conn = DatabaseBean.getConnection();
            // �����Զ��ύΪfalse�Ա��������
            conn.setAutoCommit(false);

            // ׼����ִ�и��¿���SQL���
            updateStmt = conn.prepareStatement(updateBookQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, bookId);
            updateStmt.setInt(3, quantity);
            int result = updateStmt.executeUpdate();

            // ����Ƿ�ɹ����¿��
            if (result > 0) {
                // ��������³ɹ�����������¼
                insertStmt = conn.prepareStatement(insertRecordQuery);
                insertStmt.setInt(1, orderId);
                insertStmt.setString(2, bookId);
                insertStmt.executeUpdate();
                //// ���¶���״̬Ϊ�����
                orderStmt = conn.prepareStatement(updateOrderQuery);
                orderStmt.setInt(1, orderId);
                orderStmt.executeUpdate();
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
	public List<InRecords> findAll() {
		List<InRecords> recordsList = new ArrayList<>();
		String sql = "SELECT InRecords.*, Books.title " +
                "FROM InRecords " +
                "INNER JOIN Books ON InRecords.bookId = Books.bookId";

        try (Connection conn = DatabaseBean.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

        	while (rs.next()) {
                int recordId = rs.getInt("RecordID");
                int orderId = rs.getInt("OrderID");
                String bookId = rs.getString("BookID");
                String title=rs.getString("title");
                java.sql.Date outDate = rs.getDate("OutDate");
                String status = rs.getString("Status");

                InRecords record = new InRecords(recordId, orderId, bookId,title, outDate, status);
                recordsList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordsList;
	}

}
