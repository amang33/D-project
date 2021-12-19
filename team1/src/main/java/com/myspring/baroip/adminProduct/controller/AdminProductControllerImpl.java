// 2021.12.08 ������

package com.myspring.baroip.adminProduct.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.adminProduct.service.AdminProductService;
import com.myspring.baroip.image.controller.ImageController;
import com.myspring.baroip.product.service.ProductService;
import com.myspring.baroip.product.vo.ProductVO;

@Controller("adminProductController")
@RequestMapping(value = "/admin/product")
public class AdminProductControllerImpl implements AdminProductController {
	
	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private ImageController imageController;
	@Autowired
	private ProductService productService;
	
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
		
		ModelAndView mav = new ModelAndView();
		
		String product_id = adminProductService.addProduct(productVO);
		String message = "["+product_id+"]�� �ӽõ���� �Ϸ�Ǿ����ϴ�.";
		HttpSession session=multipartRequest.getSession();
		session.removeAttribute("message");
		session.setAttribute("message", message);
		/* mav.addObject("message", message); */
		mav.setViewName("redirect:/admin/product/list.do");
		
		imageController.ImageSetImageVO(multipartRequest, product_id);

		// ��ϵ� ��ǰ �̹��� ���� ����
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/list.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectExtraList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// product_states�� 0�� Product�� ȣ��
		Map<String, Map<String, Object>> extraList = productService.selectProductList("0");
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.addObject("extraList", extraList);
		
		mav.setViewName(viewName);
		

		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/update_amount.do", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/text; charset=UTF-8" )
	public String update_amount(@RequestParam Map<String, String> info) throws Exception {
		
		adminProductService.updateAmount(info);
		String title = info.get("product_title");
		String amount = info.get("product_amount");
		String message = "["+title+"]�� ��� ������ ["+amount+"]���� ���������� ����Ǿ����ϴ�.";
		
		System.out.printf("baorip : [%s]�� ��� ������ [%s]�� ����Ǿ����ϴ�.%n", title, amount);
				

		return message;
	}
	
	@Override
	@RequestMapping(value = "/delete_product.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView delete_product(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		adminProductService.deleteProduct(product_id);
		String message = "�ش� ��ǰ�� ���������� ���� �Ǿ����ϴ�.";
		HttpSession session=request.getSession();
		session.removeAttribute("message");
		session.setAttribute("message", message);
		
		mav.addObject("message", message);
		mav.setViewName("redirect:/admin/product/list.do");
		

		return mav;
	}
	
}
