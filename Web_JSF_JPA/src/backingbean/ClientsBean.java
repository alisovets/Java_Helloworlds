package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.PersistenceException;

import model.Client;
import dao.ClientDao;

@ManagedBean(name = "clients")
@RequestScoped
public class ClientsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DataModel<Client> model;
	private Client client;

	public ClientsBean() {
		model = new ListDataModel<Client>();
		// model.setWrappedData(new ClientDao().findAll());
	}

	public DataModel<Client> getModel() {
		model.setWrappedData(new ClientDao().findAll());
		return model;
	}

	public Client getClient() {
		return client;
	}

	public String add() {
		return "create_client";
	}

	public String edit() {
		client = model.getRowData();

		FacesContext context = FacesContext.getCurrentInstance();
		ClientBean bean = (ClientBean) context.getApplication()
				.evaluateExpressionGet(context, "#{client}", ClientBean.class);
		bean.setClient(client);
		bean.setMessage(null);

		return "edit_client";
	}

	public String delete() {
		client = model.getRowData();

		FacesContext context = FacesContext.getCurrentInstance();
		ClientBean bean = (ClientBean) context.getApplication()
				.evaluateExpressionGet(context, "#{client}", ClientBean.class);

		bean.setClient(client);
		ClientDao clientDao = new ClientDao();
		try {
			clientDao.remove(client);
		} catch (PersistenceException e) {
			// ignore
		}
		return "client_list";
	}

	public String select() {
		client = model.getRowData();
		FacesContext context = FacesContext.getCurrentInstance();
		OrderBean bean = (OrderBean) context.getApplication()
				.evaluateExpressionGet(context, "#{order}", OrderBean.class);
		bean.getOrder().setClient(client);
		if (bean.getOrder().getRentOrderId() == 0) {
			return "create_order";
		}
		return "edit_order";
	}

	public String cancel() {
		client = model.getRowData();
		FacesContext context = FacesContext.getCurrentInstance();
		OrderBean bean = (OrderBean) context.getApplication()
				.evaluateExpressionGet(context, "#{order}", OrderBean.class);
		if (bean.getOrder().getRentOrderId() == 0) {
			return "create_order";
		}
		return "edit_order";
	}
}
