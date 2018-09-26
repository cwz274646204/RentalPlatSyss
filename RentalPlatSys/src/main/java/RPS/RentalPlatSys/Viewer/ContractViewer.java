package RPS.RentalPlatSys.Viewer;


import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.event.TabChangeEvent;
import RPS.RentalPlatSys.Controller.ContractController;
import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.Contract;
import RPS.RentalPlatSys.Entity.User;
import RPS.RentalPlatSys.Util.Util;

@ManagedBean(name = "con")
@SessionScoped
public class ContractViewer {
	private ArrayList<Contract> conList;
	private String location;
	private String age;
	private int price;
	private String customer_detail;
	private String lendeeAddress;
	private String lenderAddress;
	private String lenderSignature;
    private String lendeeSignature;
    private User lendeeProf;
	@Inject ProductController pController;
	@Inject ContractController conController;
	@Inject UserController userController;
	
	public ArrayList<Contract> getConList() {
		conList = (ArrayList<Contract>)conController.getUserCon(Util.getInstance().getUserId());
		return conList;
	}
	
	public void setConList(ArrayList<Contract> conList) {
		this.conList = conList;
	}
	
	public void lendeeSign(int cid,int s) {
		conController.updateLendeeSign(cid, lendeeSignature);
		setConStatus(cid,s);
	}
	
	public void rejectContract(int cid,int s) {
		pController.setNewAvailableDateAndStatus(conController.getConById(cid).getProduct().getId(), 0, new Date(), 0);
		setConStatus(cid,s);
	}
	
	public void finishContract(int cid,int s) {
		pController.addTimeOfProdcut(conController.getConById(cid).getProduct().getId());
		pController.setNewAvailableDateAndStatus(conController.getConById(cid).getProduct().getId(), 0, new Date(), 0);
		setConStatus(cid,s);
	}
	
	public void setConStatus(int cid,int s) {
		conController.setConStatus(cid, s);
	}
	
	public void updateContractWithFirstTime(int id) {
		conController.updateContract(id,age , price, location,customer_detail,lenderSignature);
		conController.setConStatus(id, 2);
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}

	public String getCustomer_detail() {
		return customer_detail;
	}
	
	public void setCustomer_detail(String customer_detail) {
		this.customer_detail = customer_detail;
	}
	
	public void setLendeeAddress(String lendeeAddress) {
		this.lendeeAddress = lendeeAddress;
	}
	
	public String getLendeeAddress(int id) {
		lendeeAddress = userController.findUserProfile(conController.getConById(id).getLendee().getId()).getAddress();
		return lendeeAddress;
	}
	
	public void setLenderAddress(String lenderAddress) {
		this.lenderAddress = lenderAddress;
	}
	
	public String getLenderAddress(int id) {
		lenderAddress = userController.findUserProfile(conController.getConById(id).getLender().getId()).getAddress();
		return lenderAddress;
	}
	
	public void setLenderSignature(String lenderSignature) {
		this.lenderSignature = lenderSignature;
	}
	
	public String getLenderSignature() {
		return lenderSignature;
	}
	
	public void setLendeeSignature(String lendeeSignature) {
		this.lendeeSignature = lendeeSignature;
	}
	
	public String getLendeeSignature() {
		return lendeeSignature;
	}

	public void onTabChange(TabChangeEvent event) {
		String []tmp = event.getTab().getTitle().split("\\s+");
		int id = Integer.valueOf(tmp[tmp.length-1]);
		lendeeProf = userController.findUserProfile(conController.getConById(id).getLendee().getId());
    }
	
	public void setLendeeProf(User lendeeProf) {
		this.lendeeProf = lendeeProf;
	}
	
	public User getLendeeProf() {
		return lendeeProf;
	}

}
