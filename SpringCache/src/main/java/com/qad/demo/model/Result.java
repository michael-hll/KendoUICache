package com.qad.demo.model;

import java.io.Serializable;

public class Result  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean success = false;
	private Product product = null;
	private String message = "";
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Result(){
	
	}
	

}
