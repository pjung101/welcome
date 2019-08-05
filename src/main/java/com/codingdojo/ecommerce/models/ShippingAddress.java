package com.codingdojo.ecommerce.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="shippingAddresses")
public class ShippingAddress{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message = "First Name cannot be blank")
    private String firstName;
    @Size(min=1, message = "Last Name cannot be blank")
    private String lastName;
    @Size(min=1, message = "Address cannot be blank")
    private String address;
    @Size(min=1, message = "City cannot be blank")
    private String city;
    @Size(min=1, message = "State cannot be blank")
    private String state;
    @Size(min=1, message = "Zipcode cannot be blank")
    private String zipcode;
	@OneToMany(mappedBy="shippingAddress", fetch=FetchType.LAZY)
	private List<Order> ordersUsingShippingAddress;

	public List<Order> getOrdersusingshippingaddress()
	{
		return this.ordersUsingShippingAddress;
	}

	public void setOrdersusingshippingaddress(List<Order> ordersUsingShippingAddress)
	{
		this.ordersUsingShippingAddress = ordersUsingShippingAddress;
	}

	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User shippingAddressUser;

    public ShippingAddress(){
    }
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
    }
    public User getShippingAddressUsers(){
		return this.shippingAddressUser;
	}
	public void setShippingAddressUsers(User shippingAddressUser){
		this.shippingAddressUser = shippingAddressUser;
	}
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}