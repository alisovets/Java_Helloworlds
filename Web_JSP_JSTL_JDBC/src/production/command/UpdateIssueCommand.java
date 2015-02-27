package command;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import log.Loger;
import model.Administrator;
import model.Issue;
import model.dao.IssueDao;

public class UpdateIssueCommand extends ServletCommand {
	Loger loger = Loger.getInstanse();

	@Override
	public String execute() {
		HttpSession session = request.getSession();
		Administrator admin = (Administrator) session.getAttribute(ADMIN_ATRIBUTE);
//		if (admin == null) {
//			return getFullNameJsp(ADMIN_LOGIN_JSP);
//		}

		Locale locale = (Locale) session.getAttribute(LOCALE_ATTRIBUTE);
		Issue issue = new Issue();		
		issue.setId(Integer.valueOf(request.getParameter(ISSUE_ID_PARAMETER)));
		issue.setName(request.getParameter(ISSUE_NAME_ATTRIBUTE));
		issue.setPublisher(request.getParameter(ISSUE_PUBLISHER_ATTRIBUTE));
		issue.setPeriodicity(Issue.Periodicity.valueOf(request.getParameter(ISSUE_PERIODICITY_ATTRIBUTE)));
		issue.setActual(Boolean.valueOf(request.getParameter(ISSUE_ACTUAL_ATTRIBUTE)));
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		String priceString = request.getParameter(ISSUE_PRICE_ATTRIBUTE);
		double price;
		try {
			try {
				price = formatter.parse(priceString).doubleValue();
			} catch (ParseException e) {
				price = Double.valueOf(priceString);
			}
			issue.setPrice(price);
			issue.setAdminId(admin.getId());
			IssueDao issueDao = daoFactory.getIssueDao();
			issueDao.update(issue);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			loger.log(Loger.Event.ERROR, getClass().getName(), "failed parsing " + request.getParameter("ISSUE_PRICE_ATTRIBUTE"), e);
		}

		AdminViewIssueCommand command = new AdminViewIssueCommand();
		command.setRequest(request);
		return command.execute();
	}

}
