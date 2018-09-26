package RPS.RentalPlatSys.Test;

import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Controller.SubscriberController;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.Product;
import RPS.RentalPlatSys.Entity.Subscriber;

@RunWith(Arquillian.class)
public class SubscriberTest extends MainTest {
	
	@Inject SubscriberController sc;
	@Inject UserController uc;
	@Inject ProductController pc;
	
	@Before
	public void init() {
	   uc.reg(user.getEmail(), user.getPassword(), user.getAddress(), user.getName(), user.getPhone());
	   u = uc.login(user.getEmail(), user.getPassword());
	}
	
	@Test
	public void testSaveSub() {
		sc.saveSub(u.getId(), "bike");
		List<Subscriber> s = sc.findAllSub(u.getId());
		Assert.assertTrue(!s.isEmpty());
	}
	
	@Test
	public void testDelSub() {
		sc.saveSub(u.getId(), "bike");
		List<Subscriber> s = sc.findAllSub(u.getId());
		sc.delSub(s.get(0).getId());
		s = sc.findAllSub(u.getId());
		Assert.assertTrue(s.isEmpty());
	}
	
	@Test
	public void testFindAllSub() {
		sc.saveSub(u.getId(), "bike");
		List<Subscriber> s = sc.findAllSub(u.getId());
		Assert.assertTrue(!s.isEmpty());
	}
	
	@Test
	public void testFindSubProdcut() {
		sc.saveSub(u.getId(), "bike");
		pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		List<Product>p = sc.findSubProdcut(u.getId());
		Assert.assertTrue(!p.isEmpty());
	}
	
	@Test
	public void testUpdate() {
		sc.saveSub(u.getId(), "bike");
		pc.addProduct("bike", 2, "sss", true, u.getId(), "bike.jpg");
		List<Product>p = sc.findSubProdcut(u.getId());
		Assert.assertTrue(!p.isEmpty());
	}
	
}
