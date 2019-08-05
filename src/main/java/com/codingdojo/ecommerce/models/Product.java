package com.codingdojo.ecommerce.models;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message = "Name cannot be blank")
    private String name;
    @Size(min=1, message = "Description cannot be blank")
    private String description;
    @DecimalMin("0.01")
	private Double price;
	@Size(min=1, message = "Image path cannot be blank")
	private String imageHash;

	public String getImageHash()
	{
		return this.imageHash;
	}

	public void setImageHash(String imageHash)
	{
		this.imageHash = imageHash;
	}

    @Column(updatable = false)
    private Date createdAt;
	private Date updatedAt;
	private Integer quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;

	public Order getOrder()
	{
		return this.order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

	public Category getCategory()
	{
		return this.category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

    @ManyToMany
    @JoinTable(
        name = "carts",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> carters;
    @ManyToOne(fetch=FetchType.LAZY)
    private Vendor vendor;
    @ManyToMany
    @JoinTable(
        name = "wishlists",
        joinColumns = @JoinColumn(name = "wish_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> wishers;
    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Product() {
	}
	public List<User> getWishers()
	{
		return this.wishers;
	}
	public Vendor getVendor()
	{
		return this.vendor;
	}
	public List<User> getCarters()
	{
		return this.carters;
	}

	public void setCarters(List<User> carters)
	{
		this.carters = carters;
	}
	public void setVendor(Vendor vendor)
	{
		this.vendor = vendor;
	}
	public void setWishers(List<User> wishers)
	{
		this.wishers = wishers;
	}
	public Integer getQuantity()
	{
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}
    public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}
    public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
    public String getDescription(){
		return this.description;
    }
    public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price){
		this.price = price;
	}
	public void setDescription(String description){
		this.description = description;
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