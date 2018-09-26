package RPS.RentalPlatSys.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import RPS.RentalPlatSys.DAO.ProductDao;
import RPS.RentalPlatSys.Entity.Product;

@Stateless

public class ProductController {
	@Inject ProductDao productdao;
	@Inject SubscriberController sController;
	
	public List<Product> getAllProducts() {
		return productdao.findAllProducts();
	}
	
	public List<Product> searchProducts(String keyword) {
		return productdao.findProductsByKeyword(keyword);
	}
	
	public Product addProduct(String name,int price, String description, boolean insurance, int userid, String photo) {
		Product p = productdao.createProduct(description, insurance, name, price, userid, photo);
		sController.update(p.getId());
		return p;
	}
	
	public Product findProductById(int id) {
		return productdao.findProductById(id);
	}
	
	public void setNewAvailableDateAndStatus(int id,int term,Date date,int status) {
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(date);
		tmp.add(Calendar.DAY_OF_YEAR, term);
		productdao.updateAvailableDateAndStatus(id,tmp.getTime(),status);		
	}
	
	public void addTimeOfProdcut(int id) {
		productdao.updateTime(id);
	}
	
}
