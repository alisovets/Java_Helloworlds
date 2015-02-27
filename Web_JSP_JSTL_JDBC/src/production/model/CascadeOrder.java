package model;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * class specifying transfer object representing the order entity 
 *
 */
public class CascadeOrder extends Order{
	
	private List<CascadeOrderItem> orderItems;
	
	public CascadeOrder() {
	}
	
	public CascadeOrder(int id, int customerId, int subscriptYear, Date createDate, Date paidDate, List<CascadeOrderItem> orderItems) {
		super(id, customerId, subscriptYear, createDate, paidDate);
		this.orderItems = orderItems;  
	}
	

	public List<CascadeOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<CascadeOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public String getLocaleFullCost(Locale locale){
		double fullCost = 0;
		for(CascadeOrderItem item: orderItems){
			fullCost += item.getQuantity() * item.getIssue().getPrice();
		}
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(fullCost);
	}
	
	
	@Override
	public int hashCode() {
		int hash = (orderItems == null ? 0 : orderItems.hashCode());
		return hash * 17 + super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof CascadeOrder)) {
			return false;
		}

		CascadeOrder order = (CascadeOrder) obj;
		
		if (orderItems != null) {
			if (!orderItems.equals(order.orderItems)) {
				return false;
			}
		} else {
			if (order.orderItems != null) {
				return false;
			}
		}
		return super.equals(obj);
	}
	

	
}
