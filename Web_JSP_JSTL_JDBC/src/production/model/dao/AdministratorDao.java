package model.dao;

import model.Administrator;


public interface AdministratorDao {
	
	Administrator get(int id);
	
	Administrator get(String login);
	
	Administrator get(String login, String password);
	
}
