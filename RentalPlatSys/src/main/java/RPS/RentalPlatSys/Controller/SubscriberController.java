package RPS.RentalPlatSys.Controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import RPS.RentalPlatSys.DAO.ProductDao;
import RPS.RentalPlatSys.DAO.SubscribeNotifyDao;
import RPS.RentalPlatSys.DAO.SubscriberDao;
import RPS.RentalPlatSys.Entity.Product;
import RPS.RentalPlatSys.Entity.Subscriber;

@Stateless

public class SubscriberController {
	@Inject SubscriberDao subDao;
	@Inject ProductDao pDao;
	@Inject SubscribeNotifyDao snDao;
	
	public void saveSub(int id, String name) {
		subDao.createSubscribe(id, name);
	}
	
	public void delSub(int id) {
		subDao.delSubById(id);
	}
	
	public List<Subscriber> findAllSub(int id) {
		return subDao.findSubByUserId(id);
	}
	
	public void update(int pid) {
		String pname = pDao.findProductById(pid).getName();
		List<Subscriber> subs = subDao.findSubByName(pname);
		for(Subscriber sub:subs) snDao.createSubscribeNotify(pid, sub.getId(),sub.getUser().getId());
	}
	
	public List<Product> findSubProdcut(int id) {
		return subDao.findSubProdcut(id);
	}
	
}
