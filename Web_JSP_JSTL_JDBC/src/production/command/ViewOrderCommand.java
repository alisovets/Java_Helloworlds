package command;

import model.CascadeOrder;
import model.dao.CascadeOrderDao;

public class ViewOrderCommand extends ServletCommand {

	@Override
	public String execute() {

		int orderId = Integer.valueOf(request.getParameter(ORDER_ID_PARAMETER));
		CascadeOrderDao cascadeOrderDao = daoFactory.getCascadeOrderDao();
		CascadeOrder cascadeOrder = cascadeOrderDao.get(orderId);
		request.setAttribute("order", cascadeOrder);
		return getJspName(VIEW_ORDER_JSP);
	}

}
