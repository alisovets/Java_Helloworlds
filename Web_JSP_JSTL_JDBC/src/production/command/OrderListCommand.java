package command;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import model.Consumer;
import model.Order;
import model.dao.OrderDao;

public class OrderListCommand extends ServletCommand {

	@Override
	public String execute() {
		HttpSession session = request.getSession();
		
		String newLang = request.getParameter(LANGUAGE_PARAMETER);
		if(!StringUtils.isEmpty(newLang)){
			session.setAttribute(LOCALE_ATTRIBUTE, new Locale(newLang.toLowerCase(), COUNTRY_CODE));
		}
		
		Consumer consumer = (Consumer) session.getAttribute(CONSUMER_ATTRIBUTE);
		if (consumer == null) {
			return getJspName(LOGIN_JSP);
		}

		OrderDao orderDao = ServletCommand.daoFactory.getOrderDao();
		List<Order> orderList = orderDao.getByConsumerId(consumer.getId());		
		request.setAttribute(ServletCommand.ORDER_LIST_ATTRIBUTE, orderList);
		return getJspName(MAIN_JSP);
	}

}
