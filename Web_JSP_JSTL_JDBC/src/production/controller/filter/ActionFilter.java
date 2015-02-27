package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ActionFilter implements Filter {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (active) {
			ChangeableHttpRequestWrapper requestWrapper = new ChangeableHttpRequestWrapper((HttpServletRequest) request);
			String action = request.getParameter("action");
			if (StringUtils.isEmpty(action)) {
				if(((HttpServletRequest)request).getRequestURI().matches(".*/admin")){
					requestWrapper.setParameter("action", "ADMIN");
				}
				else{
					requestWrapper.setParameter("action", "DEFAULT");
				}
				
			} else {
				requestWrapper.setParameter("action", action.toUpperCase());
			}
			chain.doFilter(requestWrapper, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

		config = null;
	}

}
