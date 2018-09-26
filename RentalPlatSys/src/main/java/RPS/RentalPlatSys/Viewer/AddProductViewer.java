package RPS.RentalPlatSys.Viewer;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Util.Util;

@ManagedBean(name = "addProduct")
@ViewScoped
@SessionScoped
public class AddProductViewer {
	private String name;
	private int price;
	private String description;
	private boolean insurance;
	private InputStream stream;
	@Inject ProductController productController;
	public void saveFile(FileUploadEvent event) throws IOException {
		 stream = event.getFile().getInputstream();
    }
	public void addProduct() throws IOException {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if (stream!=null) {
			String filename = Util.getInstance().submit(stream,"/upload_product/");
			productController.addProduct(name,price,description,insurance, (Integer)session.getAttribute("userid"),filename);
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OMG", "Please upload an product p-h-o-t-o"));
		}
	}    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isInsurance() {
		return insurance;
	}
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
}
