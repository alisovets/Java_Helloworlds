package validator;

import model.Bill;
import model.RentOrder;


public class BackingBeanValidator {

	public static void validateOrder(RentOrder order) throws BackingBeanValidatorException {
		if( order.getClient() == null){	
			throw new BackingBeanValidatorException("client is not selected");
		}
		
		if(order.getCar() == null){	
			throw new BackingBeanValidatorException("car is not selected");
		}
		
		if((order.getEndDate() != null) && (order.getStartDate().getTime() > order.getEndDate().getTime() )){
			throw new BackingBeanValidatorException("the end date must be more then the start date");
		}	
	}
	
	public static void validateBill(Bill bill) throws BackingBeanValidatorException {
		if( bill.getRentOrder() == null){	
			throw new BackingBeanValidatorException("order is not selected");
		}
			
	}
	
}
