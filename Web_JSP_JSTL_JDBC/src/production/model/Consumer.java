package model;


/**
 * 
 * class specifying transfer object representing the consumer entity
 *
 */
public class Consumer {
	private int id;
	private String login;
	private String password;
	private String name;
	private String address;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Consumer)) {
			return false;
		}

		Consumer consumer = (Consumer) obj;
		if (id != consumer.id) {
			return false;
		}
		
		if (login != null) {
			if (!login.equals(consumer.login)) {
				return false;
			}
		} else {
			if (consumer.login != null) {
				return false;
			}
		}
		
		if (password != null) {
			if (!password.equals(consumer.password)) {
				return false;
			}
		} else {
			if (consumer.password != null) {
				return false;
			}
		}

		if (name != null) {
			if (!name.equals(consumer.name)) {
				return false;
			}
		} else {
			if (consumer.name != null) {
				return false;
			}
		}
		
		if (address != null) {
			if (!address.equals(consumer.address)) {
				return false;
			}
		} else {
			if (consumer.address != null) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 17 + id;
		hash = hash * 17 + (login == null ? 0 : login.hashCode());
		hash = hash * 17 + (password == null ? 0 : password.hashCode());
		hash = hash * 17 + (name == null ? 0 : name.hashCode());
		hash = hash * 17 + (address == null ? 0 : address.hashCode());
		return hash;
	}
	
}
