package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="index")
@RequestScoped
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String goClientList(){
		return "client_list";
	}
	
	public String goCarList(){
		return "car_list";
	}
	
	public String goOrderList(){
		return "order_list";
	}
	
	public String goAddClient(){
		return "create_client";
	}
	
	public String goBillList(){
		return "bill_list";
	}
	
	public String goIndex(){
		return "index";
	}
	
	
	
	

}
