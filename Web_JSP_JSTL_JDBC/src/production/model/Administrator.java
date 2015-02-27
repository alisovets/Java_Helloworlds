package model;

/**
 * 
 * class specifying transfer object representing the administrator entity
 *
 */
public class Administrator {

	private int id;
	private String login;
	private String password;
	private String name;

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

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Administrator)) {
			return false;
		}

		Administrator admin = (Administrator) obj;
		if (id != admin.id) {
			return false;
		}
		
		if (login != null) {
			if (!login.equals(admin.login)) {
				return false;
			}
		} else {
			if (admin.login != null) {
				return false;
			}
		}
		
		if (password != null) {
			if (!password.equals(admin.password)) {
				return false;
			}
		} else {
			if (admin.password != null) {
				return false;
			}
		}

		if (name != null) {
			if (!name.equals(admin.name)) {
				return false;
			}
		} else {
			if (admin.name != null) {
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
		return hash;
	}
}
