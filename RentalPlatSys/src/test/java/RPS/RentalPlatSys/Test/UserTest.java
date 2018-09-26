package RPS.RentalPlatSys.Test;


import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import RPS.RentalPlatSys.Controller.UserController;
import RPS.RentalPlatSys.Entity.User;

@RunWith(Arquillian.class)
public class UserTest extends MainTest {
	
	@Inject UserController uc;
	@Before
	
	public void init() {
	   uc.reg(user.getEmail(), user.getPassword(), user.getAddress(), user.getName(), user.getPhone());
	}
	
	@Test
	
	public void testReg() {
		User u =  uc.reg(user.getEmail(), user.getPassword(), user.getAddress(), user.getAddress(), user.getPhone());
		Assert.assertTrue(u==null);
		u =uc.reg("cwz274646204@gmail.com", "ccc274646204", "PAVIA", "Weizheng Chen", "332658575");
		Assert.assertTrue(u!=null);
	}
	
	@Test
	
	public void testLogin() {
		User u = uc.login(user.getEmail(), user.getPassword());
		Assert.assertTrue(u!=null);
	}
	
	@Test
	
	public void testUpdateProfile() {
		User u = uc.login(user.getEmail(), user.getPassword());
		uc.updateProfile(u.getId(), "China", "Weizheng Chen", "13222222222");
		User u1 = uc.findUserProfile(u.getId());
		Assert.assertTrue(u1.getAddress().equals("China"));
		Assert.assertTrue(u1.getName().equals("Weizheng Chen"));
		Assert.assertTrue(u1.getPhone().equals("13222222222"));
	}
	
	@Test
	
	public void testUpdateCred() {
		User u = uc.login(user.getEmail(), user.getPassword());
		uc.updateCred(u.getId(), "Weizheng.jpg");
		User u1 = uc.findUserProfile(u.getId());
		
		Assert.assertTrue(u1.getCredential().equals("Weizheng.jpg"));
	}
	
	@Test
	
	public void testFindUserProfile() {
		User u = uc.login(user.getEmail(), user.getPassword());
		User u1 = uc.findUserProfile(u.getId());
		Assert.assertTrue(u1.getEmail().equals(user.getEmail()));
		Assert.assertTrue(u1.getAddress().equals(user.getAddress()));
		Assert.assertTrue(u1.getName().equals(user.getName()));
		Assert.assertTrue(u1.getPhone().equals(user.getPhone()));
	}
	
	@Test
	
	public void testFindAllUser() {
		uc.reg("cwz274646204@gmail.com", "ccc274646204", "PAVIA", "Weizheng Chen", "332658575");
		uc.reg("ccc274646204@qq.com", "cwz274646204", "PAVIA", "Weizheng Chen", "332658575");
		uc.reg("zzz274646204@163.com", "zcw274646204", "PAVIA", "Weizheng Chen", "332658575");
		List<User> u = uc.findAllUser();
		Assert.assertTrue(!u.isEmpty());
	}
	
	@Test
	
	public void testFindByCred() {
		uc.reg(user.getEmail(), user.getPassword(), user.getAddress(), user.getName(), user.getPhone());
		User loginUser = uc.login(user.getEmail(), user.getPassword());
		loginUser.setCredential("Weizheng");
		User testUser = uc.findByCred("Weizheng");
		
		Assert.assertTrue(testUser.getEmail().equals(user.getEmail()));
	}
}

