package com.qad.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qad.demo.model.Bucket;
import com.qad.demo.model.Product;
import com.qad.demo.model.Result;
import com.qad.demo.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	private Bucket productBucket = null;
 
    @Override
    @Cacheable(value = "demoCache", key="#cachItem")
    public List<Product> getAllProducts(String cachItem) {
        slowLookupOperation();
        return this.getBucket().Products;
    }
    
    @Override    
    public List<Product> getAllProducts() {
        slowLookupOperation();
        return this.getBucket().Products;
    }
    
    @CacheEvict(value = "demoCache", allEntries = true)
    public void refreshAllProducts() {
       //This method will remove all 'products' from cache, say as a result of flush API call.
    }
 
    public void slowLookupOperation(){
         try {
                long time = 5000L;
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
           }
    }

	@Override
	public Bucket getBucket() {
		if(productBucket == null)
			productBucket = new Bucket();
		return productBucket;
	}
	
	@Override
	public Result create(Map<String, String[]> params) {
		Result result = new Result();
		Product product = new Product();		
		JSONObject jsonObject = getJSONObject(params);	 
		String productName = (String)jsonObject.get("productName");
		Double unitPrice = Double.parseDouble(jsonObject.get("unitPrice").toString());
		Boolean discontinued = (Boolean)jsonObject.get("discontinued");
		Integer unitsInStock = Integer.parseInt(jsonObject.get("unitsInStock").toString());
		String type = (String)jsonObject.get("type");
		product.update(productName, unitPrice, discontinued, unitsInStock,type);
		this.getBucket().Products.add(product);
		result.setProduct(product);
		result.setSuccess(true);
		return result;
	}

	@Override
	public Result update(ServletInputStream params) {
		Result result = new Result();
		JSONObject jsonObject = getJSONObject(params);	
		Integer productID = Integer.parseInt(jsonObject.get("productID").toString());
		String productName = (String)jsonObject.get("productName");
		Double unitPrice = Double.parseDouble(jsonObject.get("unitPrice").toString());
		Boolean discontinued = (Boolean)jsonObject.get("discontinued");
		Integer unitsInStock = Integer.parseInt(jsonObject.get("unitsInStock").toString());
		String type = (String)jsonObject.get("type");
		Product product = this.getBucket().getProduct(productID);
		if(product != null){
			product.update(productName, unitPrice, discontinued, unitsInStock,type);
			result.setProduct(product);
			result.setSuccess(true);
			return result;
		}
		
		// Couldn't find the product in the bucket with given product id
		result.setMessage("System can't find the product id: " + productID);
		return result;
	}

	

	@Override
	public Result delete(ServletInputStream params){
		Result result = new Result();
		JSONObject jsonObject = getJSONObject(params);	
		Integer productID = Integer.parseInt(jsonObject.get("productID").toString());
		Product product = this.productBucket.getProduct(productID);
		if(product != null){
			this.getBucket().Products.remove(product);
			result.setProduct(product);
			result.setSuccess(true);
			result.setMessage("Product: " + productID + " was removed!");
			return result;
		}		
		// Couldn't find the product in the bucket with given product id
		result.setMessage("System can't find the product id: " + productID);
		return result;
	}
	
	private JSONObject getJSONObject(Map<String, String[]> params){
		JSONParser parser = new JSONParser();
		Object obj = null;
		JSONObject jsonObject = null;
		try {
			String[] paramsArray = (String[])params.get("params");
			obj = parser.parse(paramsArray[0]);
			jsonObject = (JSONObject) obj;
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	private JSONObject getJSONObject(ServletInputStream params){
		JSONParser parser = new JSONParser();
		Object obj = null;
		JSONObject jsonObject = null;
		try {
			String paramsStr = convertStreamToString(params);
			obj = parser.parse(paramsStr);
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}