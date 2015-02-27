package command;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import log.Loger;
import model.Administrator;
import model.dao.AdministratorDao;

import org.apache.commons.lang.StringUtils;

public class AdminLoginCommand extends ServletCommand {
	private static final String EMPTY_LOGIN_KEY = "login.empty";
	private static final String LOGIN_INCORRECT_KEY = "login.incorrect";
	
	 ResourceBundle messageResources; 

	@Override
	public String execute() {
		messageResources = ResourceBundle.getBundle("messages", getSessionLocale());
		
		String login = request.getParameter("adminlogin");
		System.out.println("login = " + login);

		if (StringUtils.isEmpty(login)) {
			request.setAttribute("errmessage", messageResources.getString(EMPTY_LOGIN_KEY));
			return getJspName(ADMIN_LOGIN_JSP);
		}

		String password = request.getParameter("password");
		AdministratorDao adminDao = daoFactory.getAdministratorDao();
		Administrator admin = adminDao.get(login, password);

		if (admin == null) {
			request.setAttribute("errmessage", messageResources.getString(LOGIN_INCORRECT_KEY));
			request.setAttribute("adminlogin", login);
			loger.log(Loger.Event.LOGIN_ATTEMPT, getClass().getName(), "attempted admin login:" + login);
			return getJspName(ADMIN_LOGIN_JSP);
		}
		loger.log(Loger.Event.LOGIN, getClass().getName(), "administrator login:" + login);
		
		HttpSession session = request.getSession();
		session.setAttribute(ADMIN_ATRIBUTE, admin);
		
		AdminViewIssueCommand command = new AdminViewIssueCommand();
		command.setRequest(request);
		return command.execute();
				
	}
	
}
