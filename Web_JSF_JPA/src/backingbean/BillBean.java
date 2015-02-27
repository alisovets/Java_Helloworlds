package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.RollbackException;

import validator.BackingBeanValidator;
import validator.BackingBeanValidatorException;

import model.Bill;
import model.BillStatus;
import dao.BillDao;

@ManagedBean(name = "bill")
@SessionScoped
public class BillBean implements Serializable {
	
	private static final long serialVersionUID = 2L;

	private Bill bill = new Bill();
	
	private String message;
	
		
	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public boolean getApproved(){
		if(bill.getApproved() == 1)
			return true;
		return false;
	} 
	
	public void setApproved(boolean approved){
		if(approved){
			bill.setApproved(1);
		}
		else{
			bill.setApproved(0);
		}
	}
	
	public String getMessage() {
		return message;
	}

	public BillStatus[] getStatusValues(){
		return BillStatus.values();
	}
	
	public String selectCar(){
		return "select_car";
	}
	
	public String selectClient(){
		return "select_client";
	}
	
	public String create(){
		message = "";
		try {
			BackingBeanValidator.validateBill(bill);
		} catch (BackingBeanValidatorException e) {
			message = e.getMessage();
			return "create_bill";
		}
		
		BillDao billDao = new BillDao();
		try {
			billDao.add(bill);
		} catch (RollbackException e) {
			message = "failed to create new bill";
			return "create_bill";
		}
		return "bill_list";
	}
	
	public String cancel() {
		return "bill_list";
	}
	
	public String save(){
		message = "";
		try {
			BackingBeanValidator.validateBill(bill);
		} catch (BackingBeanValidatorException e) {
			message = e.getMessage();
			return "edit_bill";
		}
		
		BillDao billDao = new BillDao();
		try {
			billDao.update(bill);
		} catch (RollbackException e) {
			message = "failed to update the bill";
			return "edit_bill";
		}
		message = "saved";	
		return "bill_list";
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String selectOrder(){
		return "select_order";
	}
	
	public void clean(){
		bill = new Bill();
		message = null;
	}
	
	
}
