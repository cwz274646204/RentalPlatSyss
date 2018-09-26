package RPS.RentalPlatSys.Test;

import java.io.File;

import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import RPS.RentalPlatSys.Entity.User;
import RPS.RentalPlatSys.Util.Util;
 
@RunWith(Arquillian.class)
public class MainTest {
 
	Logger log = Logger.getLogger(MainTest.class);
	protected User user;
	protected User u;
	@Deployment
	public static Archive<?> createDeployment() {
		  File[] files = Maven.resolver().loadPomFromFile(Util.getProjectpompath())
			        .importRuntimeDependencies().resolve().withTransitivity().asFile();
		  WebArchive archive = ShrinkWrap.create(WebArchive.class, "TestRentalPlatSys.war")
			        .addPackages(true, "RPS.RentalPlatSys.Controller")
			        .addPackages(true, "RPS.RentalPlatSys.Dao")
			        .addPackages(true, "RPS.RentalPlatSys.Entity")
			        .addPackages(true, "RPS.RentalPlatSys.Util")
			        .addPackages(true, "mRPS.RentalPlatSys.Viewer")
			        .addPackages(true, "RPS.RentalPlatSys.Test")
			        .addAsResource("META-INF/persistence.xml")
			        .addAsLibraries(files)
			        .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
					return archive;
	}
	@Test
	public void testEnv() throws Exception {
	     Assert.assertTrue(true);
	}
	@Inject
	UserTransaction utx;
	
	@Before
	public void startTransaction() throws Exception {
	    utx.begin();
	    user = new User();
	    user.setAddress("Pavia");
	    user.setCredential("Weizheng.jpg");
	    user.setEmail("cwz274646204@gmail.com");
	    user.setName("Weizheng Chen");
	    user.setPassword("ccc274646204");
	    user.setPhone("336205822555");
	}
	@After
	public void rollbackTransaction() throws Exception {
	    utx.rollback();
	}
	
}