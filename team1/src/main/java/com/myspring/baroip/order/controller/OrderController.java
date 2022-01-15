// 2022.01.14 ������

package com.myspring.baroip.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface OrderController {

	// Order ��ü mapping
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView orderForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
