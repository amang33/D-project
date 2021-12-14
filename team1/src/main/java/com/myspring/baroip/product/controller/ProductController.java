package com.myspring.baroip.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface ProductController {
	public ModelAndView product(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView productTest(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView productDetail(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
