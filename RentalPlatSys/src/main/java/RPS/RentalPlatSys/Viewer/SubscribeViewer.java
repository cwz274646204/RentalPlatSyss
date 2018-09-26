package RPS.RentalPlatSys.Viewer;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import  RPS.RentalPlatSys.Controller.SubscriberController;
import  RPS.RentalPlatSys.Entity.Product;
import  RPS.RentalPlatSys.Entity.Subscriber;
import  RPS.RentalPlatSys.Util.Util;

@ManagedBean(name = "sub")
@ViewScoped
@SessionScoped

public class SubscribeViewer {
	private String keyword;
	private ArrayList<Subscriber> keywords;
	private ArrayList<Product> subProduct;
	
	@Inject SubscriberController subController;
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void saveSub() {
		subController.saveSub(Util.getInstance().getUserId(),keyword);
	}

	public void delSub(int id) {
		subController.delSub(id);
	}

	public void setKeywords(ArrayList<Subscriber> keywords) {
		this.keywords = keywords;
	}

	public ArrayList<Subscriber> getKeywords() {
		keywords = (ArrayList<Subscriber>) subController.findAllSub(Util.getInstance().getUserId());
		return keywords;
	}

	public void setSubProduct(ArrayList<Product> subProduct) {
		this.subProduct = subProduct;
	}
	
	public ArrayList<Product> getSubProduct() {
		subProduct = (ArrayList<Product>)subController.findSubProdcut(Util.getInstance().getUserId());
		return subProduct;
	}

}
