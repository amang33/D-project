// 2022.01.14 ������

package com.myspring.baroip.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl implements OrderController{


	// Order ��ü mapping
	@Override
	@RequestMapping(value = "/*.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}
	
	// �ֹ� ������ �̵� ��Ʈ�ѷ�
	// post ������ �迭������ product_id�� �����ؾ� �Ѵ�.
	@Override
	@RequestMapping(value = "/order_form.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView orderForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		String[] arrayProductID = request.getParameterValues("product_id");


		for( String product_id : arrayProductID) {
			System.out.println(product_id);
		}

		return mav;
	}
	
	

	
}
