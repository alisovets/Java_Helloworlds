package model;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * 
 * class specifying transfer object representing the order item entity
 *
 */
public class CascadeOrderItem {
	private int id;
	private int quantity;
	private Issue issue;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public String getLocaleCost(Locale locale) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(quantity * issue.getPrice());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof CascadeOrderItem)) {
			return false;
		}

		CascadeOrderItem item = (CascadeOrderItem) obj;
		if (id != item.id) {
			return false;
		}
		
		if (quantity != item.quantity) {
			return false;
		}
		
		if (issue != null) {
			if (!issue.equals(item.issue)) {
				return false;
			}
		} else {
			if (item.issue != null) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public int hashCode() {
		int hash = 17 + id;
		hash = hash * 17 + quantity;
		hash = hash * 17 + (issue == null ? 0 : issue.hashCode());
		return hash;
	}

}
