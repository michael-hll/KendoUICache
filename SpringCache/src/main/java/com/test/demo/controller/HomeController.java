package com.test.demo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.model.Bucket;
import com.test.demo.model.Product;
import com.test.demo.model.Result;
import com.test.demo.service.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getProducts(Model model) throws JsonProcessingException {
		
		logger.info("/api/products");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(productService.getAllProducts());
		return json;
	}
	
	@RequestMapping(value = "/api/products/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateProduct(Model model,
			HttpServletRequest request) throws IOException {
		
		logger.info("/api/products/update, request.getParameterMap.get('params') = {}", request.getParameterMap().get("params"));
		
		Result result = productService.update(request.getInputStream());	
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result.getProduct());
		return json;
	}
	
	@RequestMapping(value = "/api/products/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createProduct(Model model,
			HttpServletRequest request) throws IOException {		
		
		logger.info("/api/products/create, request.getParameterMap.get('params') = {}", request.getParameterMap().get("params"));		
		ObjectMapper mapper = new ObjectMapper();		
		@SuppressWarnings("unchecked")
		Result result = productService.create(request.getParameterMap());		
		String json = mapper.writeValueAsString(result.getProduct());
		return json;
	}
	
	@RequestMapping(value = "/api/products/destroy", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteProduct(Model model,
			HttpServletRequest request) throws IOException {
		
		logger.info("/api/products/destroy, request.getParameterMap.get('params') = {}", request.getParameterMap().get("params"));
		Result result = productService.delete(request.getInputStream());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result.getProduct());
		return json;
	}
	
	@RequestMapping(value = "/api/products/cache/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllProducts(Model model,@PathVariable("name") String name) 
		throws JsonProcessingException {	
		logger.info("/api/products/cache/{}",name);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(productService.getAllProducts(name));
		return json;
	}
	
	@RequestMapping(value = "/api/products/cache/clear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String refreshProducts(Model model) throws JsonProcessingException{
		logger.info("/api/products/cache/clear");
		productService.refreshAllProducts();
		return "refreshed";
	}
	
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
}
