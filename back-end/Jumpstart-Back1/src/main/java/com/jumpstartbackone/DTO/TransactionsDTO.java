package com.jumpstartbackone.DTO;

import java.time.LocalDateTime;

import com.jumpstartbackone.entity.OrderRequest;

public class TransactionsDTO {
    private String email;
    private Double payment;
    private LocalDateTime transactionDate;
    private OrderRequest order; // Add OrderRequest field

    public TransactionsDTO() {
    }



	public TransactionsDTO(String email, Double payment, LocalDateTime transactionDate, OrderRequest order) {
		super();
		this.email = email;
		this.payment = payment;
		this.transactionDate = transactionDate;
		this.order = order;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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



	public OrderRequest getOrder() {
		return order;
	}


	public void setOrder(OrderRequest order) {
		this.order = order;
	}

}
