package RPS.RentalPlatSys.Entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Subscriber {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private int id;
    
    @ManyToOne
    
    private User user;
    private String subscribe_name;
  
    @OneToMany(mappedBy="subscriber")
    
    List<SubscriberNotify> SubscriberNotifys;
	
	public void setId(int id) {
		this.id = id;
	}
	
    public int getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void setSubscribe_name(String subscribe_name) {
		this.subscribe_name = subscribe_name;
	}
	
	public String getSubscribe_name() {
		return subscribe_name;
	}
	
}
