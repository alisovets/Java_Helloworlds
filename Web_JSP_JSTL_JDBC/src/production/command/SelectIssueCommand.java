package command;

import java.util.List;

import model.Issue;
import model.dao.IssueDao;

public class SelectIssueCommand extends ServletCommand {

	@Override
	public String execute() {

		@SuppressWarnings("unchecked")
		List<Integer> listIds = (List<Integer>) request.getSession().getAttribute(SELECTED_ISSUES_ATTRIBUTE);
		Integer id = Integer.valueOf(request.getParameter("issueId"));
		listIds.add(id);

		IssueDao dao = ServletCommand.daoFactory.getIssueDao();
		List<Issue> issues = dao.getActuals();
		request.setAttribute(ServletCommand.ISSUE_LIST_ATTRIBUTE, issues);

		return getJspName(ISSUE_LIST_JSP);

	}

}
