package RPS.RentalPlatSys.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import RPS.RentalPlatSys.Entity.Contract;
import RPS.RentalPlatSys.Entity.User;
import RPS.RentalPlatSys.Util.Util;

@Stateless

public class UserDao {
	@PersistenceContext
	
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	
	public List<User> findByEmailAndPassword(String email, String password) {
		password = Util.getMD5(password);
		String sql = "Select * from User where email=? and password=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1, email).setParameter(2, password);
		return (List<User>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	
	public List<User> findByEmail(String email) {
		String sql = "Select * from User where email=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1,email);
		return (List<User>)query.getResultList();
	}
	
	public User createUser(String email, String password, String address, String name,String phone) {
		password = Util.getMD5(password);
		User user = new User();
		user.setAddress(address);
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		user.setIsAdmin(false);
		user.setPassword(password);
		em.persist(user);
		return user;
	}
	
	public User findUserProfile(int id) {
		String sql = "Select * from User where id=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1,id);
		return (User)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	
	public List<User> findAllUser() {
		String sql = "Select * from User where isAdmin=0";
		return (List<User>)em.createNativeQuery(sql, User.class).getResultList();
	}
	
	public void updateProfile(int id, String address,String name, String phone) {
		User user = findUserProfile(id);
		if (address!=null) user.setAddress(address);
		if (name!=null) user.setName(name);
		if (phone!=null) user.setPhone(phone);
		em.merge(user);
	}
	
	public void updateCred(int id, String filename) {
		User user = findUserProfile(id);
		if (filename!=null) user.setCredential(filename);
		em.merge(user);
	}
	
	public User findByCred(String filename) {
		String sql = "Select * from User where credential=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1, filename);
		return (User)query.getSingleResult();
	}
	
}
