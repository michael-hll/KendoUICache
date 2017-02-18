package com.test.demo.model;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 8795653653636L;
	public static int NextProductId = 0;
	private int productID;
	private String productName;
    private double unitPrice;
    private boolean discontinued;
    private int unitsInStock;
    private String type;
    
    public Product(String productName, double unitPrice, boolean discontinued, int unitsInStock, String type) {
		super();
		this.productID = ++Product.NextProductId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.discontinued = discontinued;
		this.unitsInStock = unitsInStock;
		this.type = type;
	}
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product() {
		super();		
		this.productID = ++Product.NextProductId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productID != other.productID)
			return false;
		return true;
	}

	public int getProductID() {
		return productID;
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public boolean isDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}


	public int getUnitsInStock() {
		return unitsInStock;
	}


	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
    
 
    @Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", unitPrice=" + unitPrice
				+ ", discontinued=" + discontinued + ", unitsInStock=" + unitsInStock + ", type=" + type + "]";
	}
    
    public void update(String productName, double unitPrice, boolean discontinued, int unitsInStock, String type) {
		this.setProductName(productName);
		this.setUnitPrice(unitPrice);
		this.setDiscontinued(discontinued);
		this.setUnitsInStock(unitsInStock);
		this.setType(type);
	};
}
