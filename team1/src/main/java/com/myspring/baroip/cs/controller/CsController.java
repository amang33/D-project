package com.myspring.baroip.cs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.cs.vo.CsVO;

public interface CsController {
	
//	���� ���� ���� ����Ʈ
	public ModelAndView cs_01(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
//	1:1 ���� ����Ʈ
	public ModelAndView cs_02(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
//	1:1 ���� ��
	public ModelAndView quest_datail(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
