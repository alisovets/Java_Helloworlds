package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the rentorder database table.
 * 
 */
@Entity
public class RentOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RENTORDER_ID")
	private int rentOrderId;

	private int cost;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	private OrderStatus status;

	@ManyToOne
	@JoinColumn(name="CAR_ID")
	private Car car;

	@ManyToOne
	@JoinColumn(name="CLIENT_ID")
	private Client client;

	public RentOrder() {
	}

	public int getRentOrderId() {
		return this.rentOrderId;
	}

	public void setRentOrderId(int rentOrderId) {
		this.rentOrderId = rentOrderId;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		RentOrder order = (RentOrder) obj;
		if (!client.equals(order.client)) {
			return false;
		}
		
		if(!car.equals(order.car)){
			return false;
		}
		
		if(!startDate.equals(order.startDate)){
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return client.hashCode() ^ car.hashCode() ^ startDate.hashCode();
	}
	
}