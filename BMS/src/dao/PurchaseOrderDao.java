package dao;

import java.util.List;

import model.PurchaseOrder;

public interface PurchaseOrderDao {
	public boolean insertOrder(String bookId,String title,int quantity);//���ɶ���
	public List<PurchaseOrder> findAllOrder();//�鿴���ж���
}
