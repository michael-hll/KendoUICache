package com.qad.demo.service;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import com.qad.demo.model.Bucket;
import com.qad.demo.model.Product;
import com.qad.demo.model.Result;

public interface ProductService {
	List<Product> getAllProducts(String cacheName);
	List<Product> getAllProducts();
	void refreshAllProducts();
	Bucket getBucket();
	Result create(Map<String, String[]> params);
	Result update(ServletInputStream  params);	
	Result delete(ServletInputStream  params);	
}
