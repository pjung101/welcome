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

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String shippingSpeed;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(fetch=FetchType.LAZY)
    private User customer;

    @ManyToOne(fetch=FetchType.LAZY)
    private ShippingAddress shippingAddress;

    @ManyToOne(fetch=FetchType.LAZY)
    private Billing billing;

    @OneToMany(mappedBy="order", fetch=FetchType.LAZY)
    private List<Product> userOrder;

    public Order(){
	}
	
	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getShippingSpeed(){
		return this.shippingSpeed;
	}
	public void setShippingSpeed(String shippingSpeed){
		this.shippingSpeed = shippingSpeed;
	}
	public Date getCreatedAt(){
		return this.createdAt;
	}
	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt(){
		return this.updatedAt;
	}
	public void setUpdatedAt(Date updatedAt){
		this.updatedAt = updatedAt;
	}
	public User getCustomer(){
		return this.customer;
	}
	public void setCustomer(User customer){
		this.customer = customer;
	}
	public ShippingAddress getShippingAddress(){
		return this.shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress){
		this.shippingAddress = shippingAddress;
	}
	public Billing getBilling(){
		return this.billing;
	}
	public void setBilling(Billing billing){
		this.billing = billing;
	}
	public List<Product> getUserOrder(){
		return this.userOrder;
	}
	public void setUserOrder(List<Product> userOrder){
		this.userOrder = userOrder;
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