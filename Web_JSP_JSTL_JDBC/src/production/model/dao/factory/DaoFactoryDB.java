package model.dao.factory;

import model.dao.AdministratorDao;
import model.dao.CascadeOrderItemDao;
import model.dao.ConsumerDao;
import model.dao.IssueDao;
import model.dao.OrderDao;
import model.dao.OrderItemDao;
import model.dao.impl.AdministratorDaoImpl;
import model.dao.impl.CascadeOrderDaoImpl;
import model.dao.impl.CascadeOrderItemDaoImpl;
import model.dao.impl.ConsumerDaoImpl;
import model.dao.impl.IssueDaoImpl;
import model.dao.impl.OrderDaoImpl;
import model.dao.impl.OrderItemDaoImpl;

public class DaoFactoryDB extends DaoFactory {

	@Override
	public AdministratorDao getAdministratorDao() {
		return new AdministratorDaoImpl();
	}

	@Override
	public ConsumerDao getConsumerDao() {
		return new ConsumerDaoImpl();
	}

	@Override
	public IssueDao getIssueDao() {
		return new IssueDaoImpl();
	}

	@Override
	public OrderDao getOrderDao() {
		return new OrderDaoImpl();
	}

	@Override
	public OrderItemDao getOrderItemDao() {
		return new OrderItemDaoImpl();
	}

	@Override
	public CascadeOrderDaoImpl getCascadeOrderDao() {
		return new CascadeOrderDaoImpl();
	}

	@Override
	public CascadeOrderItemDao getCascadeOrderItemDao() {
		return new CascadeOrderItemDaoImpl();
	}

}
