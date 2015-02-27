package model.dao;

import model.Consumer;

public interface ConsumerDao {
	 int delete(int id);
	 int insert(Consumer consumer);
	 Consumer get(int id);
	 Consumer get(String login);
	 Consumer get(String login, String password);
	 
}
