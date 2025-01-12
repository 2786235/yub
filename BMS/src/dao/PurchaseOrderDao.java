package dao;

import java.util.List;

import model.PurchaseOrder;

public interface PurchaseOrderDao {
	public boolean insertOrder(String bookId,String title,int quantity);//生成订单
	public List<PurchaseOrder> findAllOrder();//查看所有订单
}
