package command;

import javax.servlet.http.HttpSession;

import model.Administrator;
import model.Issue;
import model.dao.IssueDao;

public class AddIssueCommand extends ServletCommand {

	@Override
	public String execute() {
		HttpSession session = request.getSession();
		Administrator admin = (Administrator) session.getAttribute(ADMIN_ATRIBUTE);
		
		Issue issue = new Issue();
		int issueId = 0;
		try {
			issue.setName(request.getParameter("name"));
			issue.setPublisher(request.getParameter("publisher"));
			issue.setPrice(Double.parseDouble(request.getParameter("price")));
			issue.setPeriodicity(Issue.Periodicity.valueOf(request
					.getParameter("periodicity")));
			issue.setAdminId(admin.getId());
			IssueDao issueDao = daoFactory.getIssueDao();
			issueId = issueDao.insert(issue);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (issueId <= 0) {
			request.setAttribute(ISSUE_NAME_ATTRIBUTE, request.getParameter(ISSUE_NAME_ATTRIBUTE));
			request.setAttribute(ISSUE_PUBLISHER_ATTRIBUTE, request.getParameter(ISSUE_PUBLISHER_ATTRIBUTE));
			request.setAttribute(ISSUE_PRICE_ATTRIBUTE, request.getParameter(ISSUE_PRICE_ATTRIBUTE));
			request.setAttribute(ISSUE_PERIODICITY_ATTRIBUTE, request.getParameter(ISSUE_PERIODICITY_ATTRIBUTE));
			request.setAttribute(MESSAGE_ATTRIBUTE, "Не удалось записать. Проверьте данные");
		}
		
		AdminViewIssueCommand command = new AdminViewIssueCommand();
		command.setRequest(request);
		return command.execute();
	}

}
