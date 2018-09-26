package RPS.RentalPlatSys.Viewer;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import RPS.RentalPlatSys.Controller.ContractController;
import RPS.RentalPlatSys.Controller.LeasePeriodController;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.Contract;
import RPS.RentalPlatSys.Entity.ContractRecord;
import RPS.RentalPlatSys.Entity.User;
import RPS.RentalPlatSys.Util.Util;

@ManagedBean(name = "admin")
@ViewScoped
@SessionScoped
public class AdminViewer {
	private ArrayList<User> users;
	private ArrayList<Contract> con;
	private int shortTerm;
	private int medium;
	private int average;
	private int selConId;
	private ArrayList<ContractRecord> conRec;
	@Inject UserController userController;
	@Inject ContractController conController;
	@Inject LeasePeriodController lpController;
	public ArrayList<User> getUsers() {
		users = (ArrayList<User>)userController.findAllUser();
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public void checkIsAdmin() throws IOException {
		if(userController.findUserProfile(Util.getInstance().getUserId()).getIsAdmin()!=true)
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
	public ArrayList<Contract> getCon() {
		con = (ArrayList<Contract>)conController.getAllCon();
		return con;
	}
	public void setCon(ArrayList<Contract> con) {
		this.con = con;
	}
	public void initLP() {
		lpController.init();
	}
	public int getShortTerm() {
		shortTerm = lpController.findValueByName("short");
		return shortTerm;
	}
	public void setShortTerm(int shortTerm) {
		this.shortTerm = shortTerm;
	}
	public int getMedium() {
		medium = lpController.findValueByName("medium");
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getAverage() {
		average = lpController.findValueByName("average");
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public void updateTerm() {
		lpController.updateTerm(shortTerm, medium, average);
	}
	public ArrayList<ContractRecord> getConHis() {
		conRec = (ArrayList<ContractRecord>)conController.getConRecord(selConId);
		return conRec;
		
	}
	public void setConRec(ArrayList<ContractRecord> conRec) {
		this.conRec = conRec;
	}
	public int getSelConId() {
		return selConId;
	}
	public void setSelConId(SelectEvent event) {
		this.selConId = ((Contract)event.getObject()).getId();
	}
}
