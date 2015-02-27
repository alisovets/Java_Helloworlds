package command;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import log.Loger;
import model.dao.factory.AbstractDaoFactory;
import model.dao.factory.DaoFactory;

public abstract class ServletCommand {
	protected Loger loger = Loger.getInstanse();		
	
	public  static final String CONSUMER_ATTRIBUTE = "consumer"; 
	protected static final String LOCALE_ATTRIBUTE = "locale";
	protected static final String ISSUE_LIST_ATTRIBUTE = "issueList";
	protected static final String SELECTED_ISSUES_ATTRIBUTE = "selectedIds";
	protected static final String ORDER_ATTRIBUTE = "order";
	protected static final String ORDER_LIST_ATTRIBUTE = "orderList";
	protected static final String ORDER_ID_ATTRIBUTE = "order_id";
	protected static final String ADMIN_ATRIBUTE = "administrator";
	protected static final String ISSUE_NAME_ATTRIBUTE = "name";
	protected static final String ISSUE_PUBLISHER_ATTRIBUTE = "publisher";
	protected static final String ISSUE_PRICE_ATTRIBUTE = "price";
	protected static final String ISSUE_PERIODICITY_ATTRIBUTE = "periodicity";
	protected static final String ISSUE_ACTUAL_ATTRIBUTE = "actual";
	protected static final String MESSAGE_ATTRIBUTE = "message";
			
	
	protected static final String LOGIN_PARAMETER = "login";
	protected static final String ADMIN_LOGIN_PARAMETER = "adminlogin";
	protected static final String PASSWORD_PARAMETER = "password";
	protected static final String ORDER_ID_PARAMETER = "orderId";
	protected static final String ISSUE_ID_PARAMETER = "issueId";
	protected static final String LANGUAGE_PARAMETER = "newlanguage";
	
	
	protected static final String LOGIN_JSP = "login";
	protected static final String ADMIN_LOGIN_JSP = "adminlogin";
	protected static final String MAIN_JSP = "orderlist";
	protected static final String ISSUE_LIST_JSP = "issuelist";
	protected static final String NEW_ORDER_JSP = "neworder";
	protected static final String VIEW_ORDER_JSP = "vieworder";
	protected static final String ADMIN_ISSUE_LIST_JSP = "adminissuelist";
	
	protected static final String COUNTRY_CODE = "UA";
	protected static final String DEFAULT_LANGUAGE = "ru";
	
	protected static final DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory("mySQL");
	
	protected HttpServletRequest request; 
	
	public void setRequest(HttpServletRequest request){
		this.request = request;
	}
	
	protected Locale getSessionLocale(){
		HttpSession session = request.getSession();
		Locale locale = (Locale)session.getAttribute(LOCALE_ATTRIBUTE);
		if(locale == null){
			locale = new Locale(DEFAULT_LANGUAGE, COUNTRY_CODE);
		}
		session.setAttribute(LOCALE_ATTRIBUTE, locale);
		return locale;
	}
	
//	protected String getLanguage(){
//		return  getSessionLocale().getLanguage();
//	}
	
	
	
	protected String getJspName(String key){
		ResourceBundle jspNameResources = ResourceBundle.getBundle("jspnames", getSessionLocale());
		return jspNameResources.getString(key);
	}
	
	
	abstract public String execute();
	
}


