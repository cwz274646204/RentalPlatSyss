package RPS.RentalPlatSys.Controller;

import javax.ejb.Stateless;
import javax.inject.Inject;
import RPS.RentalPlatSys.DAO.LeasePeriodDao;

@Stateless

public class LeasePeriodController {
	@Inject LeasePeriodDao lpdao;
	
	public void init () {
		if(lpdao.findAllLeasePeriod().size()==0) {
			lpdao.initLeasePeriod();
		}
	}
	
	public void updateTerm(int shortTerm,int mediumTerm, int averageTerm) {
		lpdao.updateLease("short", shortTerm);
		lpdao.updateLease("medium", mediumTerm);
		lpdao.updateLease("average", averageTerm);
	}
	
	public int findValueByName (String name) {
		return lpdao.findLeasePeriodByName(name).getValue();
	}
	
}
