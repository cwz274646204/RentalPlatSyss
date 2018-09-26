package RPS.RentalPlatSys.Test;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.Product;

@RunWith(Arquillian.class)

public class ProductTest extends MainTest {
	@Inject UserController uc;
	@Inject ProductController pc;
	@Before
	
	public void init() {
	   uc.reg(user.getEmail(), user.getPassword(), user.getAddress(), user.getName(), user.getPhone());
	   u = uc.login(user.getEmail(), user.getPassword());
	}
	
	@Test
	
	public void testGetAllProducts() {
		pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		List<Product> p  = pc.getAllProducts();
		Assert.assertTrue(!p.isEmpty());
	}
	
	@Test
	
	public void testSearchProducts() {
		pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		List<Product> p  = pc.searchProducts("byt");
		Assert.assertTrue(!p.isEmpty());
	}
	
	@Test
	
	public void testAddProduct() {
		pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		List<Product> p  = pc.getAllProducts();
		Assert.assertTrue(!p.isEmpty());
	}
	
	@Test
	
	public void testFindProductById() {
		Product p = pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		Product pFind = pc.findProductById(p.getId());
		Assert.assertTrue(pFind!=null);
	}
	
	@Test
	
	public void testSetNewAvailableDateAndStatus() {
		Product p = pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		pc.setNewAvailableDateAndStatus(p.getId(), 10, new Date(), 0);
		Product pFind = pc.findProductById(p.getId());
		Assert.assertTrue(pFind.getStatus()==0);
		Assert.assertTrue(pFind.getAvailableDate()!=null);
	}
	
	@Test
	
	public void testAddTimeOfProdcut() {
		Product p = pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		pc.addTimeOfProdcut(p.getId());
		Product pFind = pc.findProductById(p.getId());
		Assert.assertTrue(pFind.getTimes()==1);
	}
	
}
