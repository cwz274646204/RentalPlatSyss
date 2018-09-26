package RPS.RentalPlatSys.DAO;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import RPS.RentalPlatSys.Entity.Contract;

@Stateless

public class ContractDao {
	@PersistenceContext
	
	EntityManager em;
	@Inject ProductDao pdao;
	@Inject UserDao udao;
	
	public Contract createContract(String age, String customer_detail,String detail,int finalprice,String location,int lenderid,int lendeeid, int product_id,int status,int term,Date date) {
		Contract con = new Contract();
		con.setAge(age);
		con.setCustomer_detail(customer_detail);
		con.setDetail(detail);
		con.setFinalprice(finalprice);
		con.setLocation(location);
		con.setLender(udao.findUserProfile(lenderid));
		con.setLendee(udao.findUserProfile(lendeeid));
		con.setProduct(pdao.findProductById(product_id));
		con.setStatus(status);
		con.setTerm(term);
		con.setStartDate(date);
		em.persist(con);
		return con;
	}
	
	public Contract updateContract(String age, String customer_detail,String detail,int finalprice,String location,int lenderid,int lendeeid,int product_id) {
		Contract con = new Contract();
		con.setAge(age);
		con.setCustomer_detail(customer_detail);
		con.setDetail(detail);
		con.setFinalprice(finalprice);
		con.setLocation(location);
		con.setLender(udao.findUserProfile(lenderid));
		con.setLendee(udao.findUserProfile(lendeeid));
		con.setProduct(pdao.findProductById(product_id));
		em.merge(con);
		return con;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Contract> findContractByUserID(int uid) {
		String sql = "Select * from Contract where lender_id=? or lendee_id=?";
		return (List<Contract>)em.createNativeQuery(sql, Contract.class).setParameter(1, uid).setParameter(2, uid).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Contract> findContracts() {
		String sql = "Select * from Contract";
		return (List<Contract>)em.createNativeQuery(sql, Contract.class).getResultList();
	}
	
	public Contract findContractsById(int id) {
		String sql = "Select * from Contract where id=?";
		@SuppressWarnings("unchecked")
		
		List<Contract> tmp = em.createNativeQuery(sql, Contract.class).setParameter(1, id).getResultList();
		return (tmp.isEmpty())? null:tmp.get(0);
	}
	
	public void updateStatus(int cid, int s) {
		String sql = "UPDATE Contract SET status =? WHERE id = ?";
		Query query = em.createNativeQuery(sql, Contract.class).setParameter(1,s).setParameter(2, cid);
		query.executeUpdate();
	}
	
	public void updateCon(int cid, String age, int price,String location,String cusdetail,String lenderSignature) {
		Contract con = findContractsById(cid);
		if (age!=null) con.setAge(age);
		if (price!=0) con.setFinalprice(price);
		if (location!=null) con.setLocation(location);
		if (cusdetail!=null) con.setCustomer_detail(cusdetail);
		con.setLenderSignature(lenderSignature);
		em.merge(con);
	}
	
	public void updateLendeeSign(int cid,String lendeeSignature) {
		Contract con = findContractsById(cid);
		con.setLendeeSignature(lendeeSignature);
		em.merge(con);
	}
	
	public Boolean isInTheSameContract(int login,int visit) {
		String sql = "Select * from Contract where lender_id=? and lendee_id=?";
		List<Contract> tmp1 = (List<Contract>)em.createNativeQuery(sql, Contract.class).setParameter(1, login).setParameter(2, visit).getResultList();
		List<Contract> tmp2 = (List<Contract>)em.createNativeQuery(sql, Contract.class).setParameter(1, visit).setParameter(2, login).getResultList();
		return (tmp1.size()!=0||tmp2.size()!=0);
	}
	
}
