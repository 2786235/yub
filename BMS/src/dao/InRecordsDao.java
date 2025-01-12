package dao;

import java.util.List;

import model.InRecords;

public interface InRecordsDao {
	public boolean inRecords(int orderId,String bookId,int quantity);//入库操作
	public List<InRecords> findAll();//查看入库记录
}
