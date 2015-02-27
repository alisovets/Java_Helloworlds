package command;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import log.Loger;
import model.Consumer;
import model.Order;
import model.dao.ConsumerDao;
import model.dao.OrderDao;

import org.apache.commons.lang.StringUtils;

public class LoginCommand extends ServletCommand {
	private static final String EMPTY_LOGIN_KEY = "login.empty";
	private static final String EMPTY_PASSWORD_KEY = "password.empty";
	private static final String LOGIN_INCORRECT_KEY = "login.incorrect";
	
	 ResourceBundle messageResources; 
		      

	@Override
	public String execute() {
		messageResources = ResourceBundle.getBundle("messages", getSessionLocale());
		String login = request.getParameter("login");
		
		if (StringUtils.isEmpty(login)) {
			request.setAttribute("errmessage", messageResources.getString(EMPTY_LOGIN_KEY));
			return getJspName(LOGIN_JSP);
		}
		
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(password)) {
			request.setAttribute("errmessage", messageResources.getString(EMPTY_PASSWORD_KEY));
			return getJspName(LOGIN_JSP);
		}

		
		Consumer consumer;
		ConsumerDao consumerDao = daoFactory.getConsumerDao();
		consumer = consumerDao.get(login, password);
		if (consumer == null) {
			request.setAttribute("errmessage", messageResources.getString(LOGIN_INCORRECT_KEY));
			loger.log(Loger.Event.LOGIN_ATTEMPT, getClass().getName(), "attempted login:" + login);
			return getJspName(LOGIN_JSP);
		}
		
		loger.log(Loger.Event.LOGIN, getClass().getName(), "login:" + login);

		HttpSession session = request.getSession();
		session.setAttribute(CONSUMER_ATTRIBUTE, consumer);
		session.setAttribute(ServletCommand.SELECTED_ISSUES_ATTRIBUTE, new ArrayList<Integer>());
		OrderDao orderDao = ServletCommand.daoFactory.getOrderDao();
		List<Order> orderList = orderDao.getByConsumerId(consumer.getId());
		request.setAttribute(ServletCommand.ORDER_LIST_ATTRIBUTE, orderList);
		return getJspName(MAIN_JSP);
	}

}
