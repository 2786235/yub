package dao;

import java.util.List;

import model.InRecords;

public interface InRecordsDao {
	public boolean inRecords(int orderId,String bookId,int quantity);//������
	public List<InRecords> findAll();//�鿴����¼
}
