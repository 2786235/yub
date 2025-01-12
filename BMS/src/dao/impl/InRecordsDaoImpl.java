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
            // 获取数据库连接
            conn = DatabaseBean.getConnection();
            // 设置自动提交为false以便管理事务
            conn.setAutoCommit(false);

            // 准备并执行更新库存的SQL语句
            updateStmt = conn.prepareStatement(updateBookQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, bookId);
            updateStmt.setInt(3, quantity);
            int result = updateStmt.executeUpdate();

            // 检查是否成功更新库存
            if (result > 0) {
                // 如果库存更新成功，插入出库记录
                insertStmt = conn.prepareStatement(insertRecordQuery);
                insertStmt.setInt(1, orderId);
                insertStmt.setString(2, bookId);
                insertStmt.executeUpdate();
                //// 更新订单状态为已完成
                orderStmt = conn.prepareStatement(updateOrderQuery);
                orderStmt.setInt(1, orderId);
                orderStmt.executeUpdate();
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
