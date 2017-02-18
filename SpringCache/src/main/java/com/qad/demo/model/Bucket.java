package com.qad.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bucket implements Serializable{

	private static final long serialVersionUID = 565689656L;
	
	public List<Product> Products = new ArrayList<Product>();
	
	public void add(){
		Product product = new Product();
		Products.add(product);
	}
	
	public Product getProduct(int productID){
		for(Product product : this.Products){
			if(product.getProductID() == productID){
				return product;
			}
		}
		return null;
	}

	public Bucket() {
		super();
		Product product = new Product();
		product.update("iPhone 6", 600.00d, true, 10, "Small");
		Products.add(product);
		
		product = new Product();
		product.update("iPhone 6 Plus", 650.00d, true, 10, "Small");
		Products.add(product);
		
		product = new Product();
		product.update("iPhone 7", 700.00d, true, 50, "Small");
		Products.add(product);
		
		product = new Product();
		product.update("iPhone 7 Plus", 750.00d, true, 50, "Small");
		Products.add(product);
		
		product = new Product();
		product.update("iPad", 2000.00d, true, 10, "Medium");
		Products.add(product);
		
		product = new Product();
		product.update("iPad Pro", 2500.00d, true, 10, "Medium");
		Products.add(product);
		
		product = new Product();
		product.update("MacBook 13", 3000.00d, true, 5, "Large");
		Products.add(product);
		
		product = new Product();
		product.update("MacBook 13 Pro", 3500.00d, true, 5, "Large");
		Products.add(product);
		
		product = new Product();
		product.update("iMac 21", 4000.00d, true, 3, "Large");
		Products.add(product);
		
		product = new Product();
		product.update("iMac 27", 5000.00d, true, 2, "Large");
		Products.add(product);
	}
	
}
