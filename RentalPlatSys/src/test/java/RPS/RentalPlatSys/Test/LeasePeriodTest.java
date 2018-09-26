package RPS.RentalPlatSys.Test;

import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import RPS.RentalPlatSys.Controller.LeasePeriodController;

@RunWith(Arquillian.class)
public class LeasePeriodTest extends MainTest {
	@Inject LeasePeriodController lpc;
	
	@Test
	public void testInit() {
		lpc.init();
		
		Assert.assertTrue(lpc.findValueByName("short")==10);
		Assert.assertTrue(lpc.findValueByName("medium")==20);
		Assert.assertTrue(lpc.findValueByName("average")==30);
	}
	
	@Test
	public void testUpdateTerm() {
		lpc.init();
		lpc.updateTerm(20, 40, 60);
		
		Assert.assertTrue(lpc.findValueByName("short")==20);
		Assert.assertTrue(lpc.findValueByName("medium")==40);
		Assert.assertTrue(lpc.findValueByName("average")==60);
	}
	
	@Test
	public void testFindValueByName() {
		lpc.init();
		
		Assert.assertTrue(lpc.findValueByName("short")==10);
		Assert.assertTrue(lpc.findValueByName("medium")==20);
		Assert.assertTrue(lpc.findValueByName("average")==30);
	}
}
