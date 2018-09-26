package RPS.RentalPlatSys.Viewer;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.User;
import RPS.RentalPlatSys.Util.Util;

@ManagedBean(name = "userLogin")
@SessionScoped

public class UserLoginViewer {
	private String email;
	private String password;
	@Inject UserController userController;
	public void login() throws IOException {
			User u = userController.login(email, password);
			if(u!=null) {
				HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("userid",u.getId());
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR: wrong username or password",""));
			}
	}
	
	public void logout() {
		Util.getInstance().logout();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void handleEvent() {
		try {
			if(!Util.getInstance().isLogin())
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
