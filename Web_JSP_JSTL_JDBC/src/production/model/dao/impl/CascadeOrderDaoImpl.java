package model.dao.impl;

import java.util.List;

import log.Loger;
import model.CascadeOrder;
import model.CascadeOrderItem;
import model.Order;
import model.OrderItem;
import model.dao.CascadeOrderDao;
import model.dao.CascadeOrderItemDao;
import model.dao.OrderDao;
import model.dao.OrderItemDao;

public class CascadeOrderDaoImpl implements CascadeOrderDao{
	private Loger loger = Loger.getInstanse();
	
	public int insert(CascadeOrder cascadeOrder){
		OrderDao orderDao = new OrderDaoImpl();
		OrderItemDao orderItemDao = new OrderItemDaoImpl();
		
		int orderId = orderDao.insert(cascadeOrder);
		System.out.println("Order inserted");
		
		for(CascadeOrderItem cascadeOrderItem: cascadeOrder.getOrderItems()){
			OrderItem orderItem = new OrderItem(0, orderId, cascadeOrderItem.getIssue().getId(), cascadeOrderItem.getQuantity());
			orderItemDao.insert(orderItem);
		}
		
		loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "Order items inserted with id:" + orderId);
		return orderId;
	}
	
	public CascadeOrder get(int id){
		OrderDao orderDao = new OrderDaoImpl();
		Order order = orderDao.get(id);
		if(order == null){
			return null;
		}
		
		CascadeOrderItemDao cascadeOrderItemDao = new CascadeOrderItemDaoImpl();
		List<CascadeOrderItem> orderItems = cascadeOrderItemDao.getByOrderId(order.getId());
				
		CascadeOrder cascadeOrder = new CascadeOrder(order.getId(), order.getConsumerId(), order.getSubscriptYear(), order.getCreateDate(), order.getPaidDate(), orderItems);
		return cascadeOrder;
	}

}
