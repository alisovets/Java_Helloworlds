package model;

/**
 * 
 * class specifying transfer object representing the order item entity
 *
 */
public class OrderItem {
	private int id;
	private int orderId;
	private int issueId;
	private int quantity; 
	
	public OrderItem(){};
	public OrderItem(int id, int orderId, int issueId, int quantity){
		this.id = id;
		this.orderId = orderId;
		this.issueId = issueId;
		this.quantity = quantity;
	};

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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
		
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof OrderItem)) {
			return false;
		}

		OrderItem item = (OrderItem) obj;
		if (id != item.id) {
			return false;
		}
		
		if (orderId != item.orderId) {
			return false;
		}
		
		if (issueId != item.issueId) {
			return false;
		}
		
		if (quantity != item.quantity) {
			return false;
		}
		
		return true;
	}
			
	@Override
	public int hashCode() {
		int hash = 17 + id;
		hash = hash * 17 + orderId;
		hash = hash * 17 + issueId;
		hash = hash * 17 + quantity;
		return hash;
	}		
		
}
