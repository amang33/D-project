// 2022.01.14 ������

package com.myspring.baroip.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface OrderController {

	// Order ��ü mapping
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView orderForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// ���� ��Ʈ�ѷ�
	public ModelAndView orderProduct(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
