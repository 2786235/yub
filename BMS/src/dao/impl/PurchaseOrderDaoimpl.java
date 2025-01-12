package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PurchaseOrderDao;
import model.BorrowRecord;
import model.PurchaseOrder;
import util.DatabaseBean;

public class PurchaseOrderDaoimpl implements PurchaseOrderDao {

	@Override
	public boolean insertOrder(String bookId, String title, int quantity) {
		String sql = "INSERT INTO PurchaseOrders (BookID, Title, Quantity, OrderDate, Status) VALUES (?, ?, ?, CURDATE(), 'pending')";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        try {
        	conn=DatabaseBean.getConnection();
			pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookId);
            pstmt.setString(2, title);
            pstmt.setInt(3, quantity);
            int result= pstmt.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
			DatabaseBean.close(rs, pstmt, conn);
		}
            return false;
        }

	@Override
	public List<PurchaseOrder> findAllOrder() {
		 Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<PurchaseOrder> orders = new ArrayList<>();
	        String sql = "SELECT * FROM PurchaseOrders ORDER BY orderid DESC";
	      
	        try {
	            conn = DatabaseBean.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                PurchaseOrder order = new PurchaseOrder(
	                    rs.getInt("orderID"),
	                    rs.getString("bookID"),
	                    rs.getString("Title"),
	                    rs.getInt("quantity"),
	                    rs.getDate("orderDate"),
	                    rs.getString("status")
	                );
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // ¹Ø±Õ×ÊÔ´
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	      
	        System.out.println("Total Orders Retrieved: " + orders.size());
	        return orders;
	}

}
