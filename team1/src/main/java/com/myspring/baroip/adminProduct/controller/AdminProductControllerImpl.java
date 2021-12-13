// 2021.12.08 ������

package com.myspring.baroip.adminProduct.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.adminProduct.service.AdminProductService;
import com.myspring.baroip.image.controller.ImageController;
import com.myspring.baroip.product.vo.ProductVO;
import com.myspring.baroip.user.vo.UserVO;

@Controller("adminProductController")
@RequestMapping(value = "/admin/product")
public class AdminProductControllerImpl implements AdminProductController {
	
	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private ImageController imageController;
	

	// ��ǰ���� ������ ��ü mapping
	@Override
	@RequestMapping(value = "/*", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView adminProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		
		UserVO userVO = (UserVO)session.getAttribute("userInfo");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("user_id", userVO.getUser_id());
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
		mav.addObject("message", message);
		mav.setViewName("/admin/product/list");
		
		imageController.ImageSetImageVO(multipartRequest, product_id);

		// ��ϵ� ��ǰ �̹��� ���� ����
		
		

		

		return mav;
	}
}
