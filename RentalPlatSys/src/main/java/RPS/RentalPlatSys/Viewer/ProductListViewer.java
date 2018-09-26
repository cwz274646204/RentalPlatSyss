package RPS.RentalPlatSys.Viewer;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Entity.Product;

@ManagedBean(name = "productList")
@ViewScoped
@SessionScoped

public class ProductListViewer {
	private ArrayList<Product> productList;
	private String keyword;
	private int id;
	private int userid;
	@Inject ProductController productController;
	
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	
	public void getProductAllList() {
		if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) { 
			return;
		} else {
			productList = (ArrayList<Product>)productController.getAllProducts();
		}
	}
	
	public void getProductListByKeyword() {
		this.productList = (ArrayList<Product>)productController.searchProducts(keyword);
	}
	
	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getUserid() {
		return userid;
	}

}
