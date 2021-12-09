// 2021.12.08 ������

package com.myspring.baroip.adminProduct.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.adminProduct.service.AdminProductService;
import com.myspring.baroip.product.vo.ProductVO;

@Controller("adminProductController")
@RequestMapping(value = "/admin/product")
public class AdminProductControllerImpl implements AdminProductController {
	
	@Autowired
	private AdminProductService adminProductService;

	// ��ǰ���� ������ ��ü mapping
	@Override
	@RequestMapping(value = "/*", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView adminProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);

		return mav;
	}


	// ��ǰ �ӽ� ���
	@Override
	@RequestMapping(value = "/addProduct.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addProduct(@ModelAttribute("productVO") ProductVO productVO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		
		Iterator<String> test = multipartRequest.getFileNames();
				
		
		while(test.hasNext()) {
			System.out.println(test.next());
		}


		
		ModelAndView mav = new ModelAndView();
		
		
		String addProductName = adminProductService.addProduct(productVO);
		String message = "["+addProductName+"] ��ǰ�� �ӽõ���� �Ϸ�Ǿ����ϴ�.";
		mav.addObject("message", message);
		mav.setViewName("/admin/product/list");

		return mav;
	}
}
