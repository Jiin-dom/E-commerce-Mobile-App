package com.jumpstartbackone.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Orders{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User who placed the order

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderItem> orderItems;
    
//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // Product in the order

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "shipping_address")
    private String shippingAddress;
    

	public Orders() {

	}


//	public Orders(Long orderId, User user, List<OrderItem> orderItems, Product product, int quantity,
//			double totalPrice, String orderStatus, LocalDateTime orderDate, String shippingAddress) {
//		super();
//		this.orderId = orderId;
//		this.user = user;
//		this.orderItems = orderItems;
//		this.product = product;
//		this.quantity = quantity;
//		this.totalPrice = totalPrice;
//		this.orderStatus = orderStatus;
//		this.orderDate = orderDate;
//		this.shippingAddress = shippingAddress;
//	}
	
	
	

	public Long getOrderId() {
		return orderId;
	}

	public Orders(Long orderId, User user, Product product, int quantity, double totalPrice, String orderStatus,
		LocalDateTime orderDate, String shippingAddress) {
	super();
	this.orderId = orderId;
	this.user = user;
	this.product = product;
	this.quantity = quantity;
	this.totalPrice = totalPrice;
	this.orderStatus = orderStatus;
	this.orderDate = orderDate;
	this.shippingAddress = shippingAddress;
}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


//	public List<OrderItem> getOrderItems() {
//		return orderItems;
//	}
//
//
//	public void setOrderItems(List<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}
//    
	
}


