package RPS.RentalPlatSys.Test;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import RPS.RentalPlatSys.Controller.ContractController;
import RPS.RentalPlatSys.Controller.ProductController;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.Contract;
import RPS.RentalPlatSys.Entity.ContractRecord;
import RPS.RentalPlatSys.Entity.Product;
import RPS.RentalPlatSys.Entity.User;

@RunWith(Arquillian.class)
public class ContractTest extends MainTest {

	@Inject UserController uc;
	@Inject ProductController pc;
	@Inject ContractController cc;
	
	private User lender;
	private User lendee;
	private Product p;
	
	@Before
	public void init() {
		uc.reg("cwz274646204@gmail.com", "ccc274646204", "PAVIA", "Weizheng Chen", "332658575");
		uc.reg("ccc274646204@qq.com", "cwz274646204", "PAVIA", "Weizheng Chen", "332755465");
		lender = uc.login("cwz274646204@gmail.com", "ccc274646204");
		lendee = uc.login("ccc274646204@qq.com", "cwz274646204");
		p = pc.addProduct("bike", 2, "sss", true, lender.getId(), "bike.jpg");
		
	}
	@Test
	public void testAddContract() {
		cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		List<Contract> c = cc.getUserCon(lender.getId());
		
		Assert.assertTrue(c!=null);
	}
	@Test
	public void testGetUserCon() {
		cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		List<Contract> c = cc.getUserCon(lender.getId());
		
		Assert.assertTrue(c!=null);
	}
	@Test
	public void testGetAllCon() {
		cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		List<Contract> c = cc.getAllCon();
		
		Assert.assertTrue(c!=null);
	}
	@Test
	public void testGetConById() {
		Contract c = cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		Contract cs = cc.getConById(c.getId());
		
		Assert.assertTrue(cs!=null);
	}
	@Test
	public void testSetConStatus() {
		Contract c = cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		cc.setConStatus(c.getId(), 1);
		Contract cs = cc.getConById(c.getId());
		
		Assert.assertTrue(cs.getStatus()==1);
	}
	@Test
	public void testUpdateContract() {
		Contract c = cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		cc.updateContract(c.getId(), "23", 30, "Italy", "acc", "HI TEST");
		Contract cs = cc.getConById(c.getId());
		
		Assert.assertTrue(cs.getAge().equals("23"));
		Assert.assertTrue(cs.getFinalprice()==30);
		Assert.assertTrue(cs.getLocation().equals("Italy"));
		Assert.assertTrue(cs.getCustomer_detail().equals("acc"));
		Assert.assertTrue(cs.getLenderSignature().equals("HI TEST"));
	}
	@Test
	public void testUpdateLendeeSign() {
		Contract c = cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		cc.updateLendeeSign(c.getId(), "wow");
		Contract cs = cc.getConById(c.getId());
		
		Assert.assertTrue(cs.getLendeeSignature().equals("wow"));
	}
	@Test
	public void testGetConHistory() {
		Contract c = cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		List<ContractRecord> cr= cc.getConRecord(c.getId());
		
		Assert.assertTrue(cr!=null);
	}
	@Test
	public void testIsInTheSameContract() {
		cc.addContract(p.getId(), lender.getId(), 10, new Date(), lendee.getId());
		
		Boolean result1 = cc.isInTheSameContract(lender.getId(), lendee.getId());
		Boolean result2 = cc.isInTheSameContract(lendee.getId(), lender.getId());
		Boolean result3 = cc.isInTheSameContract(lender.getId(), lender.getId());
		
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
		Assert.assertFalse(result3);
	}
}
