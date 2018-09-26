package RPS.RentalPlatSys.DAO;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import RPS.RentalPlatSys.Entity.Product;

@Stateless

public class ProductDao {
	@PersistenceContext
	
	EntityManager em;
	
	@Inject UserDao udao;
	
	public Product createProduct(String description, boolean insurance, String name, int price, int userid,String photo) {
		Product prd = new Product();
		prd.setInsurance(insurance);
		prd.setName(name);
		prd.setPrice(price);
		prd.setStatus(0);
		prd.setTimes(0);
		prd.setUserid(udao.findUserProfile(userid));
		prd.setDescription(description);
		prd.setPhoto(photo);
		prd.setAvailableData(new Date());
		em.persist(prd);
		return prd;
	}
	
	public Product updateProduct(String description, Boolean insurance, String name, int price, int status,int userid) {
		Product prd = new Product();
		prd.setInsurance(insurance);
		prd.setName(name);
		prd.setPrice(price);
		prd.setStatus(status);
		prd.setTimes(0);
		prd.setUserid(udao.findUserProfile(userid));
		em.merge(prd);
		return prd;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Product> findAllProducts() {		
		String sql = "Select * from Product";
		return (List<Product>)em.createNativeQuery(sql, Product.class).getResultList();
	}
	
	public Product findProductById(int id) {		
		String sql = "Select * from Product where id=?";
		@SuppressWarnings("unchecked")
		List<Product> tmp = em.createNativeQuery(sql,Product.class).setParameter(1, id).getResultList();
		return (tmp.isEmpty())? null:tmp.get(0);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Product> findProductsByKeyword(String keyword) {		
		String sql = "Select * from Product where name like ?";
		Query query = em.createNativeQuery(sql, Product.class).setParameter(1, "%"+keyword+"%");
		return (List<Product>)query.getResultList();
	}
	
	public void updateAvailableDateAndStatus(int id,Date date,int status) {
		Product p = findProductById(id);
		p.setAvailableData(date);
		p.setStatus(status);
		em.merge(p);
	}
	
	public void updateTime(int id) {
		Product p = findProductById(id);
		p.setTimes(p.getTimes()+1);
		em.merge(p);
	}
	
}
