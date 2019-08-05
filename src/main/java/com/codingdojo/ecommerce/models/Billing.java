package com.codingdojo.ecommerce.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="billings")
public class Billing{
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
    @Size(min=1, message = "Card Number cannot be blank")
    private String creditCard;
    @Size(min=1, message = "Security Code cannot be blank")
    private String csvCode;
    @Size(min=2, max = 2, message = "Expiration month cannot be blank")
    private String eMonth;
    @Size(min=2, max = 2, message = "Expiration year cannot be blank")
    private String eYear;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany
    @JoinTable(
        name = "billing_users",
        joinColumns = @JoinColumn(name = "billing_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> cardHolders;
    public Billing(){

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
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getCsvCode() {
		return csvCode;
	}
	public void setCsvCode(String csvCode) {
		this.csvCode = csvCode;
	}
	public String geteMonth() {
		return eMonth;
	}
	public void seteMonth(String eMonth) {
		this.eMonth = eMonth;
	}
	public String geteYear() {
		return eYear;
	}
	public void seteYear(String eYear) {
		this.eYear = eYear;
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
	public List<User> getCardHolders() {
		return cardHolders;
	}
	public void setCardHolders(List<User> cardHolders) {
		this.cardHolders = cardHolders;
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