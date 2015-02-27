package command;

import java.util.List;

import model.Issue;
import model.dao.IssueDao;

public class AdminViewIssueCommand extends ServletCommand {

	@Override
	public String execute() {

		IssueDao dao = daoFactory.getIssueDao();
		List<Issue> issues = dao.getAll();
		request.setAttribute(ISSUE_LIST_ATTRIBUTE, issues);
		request.setAttribute("periodicities", Issue.Periodicity.values());
		return getJspName(ADMIN_ISSUE_LIST_JSP);

	}

}
