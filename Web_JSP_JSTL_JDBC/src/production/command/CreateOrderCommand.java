package command;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import model.CascadeOrder;
import model.CascadeOrderItem;
import model.Consumer;
import model.Issue;
import model.dao.IssueDao;

public class CreateOrderCommand extends ServletCommand {

	@Override
	public String execute() {
		HttpSession session = request.getSession();
		Consumer consumer = (Consumer) session.getAttribute(CONSUMER_ATTRIBUTE);

		List<CascadeOrderItem> orderItems = null;

		orderItems = getOrderItems();

		CascadeOrder order = new CascadeOrder();
		order.setConsumerId(consumer.getId());
		Date now = new Date(new java.util.Date().getTime());
		order.setCreateDate(now);
		Locale locale = (Locale) request.getSession().getAttribute(LOCALE_ATTRIBUTE);

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(now);
		order.setSubscriptYear(calendar.get(Calendar.YEAR) + 1);
		order.setOrderItems(orderItems);

		session.setAttribute(ORDER_ATTRIBUTE, order);
		System.out.println("!order = " + request.getAttribute(ORDER_ATTRIBUTE));
		return getJspName(NEW_ORDER_JSP);

	}

	List<CascadeOrderItem> getOrderItems() {
		// TODO
		@SuppressWarnings("unchecked")
		List<Integer> selectedIds = (List<Integer>) request.getSession().getAttribute(SELECTED_ISSUES_ATTRIBUTE);

		if (selectedIds == null) {
			return null;
		}

		IssueDao dao = daoFactory.getIssueDao();
		List<Issue> issues = dao.getAll();
		List<CascadeOrderItem> items = new ArrayList<CascadeOrderItem>();
		for (Issue issue : issues) {
			if (!selectedIds.contains(issue.getId())) {
				continue;
			}
			CascadeOrderItem item = new CascadeOrderItem();
			item.setIssue(issue);
			item.setQuantity(1);
			items.add(item);
		}
		return items;
	}

}
