package command;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import model.Administrator;

import org.apache.commons.lang.StringUtils;

public class AdminCommand extends ServletCommand {

	@Override
	public String execute() {

		HttpSession session = request.getSession();
		String newLang = request.getParameter(LANGUAGE_PARAMETER);
		if(!StringUtils.isEmpty(newLang)){
			session.setAttribute(LOCALE_ATTRIBUTE, new Locale(newLang.toLowerCase(), COUNTRY_CODE));
		}
		
		Administrator admin = (Administrator) session.getAttribute(ADMIN_ATRIBUTE);
		if (admin == null) {
			return getJspName(ADMIN_LOGIN_JSP);
		}
		
		AdminViewIssueCommand command = new AdminViewIssueCommand();
		command.setRequest(request);
		return command.execute();

	}

}
