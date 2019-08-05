package com.codingdojo.ecommerce.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message = "First name cannot be blank")
    private String firstName;
    @Size(min=1, message = "Last name cannot be blank")
    private String lastName;
    @Email(message = "Email must be valid")
    private String email;
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;
    @Transient
	private String passwordConfirmation;
    @Column(updatable=false)
    private Date createdAt;
	private Date updatedAt;
	private int adminLevel = 9;

	public int getAdminLevel()
	{
		return this.adminLevel;
	}

	public void setAdminLevel(int adminLevel)
	{
		this.adminLevel = adminLevel;
	}

	@ManyToMany
    @JoinTable(
        name = "carts",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
	private List<Product> cart;
	@ManyToMany
    @JoinTable(
        name = "wishlists",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "wish_id")
    )
	private List<Product> wishlist;
    @ManyToMany
    @JoinTable(
        name="billing_users",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "billing_id")
    )
    private List<Billing> billings;
    @OneToMany(mappedBy="shippingAddressUser", fetch = FetchType.LAZY)
    private List<ShippingAddress> shippingAddresses;
    @OneToMany(mappedBy="author", fetch = FetchType.LAZY)
    private List<Review> reviews;
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY)
	private List<Order> orders;

	public List<Order> getOrders()
	{
		return this.orders;
	}

	public void setOrders(List<Order> orders)
	{
		this.orders = orders;
	}


    public User() {

    }

	public List<Product> getCart(){
		return this.cart;
	}
	public void setCart(List<Product> cart){
		this.cart = cart;
	}
	public List<Product> getWishlist(){
		return this.wishlist;
	}
	public void setWishlist(List<Product> wishlist){
		this.wishlist = wishlist;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	public List<Billing> getBillings() {
		return billings;
	}
	public void setBillings(List<Billing> billings) {
		this.billings = billings;
	}
	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}
	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
    }
    public List<Review> getReviews(){
		return this.reviews;
	}
	public void setReviews(List<Review> reviews){
		this.reviews = reviews;
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