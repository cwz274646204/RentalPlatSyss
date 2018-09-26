package RPS.RentalPlatSys.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity

public class Contract {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
    private int id;
	
	@ManyToOne
	
    private User lender;
	
	@ManyToOne
	
    private User lendee;
    private String detail;
    private Date startDate;
    private int term;
    
    @ManyToOne
    
    private Product product;
    private int finalprice;
    private String location;
    private String age;
    
    @Column(length=300) 
    
    private String customer_detail;
    
    @Column(length=65535) 
    
    private String lenderSignature;
    
    @Column(length=65535) 
    
    private String lendeeSignature;
    private int status;
    
    @OneToMany(mappedBy="contract")
    
	private List<ContractRecord> ContractRecords; 
    
    public void setId(int id) {
 		this.id = id;
 	}
    
    public int getId() {
        return id;
    }
 
	public void setLender(User lender) {
		this.lender = lender;
	}
	
	public User getLender() {
		return lender;
	}

	public void setLendee(User lendee) {
		this.lendee = lendee;
	}

	public User getLendee() {
		return lendee;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setTerm(int term) {
		this.term = term;
	}
	
	public int getTerm() {
		return term;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setFinalprice(int finalprice) {
		this.finalprice = finalprice;
	}
	
	public int getFinalprice() {
		return finalprice;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getAge() {
		return age;
	}

	public void setCustomer_detail(String customer_detail) {
		this.customer_detail = customer_detail;
	}
	
	public String getCustomer_detail() {
		return customer_detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setLenderSignature(String lenderSignature) {
		this.lenderSignature = lenderSignature;
	}
	
	public String getLenderSignature() {
		return lenderSignature;
	}

	public void setLendeeSignature(String lendeeSignature) {
		this.lendeeSignature = lendeeSignature;
	}
	
	public String getLendeeSignature() {
		return lendeeSignature;
	}
	
}
