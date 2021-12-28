// 2021.12.28 ������

package com.myspring.baroip.adminUser.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminUserController")
@RequestMapping(value = "/admin/user")
public class AdminUserControllerImpl implements AdminUserController{

	// rank 2 �������� �ӽû�ǰ���� �޴� ��Ʈ�ѷ�
		@Override
		@RequestMapping(value = "/user_list.do", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView userList(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			
//			 get ��û�� �������, ������ session�� ����
//			if (info.isEmpty()) {
//				session.removeAttribute("search_option");
//				session.removeAttribute("search_value");
//			}
//			Map<String, Map<String, Object>> userList = getFullList(info, request);

			

			
			String pageNo = info.get("pageNo");
			ModelAndView mav = new ModelAndView();
			String viewName = (String) request.getAttribute("viewName");
			
//			if (pageNo != null && pageNo != "") {
//				mav.addObject("pageNo", pageNo);
//			} else {
//				mav.addObject("pageNo", 1);
//			}
//			mav.addObject("userList", userList);
//			mav.setViewName(viewName);

			return mav;
		}
}
