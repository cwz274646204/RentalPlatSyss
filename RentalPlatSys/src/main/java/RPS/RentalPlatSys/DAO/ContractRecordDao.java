package RPS.RentalPlatSys.DAO;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import RPS.RentalPlatSys.Entity.ContractRecord;

@Stateless

public class ContractRecordDao {
	@PersistenceContext
	
	EntityManager em;
	@Inject ContractDao cdao;
	
	public void createContractRecord(int conid,int status) {
		ContractRecord cr = new ContractRecord();
		cr.setContract(cdao.findContractsById(conid));
		cr.setStatus(status);
		cr.setStatus(status);
		cr.setDate(new Date());
		em.persist(cr);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<ContractRecord> findContractRecordByConid(int conid) {
		String sql = "Select * from ContractRecord where contract_id=?";
		return (List<ContractRecord>)em.createNativeQuery(sql, ContractRecord.class).setParameter(1, conid).getResultList();
	}
	
}
