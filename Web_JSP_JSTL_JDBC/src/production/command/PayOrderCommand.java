package command;

import java.sql.Date;

import model.CascadeOrder;
import model.Order;
import model.dao.CascadeOrderDao;
import model.dao.OrderDao;

public class PayOrderCommand extends ServletCommand {

	@Override
	public String execute() {

		int orderId = Integer.valueOf(request.getParameter(ORDER_ID_PARAMETER));
		OrderDao orderDao = daoFactory.getOrderDao();
		Order order = orderDao.get(orderId);
		order.setPaidDate(new Date(new java.util.Date().getTime()));
		orderDao.update(order);
		CascadeOrderDao cascadeOrderDao = daoFactory.getCascadeOrderDao();
		CascadeOrder cascadeOrder = null;
		cascadeOrder = cascadeOrderDao.get(orderId);
		request.setAttribute("order", cascadeOrder);
		return getJspName(VIEW_ORDER_JSP);
	}

}
