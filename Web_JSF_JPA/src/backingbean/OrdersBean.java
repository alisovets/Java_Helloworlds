package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import model.RentOrder;
import dao.RentOrderDao;

@ManagedBean(name = "orders")
@RequestScoped
public class OrdersBean implements Serializable {
private static final long serialVersionUID = 1L;
	
	private DataModel<RentOrder> model;
    private RentOrder order;
    
    public OrdersBean() {
        model = new ListDataModel<RentOrder>();
    }

	public DataModel<RentOrder> getModel() {
		model.setWrappedData(new RentOrderDao().findAll());
		return model;
	}
	
	
	public String edit(){
		order = model.getRowData();

		FacesContext context = FacesContext.getCurrentInstance();
		OrderBean bean = (OrderBean) context.getApplication().evaluateExpressionGet(context, "#{order}", OrderBean.class);
		bean.clean();
		bean.setOrder(order);
		return "edit_order";
	}
	
	
	public String delete(){
		order = model.getRowData();
		RentOrderDao orderDao = new RentOrderDao();
		try{
			orderDao.remove(order);
		} catch (PersistenceException e) {
			// ignore
		}
		
		return "order_list";
	}
	
	public String select(){
		order = model.getRowData();
		FacesContext context = FacesContext.getCurrentInstance();
		BillBean bean = (BillBean) context.getApplication().evaluateExpressionGet(context, "#{bill}", BillBean.class);
		bean.getBill().setRentOrder(order);
		if(bean.getBill().getBillId() == 0){
			return "create_bill";
		}
		return "edit_bill";
	} 
	
	public String cancel() {
		return "edit_bill";
	}
	
	public String add(){
		FacesContext context = FacesContext.getCurrentInstance();
		OrderBean bean = (OrderBean) context.getApplication().evaluateExpressionGet(context, "#{order}", OrderBean.class);
		bean.clean();
		return "create_order";
	}

}
