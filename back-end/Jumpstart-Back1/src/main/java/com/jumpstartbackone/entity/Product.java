package com.jumpstartbackone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long productId;
	 
	 @Column
	 private String productName;
	 
	 @Column
	 private int productPrice;
	  
	 @Column
	 private int productStock;
	 
	 @Column
	 private String productCategory;
	 
	 @Column 
	 private String productHeight;
	 
	 @Column
	 private String productWeight;
	 
	 @Column 
	 private String productWidth;
	 
	 @Column 
	 private String productDescription;
	 
	 @Column
	 private String productImgPath;
	 
	 @Column
	 private String productPhoto;

	public Product() {
	}

	public Product(Long productId, String productName, int productPrice, int productStock, String productCategory,
			String productHeight, String productWeight, String productWidth, String productDescription,
			String productImgPath, String productPhoto) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productCategory = productCategory;
		this.productHeight = productHeight;
		this.productWeight = productWeight;
		this.productWidth = productWidth;
		this.productDescription = productDescription;
		this.productImgPath = productImgPath;
		this.productPhoto = productPhoto;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductHeight() {
		return productHeight;
	}

	public void setProductHeight(String productHeight) {
		this.productHeight = productHeight;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	public String getProductWidth() {
		return productWidth;
	}

	public void setProductWidth(String productWidth) {
		this.productWidth = productWidth;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImgPath() {
		return productImgPath;
	}

	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}

	public String getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productStock=" + productStock + ", productCategory=" + productCategory + ", productHeight="
				+ productHeight + ", productWeight=" + productWeight + ", productWidth=" + productWidth
				+ ", productDescription=" + productDescription + ", productImgPath=" + productImgPath
				+ ", productPhoto=" + productPhoto + "]";
	}

	
	
	
	


	 
}
