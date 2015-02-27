package model.dao.factory;

import model.dao.AdministratorDao;
import model.dao.CascadeOrderDao;
import model.dao.CascadeOrderItemDao;
import model.dao.ConsumerDao;
import model.dao.IssueDao;
import model.dao.OrderDao;
import model.dao.OrderItemDao;

public abstract class DaoFactory {
	abstract public AdministratorDao getAdministratorDao();
	abstract public ConsumerDao getConsumerDao();
	abstract public IssueDao getIssueDao();
	abstract public OrderDao getOrderDao();
	abstract public OrderItemDao getOrderItemDao();
	abstract public CascadeOrderDao getCascadeOrderDao();
	abstract public CascadeOrderItemDao getCascadeOrderItemDao();
	
}
