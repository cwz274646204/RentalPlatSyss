package RPS.RentalPlatSys.Entity;

import java.util.List;
import javax.persistence.*;

@Entity

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
    private int id;
	private String email;
	private String password;
	private boolean isAdmin;
	private String credential;
	private String address;
	private String name;
	private String phone;
	
	@OneToMany(mappedBy="user")
	
	private List<Product> products; 
	
	@OneToMany(mappedBy="user")
	
	private List<Subscriber> subscribers; 
	
	@OneToMany(mappedBy="user")
	
	List<SubscriberNotify> SubscriberNotifys;

    public void setId(int id) {
		this.id = id;
	}

    public int getId() {
        return id;
    }
  
    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getEmail() {
        return email;
    }
   
    public void setPassword(String password) {
		this.password = password;
	}
    
    public String getPassword() {
        return password;
    }
    
    public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setCredential(String credential) {
		this.credential = credential;
	}
    
    public String getCredential() {
        return credential;
    }
   
	public void setAddress(String address) {
		this.address = address;
	}
	 
	public String getAddress() {
		return address;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
}
