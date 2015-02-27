package backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.PersistenceException;

import model.Bill;
import dao.BillDao;

@ManagedBean(name = "bills")
@RequestScoped
public class BillsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DataModel<Bill> model;
	private Bill bill;

	public BillsBean() {
		model = new ListDataModel<Bill>();
	}

	public DataModel<Bill> getModel() {
		model.setWrappedData(new BillDao().findAll());
		return model;
	}

	public String edit() {
		bill = model.getRowData();

		FacesContext context = FacesContext.getCurrentInstance();
		BillBean bean = (BillBean) context.getApplication()
				.evaluateExpressionGet(context, "#{bill}", BillBean.class);
		bean.setBill(bill);
		bean.setMessage(null);
		return "edit_bill";
	}

	public String delete() {
		bill = model.getRowData();
		BillDao billDao = new BillDao();

		try {
			billDao.remove(bill);
		} catch (PersistenceException e) {
			// ignore
			// TODO: must be message
		}

		return "bill_list";
	}

	public String add() {
		FacesContext context = FacesContext.getCurrentInstance();
		BillBean bean = (BillBean) context.getApplication()
				.evaluateExpressionGet(context, "#{bill}", BillBean.class);
		bean.clean();
		return "create_bill";
	}

}
