package RPS.RentalPlatSys.Viewer;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import RPS.RentalPlatSys.Controller.ContractController;
import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.Contract;
import RPS.RentalPlatSys.Entity.Product;
import RPS.RentalPlatSys.Entity.User;

@ManagedBean(name = "lease")
@SessionScoped

public class LeaseViewer {
	private Contract lease;
	private User lender;
	private User lendee;
	private Product pro;
	@Inject ContractController conController;
	@Inject UserController userController;
	@Inject ProductController pController;
	
	public void getLease(String id) throws IOException {
		if (id.equals("")==false) {
			lease = conController.getConById(Integer.valueOf(id));
			if (lease==null)  {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			}
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}
	}
	
	public void setLease(Contract lease) {
		this.lease = lease;
	}
	
	public Contract getLease() {
		return lease;
	}

	public void setLender(User lender) {
		this.lender = lender;
	}
	
	public User getLender() {
		lender = userController.findUserProfile(lease.getLender().getId());
		return lender;
	}
	
	public void setLendee(User lendee) {
		this.lendee = lendee;
	}
	
	public User getLendee() {
		lender = userController.findUserProfile(lease.getLendee().getId());
		return lendee;
	}

	public void setPro(Product pro) {
		this.pro = pro;
	}
	
	public Product getPro() {
		pro = pController.findProductById(lease.getProduct().getId());
		return pro;
	}
	

}
