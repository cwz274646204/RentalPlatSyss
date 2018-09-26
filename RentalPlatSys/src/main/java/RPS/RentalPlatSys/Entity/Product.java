package RPS.RentalPlatSys.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity

public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private int id;
    private String photo;
    private String name;
    
    @Column(length=65535) 
    
    private String description;
    
    @ManyToOne
    private User user;
    private int status;
    private boolean insurance;
    private int price;
    private int times;
    private Date availableDate;
    
    @OneToMany(mappedBy="product")
    
	List<SubscriberNotify> SubscriberNotifys;
  
	public void setId(int id) {
		this.id = id;
	}
	  
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
	public int getTimes() {
		return times;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	public Boolean getInsurance() {
		return insurance;
	}
	
	public void setAvailableData(Date availableDate) {
		this.availableDate = availableDate;
	}
	
	public Date getAvailableDate() {
		return availableDate;
	}
	
	public void setUserid(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
