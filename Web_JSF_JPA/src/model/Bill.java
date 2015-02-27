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
 * The persistent class for the bill database table.
 * 
 */
@Entity
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BILL_ID")
	private int billId;

	private int approved;

	@Temporal(TemporalType.DATE)
	@Column(name="PAY_DATE")
	private Date payDate;

	private BillStatus status;

	private int summ;

	//bi-directional many-to-one association to Rentorder
	@ManyToOne
	@JoinColumn(name="RENTORDER_ID")
	private RentOrder rentOrder;

	public Bill() {
	}

	public int getBillId() {
		return this.billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getApproved() {
		return this.approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public BillStatus getStatus() {
		return this.status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public int getSumm() {
		return this.summ;
	}

	public void setSumm(int summ) {
		this.summ = summ;
	}

	public RentOrder getRentOrder() {
		return this.rentOrder;
	}

	public void setRentOrder(RentOrder rentOrder) {
		this.rentOrder = rentOrder;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		Bill bill = (Bill) obj;
		
		if(!rentOrder.equals(bill.rentOrder)){
			return false;
		}
		
		if(!payDate.equals(bill.payDate)){
			return false;
		}
		
		if(summ != bill.summ){
			return false;
		}
		
		return true;		
	}

	@Override
	public int hashCode() {
		int hash = 17 + summ;
		hash = hash * 17 +  rentOrder.hashCode();
		return hash * 17 + rentOrder.hashCode() + payDate.hashCode();  
	}

} 