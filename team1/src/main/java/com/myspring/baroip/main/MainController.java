// 2021.12.09 ������

package com.myspring.baroip.main;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.product.service.ProductService;

@Controller
public class MainController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value= "/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{

		ModelAndView mav = new ModelAndView();
		Map<String, Map<String, Object>> bestProducts = productService.bestProductList();
		String viewName = (String)request.getAttribute("viewName");
		mav.addObject("bestProducts", bestProducts);
		mav.setViewName(viewName);
		
		return mav;
	}
	
	


	/* 
	 * 
	 * // admin-ȸ�� ����
	 * 
	 * @RequestMapping(value= "/adminUser_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminUser_01(HttpServletRequest request, HttpServletResponse response) throws
	 * Exception{ // HttpSession session; ModelAndView mav = new ModelAndView();
	 * String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; } // admin-ȸ�� ���� ��
	 * 
	 * @RequestMapping(value= "/adminUser_02.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminUser_02(HttpServletRequest request, HttpServletResponse response) throws
	 * Exception{ // HttpSession session; ModelAndView mav = new ModelAndView();
	 * String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin- Q&A ���� ���
	 * 
	 * @RequestMapping(value= "/adminCS_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminCS_01(HttpServletRequest request, HttpServletResponse response) throws
	 * Exception{ // HttpSession session; ModelAndView mav = new ModelAndView();
	 * String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin- Q&A �ۼ�
	 * 
	 * @RequestMapping(value= "/adminCS_01_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminCS_01_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin- Q&A ����
	 * 
	 * @RequestMapping(value= "/adminCS_01_02.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminCS_01_02(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin- Q&A ��
	 * 
	 * @RequestMapping(value= "/adminCS_01_03.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminCS_01_03(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin- Q&A ���� ����
	 * 
	 * @RequestMapping(value= "/adminCS_02.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminCS_02(HttpServletRequest request, HttpServletResponse response) throws
	 * Exception{ // HttpSession session; ModelAndView mav = new ModelAndView();
	 * String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin- Q&A ���� �亯 �ۼ�
	 * 
	 * @RequestMapping(value= "/adminCS_02_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminCS_02_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - �ֹ� ����
	 * 
	 * @RequestMapping(value= "/adminOrder.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminOrder(HttpServletRequest request, HttpServletResponse response) throws
	 * Exception{ // HttpSession session; ModelAndView mav = new ModelAndView();
	 * String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��ǰ / ��ȯ ����
	 * 
	 * @RequestMapping(value= "/adminReturn_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminReturn_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * 
	 * // admin - ��ǰ / ��ȯ ��û�� Ȯ�� ������
	 * 
	 * @RequestMapping(value= "/adminReturn_02.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminReturn_02(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��ǰ ����
	 * 
	 * @RequestMapping(value= "/adminProduct_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminProduct_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��ǰ ���
	 * 
	 * @RequestMapping(value= "/adminProduct_02.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminProduct_02(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��ǰ ����
	 * 
	 * @RequestMapping(value= "/adminProduct_03.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminProduct_03(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��������
	 * 
	 * @RequestMapping(value= "/adminNotice_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminNotice_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��������
	 * 
	 * @RequestMapping(value= "/adminNotice_02.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminNotice_02(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��������
	 * 
	 * @RequestMapping(value= "/adminNotice_02_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminNotice_02_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ��������
	 * 
	 * @RequestMapping(value= "/adminNotice_03.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminNotice_03(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ����
	 * 
	 * @RequestMapping(value= "/adminReview_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminReview_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 * 
	 * // admin - ����
	 * 
	 * @RequestMapping(value= "/adminReview_01_01.do"
	 * ,method={RequestMethod.POST,RequestMethod.GET}) public ModelAndView
	 * adminReview_01_01(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ // HttpSession session; ModelAndView mav = new
	 * ModelAndView(); String viewName = (String)request.getAttribute("viewName");
	 * mav.setViewName(viewName); return mav; }
	 */
		
}
