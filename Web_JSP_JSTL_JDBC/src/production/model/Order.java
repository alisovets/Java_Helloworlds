package model;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;

/**
 * 
 * class specifying transfer object representing the order entity
 *
 */
public class Order {
	protected int id;
	protected int consumerId;
	protected int subscriptYear;
	protected Date createDate;
	protected Date paidDate;
	
	public Order(){
	}
	
	public Order(int id, int customerId, int subscriptYear, Date createDate, Date paidDate){
		this.id = id;
		this.consumerId = customerId;
		this.subscriptYear = subscriptYear;
		this.createDate = createDate;
		this.paidDate = paidDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}

	public int getSubscriptYear() {
		return subscriptYear;
	}

	public void setSubscriptYear(int subscriptYear) {
		this.subscriptYear = subscriptYear;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
	public String getLocaleCreateDate(Locale locale) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
		return dateFormat.format(createDate);
	}

	public String getLocalePaidDate(Locale locale) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
		return dateFormat.format(paidDate);
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Order)) {
			return false;
		}

		Order order = (Order) obj;
		if (id != order.id) {
			return false;
		}
		
		if (consumerId != order.consumerId) {
			return false;
		}
		
		if (subscriptYear != order.subscriptYear) {
			return false;
		}
		
		if (createDate != null) {
			if (!createDate.equals(order.createDate)) {
				return false;
			}
		} else {
			if (order.createDate != null) {
				return false;
			}
		}
		
		if (paidDate != null) {
			if (!paidDate.equals(order.paidDate)) {
				return false;
			}
		} else {
			if (order.paidDate != null) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 17 + id;
		hash = hash * 17 + consumerId;
		hash = hash * 17 + subscriptYear;
		hash = hash * 17 + (createDate == null ? 0 : createDate.hashCode());
		hash = hash * 17 + (paidDate == null ? 0 : paidDate.hashCode());
		return hash;
	}
}
