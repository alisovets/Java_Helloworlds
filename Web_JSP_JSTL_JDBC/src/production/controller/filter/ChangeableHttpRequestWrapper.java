package controller.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ChangeableHttpRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String> newParameters;
	private HttpServletRequest request;

	public ChangeableHttpRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
		newParameters = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			newParameters.put(name, value);
		}

	}

	public void setParameter(String name, String value) {
		newParameters.put(name, value);
	}

	public void removeParameter(String name) {
		newParameters.remove(name);
	}

	@Override
	public String getParameter(String name) {
		return newParameters.get(name);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(newParameters.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return (String[]) newParameters.values().toArray();

	}
}
