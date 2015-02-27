package command;

import java.util.List;

import model.Issue;
import model.dao.IssueDao;

public class ViewIssueCommand extends ServletCommand {

	@Override
	public String execute() {
		IssueDao dao = ServletCommand.daoFactory.getIssueDao();
		List<Issue> issues = dao.getActuals();
		request.setAttribute(ServletCommand.ISSUE_LIST_ATTRIBUTE, issues);
		return getJspName(ISSUE_LIST_JSP);
	}
}
