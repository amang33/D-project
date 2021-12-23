// 2021.12.09 ������

package com.myspring.baroip.adminProduct.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.product.vo.ProductVO;

public interface AdminProductController {

	// ������ ������ ��ǰ ��� ��ü ����
	public ModelAndView adminProduct(HttpServletRequest request, HttpServletResponse response) throws Exception;

	//  ������ ������ ��ǰ ���
	public ModelAndView addProduct(@ModelAttribute("productVO") ProductVO productVO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	
	public ModelAndView selectExtraList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//  ������ ������ ��ǰ ���� ����
	public String update_amount(@RequestParam Map<String, String> info) throws Exception;
	
	// ������ ������ ��ǰ ����
	public ModelAndView delete_product(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//  ������ ������ ��ǰ ���� ��
	public ModelAndView update_product_form (@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// ������ ������ ��ǰ ����
	public ModelAndView update_product(@ModelAttribute("productVO") ProductVO productVO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	// ��¥�� �������� ��ǰ ��ȸ
	public void search_date(@RequestParam Map<String, String> option) throws Exception;
}
