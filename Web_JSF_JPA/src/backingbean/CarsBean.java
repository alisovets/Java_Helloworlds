package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import model.Car;
import dao.CarDao;

@ManagedBean(name = "cars")
@RequestScoped
public class CarsBean implements Serializable {
private static final long serialVersionUID = 1L;
	
	private DataModel<Car> model;
    private Car car;
    
    public CarsBean() {
        model = new ListDataModel<Car>();
    }

	public DataModel<Car> getModel() {
		model.setWrappedData(new CarDao().findAll());
		return model;
	}
	
	public DataModel<Car> getFreeCarModel() {
		model.setWrappedData(new CarDao().findFree());
		return model;
	}
	
	public String select(){
		car = model.getRowData();
		FacesContext context = FacesContext.getCurrentInstance();
		OrderBean bean = (OrderBean) context.getApplication().evaluateExpressionGet(context, "#{order}", OrderBean.class);
		bean.getOrder().setCar(car);
		if(bean.getOrder().getRentOrderId() == 0){
			return "create_order";
		}
		return "edit_order";
	} 
	
	public String cancel() {
		car = model.getRowData();
		FacesContext context = FacesContext.getCurrentInstance();
		OrderBean bean = (OrderBean) context.getApplication().evaluateExpressionGet(context, "#{order}", OrderBean.class);
		if(bean.getOrder().getRentOrderId() == 0){
			return "create_order";
		}
		return "edit_order";
	}

}
