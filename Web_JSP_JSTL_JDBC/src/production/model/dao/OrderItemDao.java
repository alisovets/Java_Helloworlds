package model.dao;

import java.util.List;

import model.OrderItem;

public interface OrderItemDao {
	int insert(OrderItem item);
	int update(OrderItem item);
	int delete(int id);
	int deleteByOrderId(int orderId);
	OrderItem get(int id);
	List<OrderItem> getByOrderId(int orderId);
}
