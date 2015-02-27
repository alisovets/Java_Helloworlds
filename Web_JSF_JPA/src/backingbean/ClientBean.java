package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.RollbackException;

import model.Client;
import dao.ClientDao;


@ManagedBean(name = "client")
@RequestScoped
public class ClientBean  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Client client = new Client();
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String create(){	
		ClientDao clientDao = createClientDao();
		try {
			clientDao.add(client);
		} catch (RollbackException e) {
			message = "failed to create new client";
			return "create_client";
		}
		return "client_list";
	}
	
	public String cancel() {
		return "client_list";
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String save(){
		ClientDao clientDao = createClientDao();
		try {
			clientDao.update(client);
		} catch (RollbackException e) {
			message = "failed to update the client";
			return "create_client";
		}
		return "client_list";
	}
	
	protected ClientDao createClientDao(){
		return new ClientDao();
	}
}
