package command;

import javax.servlet.http.HttpSession;

import model.Administrator;
import model.Issue;
import model.dao.IssueDao;

public class IssueActualCommand extends ServletCommand {

	@Override
	public String execute() {
		HttpSession session = request.getSession();
		Administrator admin = (Administrator) session.getAttribute(ADMIN_ATRIBUTE);
		int issueId = Integer.valueOf(request.getParameter(ISSUE_ID_PARAMETER));
		IssueDao issueDao = daoFactory.getIssueDao();
		Issue issue = issueDao.get(issueId);
		issue.setActual(true);
		issue.setAdminId(admin.getId());
		issueDao.update(issue);

		AdminViewIssueCommand command = new AdminViewIssueCommand();
		command.setRequest(request);
		return command.execute();
	}

}