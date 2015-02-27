package command;

import javax.servlet.http.HttpServletRequest;

import log.Loger;

public class CommandFactory {
	final public static String ACTION_PARAMETER_NAME = "action";
	private static Loger loger = Loger.getInstanse();

	private static enum Command {
		DEFAULT(OrderListCommand.class),

		LOGIN(LoginCommand.class),

		VIEWISSUE(ViewIssueCommand.class),

		SELECTISSUE(SelectIssueCommand.class),

		DESELECTISSUE(DeselectIssueCommand.class),

		CREATEORDER(CreateOrderCommand.class),

		INCQUANTITY(IncQuantityCommand.class),

		DECQUANTITY(DecQuantityCommand.class),

		SAVEORDER(SaveOrderCommand.class),

		PAYORDER(PayOrderCommand.class),

		ORDERLIST(OrderListCommand.class),

		VIEWORDER(ViewOrderCommand.class),

		ADMIN(AdminCommand.class),

		ADMINLOGIN(AdminLoginCommand.class),

		ISSUEACTUAL(IssueActualCommand.class),

		ISSUENOTACTUAL(IssueNotActualCommand.class),

		UPDATEISSUE(UpdateIssueCommand.class),

		ADDISSUE(AddIssueCommand.class);

		private Class<? extends ServletCommand> classObject;

		private Command(Class<? extends ServletCommand> classObject) {
			this.classObject = classObject;
		}

		public ServletCommand getNewInstanse(HttpServletRequest request) {
			try {
				ServletCommand command = classObject.newInstance();
				command.setRequest(request);
				return command;
			} catch (InstantiationException e) {
				loger.log(Loger.Event.ERROR, "CommandFactory", "failet instantion commad of " + classObject.getName(), e);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				loger.log(Loger.Event.ERROR, "CommandFactory", "failet instantion commad of " + classObject.getName(), e);
				throw new RuntimeException(e);
			}
		}
	}

	public static ServletCommand getCommand(HttpServletRequest request) {
		Command command = Command.valueOf(request.getParameter(ACTION_PARAMETER_NAME));
		ServletCommand servletCommand = command.getNewInstanse(request);
		loger.log(Loger.Event.INFO, "CommandFactory", "instantion commad of " + command.getClass().getName());
		return servletCommand;
	}
}
