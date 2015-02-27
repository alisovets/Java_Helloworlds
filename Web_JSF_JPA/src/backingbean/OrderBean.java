package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.RollbackException;

import validator.BackingBeanValidator;
import validator.BackingBeanValidatorException;

import model.OrderStatus;
import model.RentOrder;
import dao.RentOrderDao;

@ManagedBean(name = "order")
@SessionScoped
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 2L;
	RentOrder order = new RentOrder();
	private String message;

	public void clean() {
		order = new RentOrder();
		message = "";
	}

	public RentOrder getOrder() {
		return order;
	}

	public void setOrder(RentOrder order) {
		this.order = order;
	}

	public String getMessage() {
		return message;
	}

	public OrderStatus[] getStatusValues() {
		return OrderStatus.values();
	}

	public String selectCar() {
		return "select_car";
	}

	public String selectClient() {
		return "select_client";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String create() {
		message = "";
		try {
			BackingBeanValidator.validateOrder(order);
		} catch (BackingBeanValidatorException e) {
			message = e.getMessage();
			return "create_order";
		}
		
		RentOrderDao orderDao = new RentOrderDao();
		try {
			orderDao.add(order);
		} catch (RollbackException e) {
			message = "failed to create new order";
			return "create_order";
		}
		return "order_list";
	}

	public String cancel() {
		return "order_list";
	}
	
	public String save() {
		message = "";
		try {
			BackingBeanValidator.validateOrder(order);
		} catch (BackingBeanValidatorException e) {
			message = e.getMessage();
			return "edit_order";
		}
		
		RentOrderDao orderDao = new RentOrderDao();
		try {
			orderDao.update(order);
		} catch (RollbackException e) {
			message = "failed to update the order";
			return "edit_order";
		}
		return "order_list";
	}
}
