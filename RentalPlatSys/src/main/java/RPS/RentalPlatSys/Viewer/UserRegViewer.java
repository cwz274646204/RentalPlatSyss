package RPS.RentalPlatSys.Viewer;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import RPS.RentalPlatSys.Controller.UserController;

@ManagedBean(name = "userReg")
@SessionScoped

public class UserRegViewer {
	@Inject UserController userController;
	private String name;
	private String password;
	private String address;
	private String email;
	private String phone;
	
	public void reg() throws IOException {
		if(userController.reg(email, password, address, name, phone)!=null) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR: register failed!The same email",""));
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}

}
