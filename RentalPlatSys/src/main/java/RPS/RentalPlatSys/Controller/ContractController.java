package RPS.RentalPlatSys.Controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import RPS.RentalPlatSys.DAO.ContractDao;
import RPS.RentalPlatSys.DAO.ContractRecordDao;
import RPS.RentalPlatSys.DAO.ProductDao;
import RPS.RentalPlatSys.Entity.Contract;
import RPS.RentalPlatSys.Entity.ContractRecord;

@Stateless

public class ContractController {
	@Inject ContractDao contractdao;
	@Inject ProductDao productdao;
	@Inject ContractRecordDao crdao;
	
	public Contract addContract(int pid,int uid,int term,Date date,int currentuid) {
		int price = productdao.findProductById(pid).getPrice()*term;
		Contract con = contractdao.createContract(null, "", "",price, "", uid,currentuid,pid,1,term,date);
		crdao.createContractRecord(con.getId(), 1);
		return con;
	}
	
	public List<Contract> getUserCon(int uid) {
		return contractdao.findContractByUserID(uid);
	}
	
	public List<Contract> getAllCon() {
		return contractdao.findContracts();
	}
	
	public Contract getConById(int id) {
		return contractdao.findContractsById(id);
	}
	
	public void setConStatus(int cid, int s) {
		contractdao.updateStatus(cid, s);
		crdao.createContractRecord(cid, s);
	}
	
	public void updateContract(int cid, String age, int price,String location,String cusdetail,String lenderS) {
		contractdao.updateCon(cid, age, price, location, cusdetail,lenderS);
	}
	
	public void updateLendeeSign(int cid, String lendeeS) {
		contractdao.updateLendeeSign(cid, lendeeS);
	}
	
	public List<ContractRecord> getConRecord(int id) {
		return crdao.findContractRecordByConid(id);
	}
	
	public Boolean isInTheSameContract(int login,int visit) {
		return contractdao.isInTheSameContract(login, visit);
	}
	
}
