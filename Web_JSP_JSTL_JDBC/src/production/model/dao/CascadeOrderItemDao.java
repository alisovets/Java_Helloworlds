package model.dao;

import java.util.List;

import model.CascadeOrderItem;

public interface CascadeOrderItemDao {
	List<CascadeOrderItem> getByOrderId(int orderId);
	

}
