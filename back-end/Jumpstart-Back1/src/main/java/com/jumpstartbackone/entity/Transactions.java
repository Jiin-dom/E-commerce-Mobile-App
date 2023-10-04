package com.jumpstartbackone.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String email;
	
    @OneToOne
    @JoinColumn(name = "user_id")
	private User user;
	
    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderRequest order;

	private Double payment;
	private LocalDateTime transactionDate;
	
	
	public Transactions() {

	}


	public Transactions(Long id, String email, User user, OrderRequest order, Double payment,
			LocalDateTime transactionDate) {
		super();
		this.id = id;
		this.email = email;
		this.user = user;
		this.order = order;
		this.payment = payment;
		this.transactionDate = transactionDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public OrderRequest getOrder() {
		return order;
	}


	public void setOrder(OrderRequest order) {
		this.order = order;
	}


	public Double getPayment() {
		return payment;
	}


	public void setPayment(Double payment) {
		this.payment = payment;
	}


	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
	
	
	
	
}
