package command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import model.CascadeOrder;
import model.dao.CascadeOrderDao;

public class SaveOrderCommand extends ServletCommand {

	@Override
	public String execute() {
		HttpSession session = request.getSession();

		CascadeOrder order = (CascadeOrder) session.getAttribute(ORDER_ATTRIBUTE);
		CascadeOrderDao cascadeOrderDao = daoFactory.getCascadeOrderDao();
		int orderId = cascadeOrderDao.insert(order);
		order.setId(orderId);

		session.setAttribute(SELECTED_ISSUES_ATTRIBUTE, new ArrayList<Integer>());
		session.removeAttribute(ORDER_ATTRIBUTE);
		request.setAttribute(ORDER_ATTRIBUTE, order);
		return getJspName(VIEW_ORDER_JSP);
	}

}
