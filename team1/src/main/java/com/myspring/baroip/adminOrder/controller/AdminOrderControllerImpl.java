// 2021.12.08 ������

package com.myspring.baroip.adminOrder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.adminOrder.service.AdminOrderService;
import com.myspring.baroip.user.vo.UserVO;

@Controller("adminOrderController")
@RequestMapping(value = "/admin/order")
public class AdminOrderControllerImpl implements AdminOrderController {

	@Autowired
	private AdminOrderService adminOrderService;

	// �ֹ����� ������ ��ü mapping
	@Override
	@RequestMapping(value = "/*", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView adminOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value = "/order_list.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView orderList(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		List<Map<String, Object>> orderList = getFullList(info, request);
		
		String pageNo = info.get("pageNo");
		
		if (pageNo != null && pageNo != "") {
			int lastNo = (orderList.size()+8)/9;
			
			if (Integer.parseInt(pageNo) > lastNo) {
				mav.addObject("pageNo", 1);
				mav.setViewName("redirect:"+viewName +".do");
			}
			else {
				mav.addObject("pageNo", pageNo);	
				mav.setViewName(viewName);
			}
			
		} else {
			mav.addObject("pageNo", 1);
			mav.setViewName(viewName);
		}
		mav.addObject("orderList", orderList);
		return mav;

	}
	
	// �ֹ� ���� ���� ��Ʈ�ѷ�
	@Override
	@ResponseBody
	@RequestMapping(value = "/update_state.do", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/text; charset=UTF-8")
	public String update_amount(@RequestParam Map<String, String> info) throws Exception {

		String order_id = info.get("order_id");
		String delivery_id = info.get("delivery_id");
		
		String message = "�ֹ� ��ȣ ["+order_id+"] �� ���°� ��������� ���� �Ǿ����ϴ�.";

		System.out.printf("baorip : [%s]�� ��� ���°� [�����]���� ����Ǿ����ϴ�.%n", order_id);

		return message;
	}


	// ��ǰ ��ȸ ���� ����, ���ǿ� �ִ� ��ǰ������ Ȯ�� �� ���񽺷� ó���ϴ� �޼ҵ�
	public List<Map<String, Object>> getFullList(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		// Map options���� ��ȸ�ϰ��� �ϴ� �������� option, ���ǿ� �ش��ϴ� value �� �ݵ�� ���ԵǾ���Ѵ�.
		// search_option(�˻� ����) = value [productCreDate / productTitle / productStates / productAmount]
		// search_value(�˻� ��) = value [yyyy-mm-dd,yyyy-mm-dd / product_main_title / 0 or 1 or all) / int]
		Map<String, String> options = new HashMap<String, String>();
		
		String paramOption = info.get("search_option");
		String paramValue = info.get("search_value");
		
		String sessionOption = (String) session.getAttribute("search_option");
		String sessionValue = (String) session.getAttribute("search_value");
		
		String viewName = (String) request.getAttribute("viewName");
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		String[] viewSplit = viewName.split("/");
		
		// param, session ��� option�� ���ε� �Ǿ��ִ� ���
		if (paramOption != null && sessionOption != null) {

				// �� �ɼ��� ��ġ�� ���, options�� ���� session�� ���� �����Ѵ�.
				if (paramOption.equals(sessionOption) && paramValue.equals(sessionValue)) {
					options.put("search_option", sessionOption);
					options.put("search_value", sessionValue);
				}

				// �� �ɼ��� ��ġ���� ���� ���, options�� paramOption�� �����ϰ�, ���� ������ Override �Ѵ�.
				else {
					options.put("search_option", paramOption);
					options.put("search_value", paramValue);

					session.setAttribute("search_option", paramOption);
					session.setAttribute("search_value", paramValue);
				}
			}

			// ���ǿ� ���ε��� option�� �������, options�� paramOption�� �����ϰ�, ���ǿ� set �Ѵ�.
			else if (paramOption != null && sessionOption == null) {
				options.put("search_option", paramOption);
				options.put("search_value", paramValue);

				session.setAttribute("search_option", paramOption);
				session.setAttribute("search_value", paramValue);
			}
		
		// param�� ���ε��� option�� �������, session�� option�� �����Ѵ�.
		else if (paramOption == null && sessionOption != null) {
			options.put("search_option", sessionOption);
			options.put("search_value", sessionValue);
		}
		
		// param�� session�� ���ε��� ������ �������, viewName�� ���� ��ü list�� �����ش�.
		else {
			if (viewName.contains("extra")) {
				options.put("search_option", "productStates");
				options.put("search_value", "0");
			}
			
			else if (viewName.contains("general")) {
				options.put("search_option", "productStates");
				options.put("search_value", "all");
			}
		}
		
		options.put("user_rank", userInfo.getUser_rank());
		options.put("view_name", viewSplit[3]);
		List<Map<String, Object>> fullList = adminOrderService.orderListToOption(options);
		
		return fullList;
	}

}
