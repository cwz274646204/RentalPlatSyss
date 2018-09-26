package RPS.RentalPlatSys.Viewer;

import java.io.IOException;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import RPS.RentalPlatSys.Controller.ContractController;
import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Entity.Product;
import RPS.RentalPlatSys.Util.Util;

@ManagedBean(name = "product")
@ViewScoped
@SessionScoped

public class ProductViewer {
	private Product p;
	private Date startRentingdate;
	private int term;
	@Inject ProductController pController;
	@Inject ContractController contractController;
	public void getProduct(String id) throws IOException { 
		if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) { 
			return;
		} else {
			if (id.equals("")==false) {
				p = pController.findProductById(Integer.valueOf(id));
				if (p==null) FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} else {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			}
		}
	}
	
	public void setP(Product p) {
		this.p = p;
	}
	
	public Product getP() {
		return p;
	}

	public void rentProduct(String pid, String uid) {
		if (startRentingdate!=null&&term!=0) {
			if(startRentingdate.before(p.getAvailableDate())==true) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Oh!!!", "Your choice must after the available date!"));
				
			} else {
			
				pController.setNewAvailableDateAndStatus(Integer.valueOf(pid), term, startRentingdate,1);
				contractController.addContract(Integer.valueOf(pid),Integer.valueOf(uid),term,startRentingdate,Util.getInstance().getUserId());
				RequestContext.getCurrentInstance().execute("alert('rent successful');PF('rentDlg').hide()");
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR: you should input",""));
		}
	}
	
	public void setStartRentingdate(Date startRentingdate) {
		this.startRentingdate = startRentingdate;
	}
	
	public Date getStartRentingdate() {
		return startRentingdate;
	}

	public void setTerm(int term) {
		this.term = term;
	}
	
	public int getTerm() {
		return term;
	}

}
