package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.Loger;

import command.CommandFactory;
import command.ServletCommand;

public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3176402715444618944L;
	private static Loger loger = Loger.getInstanse();

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		loger.log(Loger.Event.INFO, getClass().getName(), "Started.");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		loger.log(Loger.Event.INFO, getClass().getName(), "doPost started");
		try {
			HttpSession session = request.getSession();
			if (session == null) {
				response.sendRedirect("error.html");
				loger.log(Loger.Event.FATALERROR, getClass().getName(), "failed to created HttpSession");
				return;
			}

			ServletCommand command = CommandFactory.getCommand(request);
			String destinationPath = command.execute();

			ServletContext servletContext = getServletContext();
			RequestDispatcher rd = servletContext.getRequestDispatcher(destinationPath);
			rd.forward(request, response);

		} 
		catch (Throwable th) {
			loger.log(Loger.Event.FATALERROR, getClass().getName(), "fatal error in doPost method", th);
			response.sendRedirect("error.html");
			return;
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		loger.log(Loger.Event.INFO, getClass().getName(), "doGet started");
		doPost(request, response);
	}
}
