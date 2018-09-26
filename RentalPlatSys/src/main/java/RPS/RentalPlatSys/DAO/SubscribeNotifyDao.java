package RPS.RentalPlatSys.DAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import RPS.RentalPlatSys.Entity.SubscriberNotify;

@Stateless

public class SubscribeNotifyDao {
	@PersistenceContext
	
	EntityManager em;
	
	@Inject UserDao udao;
	@Inject ProductDao pdao;
	@Inject SubscriberDao sdao;
	
	public SubscriberNotify createSubscribeNotify(int pid,int subid,int userid) {
		SubscriberNotify sn = new SubscriberNotify();
		sn.setProduct(pdao.findProductById(pid));
		sn.setSubscriber(sdao.findSubById(subid));
		sn.setUser(udao.findUserProfile(userid));
		em.persist(sn);
		return sn;
	}
	
}
