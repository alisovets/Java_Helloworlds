package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Administrator;
import model.Consumer;

public class AdminFilter  implements Filter {
	private FilterConfig config = null;
	private boolean active = false;

	@Override
	public void init(FilterConfig config) throws ServletException {

		this.config = config;
		String act = config.getInitParameter("active");
		if (act != null) {
			active = (act.toUpperCase().equals("TRUE"));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


		if (active) {
			if (!((HttpServletRequest) request).getRequestURI().matches(".*/admin")) {
				chain.doFilter(request, response);
				return;
			}
			HttpSession session = ((HttpServletRequest) request).getSession();
			Administrator admin = (Administrator) session.getAttribute("administrator");
			if ((admin == null) && !"ADMINLOGIN".equals(request.getParameter("action"))) {
				ChangeableHttpRequestWrapper requestWrapper = (ChangeableHttpRequestWrapper) request;
				requestWrapper.setParameter("action", "ADMIN");
			}
		}	
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

		config = null;
	}
}
