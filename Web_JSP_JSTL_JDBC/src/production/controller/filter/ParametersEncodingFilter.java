package controller.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ParametersEncodingFilter implements Filter {
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
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = new String(request.getParameter(name).getBytes(
						"ISO-8859-1"), "UTF-8");
				((ChangeableHttpRequestWrapper)request).setParameter(name, value);
			}
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {

		config = null;
	}
}
