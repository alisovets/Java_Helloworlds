package command;

import java.util.List;

import javax.servlet.http.HttpSession;

import model.CascadeOrder;
import model.CascadeOrderItem;

public class IncQuantityCommand extends ServletCommand {

	@Override
	public String execute() {

		HttpSession session = request.getSession();
		int issueId = Integer.valueOf(request.getParameter(ISSUE_ID_PARAMETER));
		CascadeOrder order = (CascadeOrder) session.getAttribute(ORDER_ATTRIBUTE);
		List<CascadeOrderItem> orderItems = order.getOrderItems();
		for (CascadeOrderItem orderItem : orderItems) {
			if (orderItem.getIssue().getId() == issueId) {
				orderItem.setQuantity(orderItem.getQuantity() + 1);
				break;
			}
		}
		return getJspName(NEW_ORDER_JSP);
	}

}
