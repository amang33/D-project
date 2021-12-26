// 2021.12.08 ������

package com.myspring.baroip.adminProduct.controller;

import java.util.HashMap;
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
	public ModelAndView addProduct(@ModelAttribute("productVO") ProductVO productVO,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();

		String product_id = adminProductService.addProduct(productVO);
		String message = "[" + product_id + "]�� �ӽõ���� �Ϸ�Ǿ����ϴ�.";
		HttpSession session = multipartRequest.getSession();
		session.setAttribute("message", message);

		mav.setViewName("redirect:/admin/product/list.do");
		System.out.println("baroip : " + message);
		imageController.ImageSetImageVO(multipartRequest, product_id);

		// ��ϵ� ��ǰ �̹��� ���� ����
		return mav;
	}

	@Override
	@RequestMapping(value = "/extra_list.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView extraList(@RequestParam Map<String, String> info, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String paramOption = info.get("option");
		String sessionOption = (String) session.getAttribute("option");
		
		if (paramOption == null && sessionOption == null) {
			info.put("option", "all");
			info.put("value", "0");
		}
		
		Map<String, Map<String, Object>> extraFullList = getFullList(info, request);
		

		
		String pageNo = info.get("pageNo");
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		
		if (pageNo != null && pageNo != "") {
			mav.addObject("pageNo", pageNo);
		} else {
			mav.addObject("pageNo", 1);
		}
		mav.addObject("extraList", extraFullList);
		mav.setViewName(viewName);

		return mav;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/update_amount.do", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/text; charset=UTF-8")
	public String update_amount(@RequestParam Map<String, String> info) throws Exception {

		adminProductService.updateAmount(info);
		String title = info.get("product_title");
		String amount = info.get("product_amount");
		String message = "[" + title + "]�� ��� ������ [" + amount + "]���� ���������� ����Ǿ����ϴ�.";

		System.out.printf("baorip : [%s]�� ��� ������ [%s]�� ����Ǿ����ϴ�.%n", title, amount);

		return message;
	}

	@Override
	@RequestMapping(value = "/delete_product.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView delete_product(@RequestParam("product_id") String product_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		adminProductService.deleteProduct(product_id);

		System.out.printf("baroip : [%s]��ǰ�� ���������� �����Ǿ����ϴ�.%n", product_id);
		mav.setViewName("redirect:/admin/product/list.do");

		return mav;
	}

	@Override
	@RequestMapping(value = "/update_product_form.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView update_product_form(@RequestParam("product_id") String product_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");

		Map<String, Map<String, Object>> productInfo = productService.productDetail(product_id);

		mav.addObject("productInfo", productInfo);
		mav.setViewName(viewName);
		return mav;

	}

	@Override
	@RequestMapping(value = "/update_product.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView update_product(@ModelAttribute("productVO") ProductVO productVO,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();

		adminProductService.updateProduct(productVO);
		String message = "������ " + productVO.getUser_id() + " ���� [" + productVO.getProduct_main_title()
				+ "]�� ������ �Ϸ�Ǿ����ϴ�.";

		HttpSession session = multipartRequest.getSession();
		session.setAttribute("message", message);
		imageController.updateImage(multipartRequest, productVO.getProduct_id());
		mav.setViewName("redirect:/admin/product/list.do");
		System.out.println("baroip : " + message);

		return mav;
	}

	public Map<String, Map<String, Object>> getFullList(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception {
		// Map options���� ��ȸ�ϰ��� �ϴ� �������� option, ���ǿ� �ش��ϴ� value �� �ݵ�� ���ԵǾ���Ѵ�.

		HttpSession session = request.getSession();
		// key "option" = value [productCreDate / productTitle / all]
		// key "value" = value [yyyy-mm-dd,yyyy-mm-dd / product_main_title / 0 or 1(product_states) ]
		Map<String, String> options = new HashMap<String, String>();
		String paramOption = info.get("option");
		String paramValue = info.get("value");
		String sessionOption = (String) session.getAttribute("option");
		String sessionValue = (String) session.getAttribute("value");

		// param, session ��� option�� ���ε� �Ǿ��ִ� ���
		if (paramOption != null && sessionOption != null) {

				// �� �ɼ��� ��ġ�� ���, options�� ���� session�� ���� �����Ѵ�.
				if (paramOption.equals(sessionOption)) {
					options.put("option", sessionOption);
					options.put("value", sessionValue);
				}

				// �� �ɼ��� ��ġ���� ���� ���, options�� paramOption�� �����ϰ�, ���� ������ Override �Ѵ�.
				else {
					options.put("option", paramOption);
					options.put("value", paramValue);

					session.setAttribute("option", paramOption);
					session.setAttribute("value", paramValue);
				}
			}

			// ���ǿ� ���ε��� option�� �������, options�� paramOption�� �����ϰ�, ���ǿ� set �Ѵ�.
			else if (paramOption != null && sessionOption == null) {
				options.put("option", paramOption);
				options.put("value", paramValue);

				session.setAttribute("option", paramOption);
				session.setAttribute("value", paramValue);
			}
		
		// param�� ���ε��� option�� �������, session�� option�� �����Ѵ�.
		else if (paramOption == null && sessionOption != null) {
			options.put("option", sessionOption);
			options.put("value", sessionValue);
		}
		
		Map<String, Map<String, Object>> fullList = adminProductService.productListToOption(options);
		
		return fullList;
	}

}
