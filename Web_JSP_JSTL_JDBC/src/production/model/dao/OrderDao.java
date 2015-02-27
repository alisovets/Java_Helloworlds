package model.dao;

import java.util.List;

import model.Order;

public interface OrderDao {
	int insert(Order order);
	int delete(int id);
	int update(Order order);
	Order get(int id);
	List<Order> getByConsumerId(int consumerId);
}
