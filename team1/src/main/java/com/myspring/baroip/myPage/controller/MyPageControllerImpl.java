// 2022.01.24 ������

package com.myspring.baroip.myPage.controller;


import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.myPage.service.MyPageService;
import com.myspring.baroip.user.service.UserService;
import com.myspring.baroip.user.vo.UserVO;



@Controller("myPageController")
@RequestMapping(value="/myPage")
public class MyPageControllerImpl implements MyPageConroller{
	
	@Autowired
	private MyPageService myPageService;
	@Autowired
	private UserService userService;

	

	// ��ü ����
	@RequestMapping(value= "/*.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception{

		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		return mav;
	}
	
	
	// ���������� ���� ��Ʈ�ѷ�
	@RequestMapping(value= "/myInfo.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("userInfo") == null) {
			mav.setViewName("redirect:/main.do");
		}
		
		else {
			
			String viewName = (String)request.getAttribute("viewName");
			UserVO userVO = (UserVO)session.getAttribute("userInfo");
			
			int orderCount = myPageService.myPageOrderCount(userVO);
			int cartCount = myPageService.myPageCartCount(userVO);
			
			mav.addObject("orderCount", orderCount);
			mav.addObject("cartCount", cartCount);
			mav.setViewName(viewName);
				
		}
		

		return mav;
	}
	
	
	// ȸ������ ����
	@RequestMapping(value= "/checkPassword.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView checkPassword(@RequestParam("user_pw") String user_pw, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		ModelAndView mav = new ModelAndView();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		String user_pw_match = userInfo.getUser_pw();
		
		if(user_pw.equals(user_pw_match)) {
			mav.setViewName("/myPage/myPage_02_01");
		} 
		else {
			mav.addObject("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			mav.setViewName("/myPage/myPage_02");
		}

				

		return mav;
	}
	
	// ȸ������ ���� ��Ʈ�ѷ�
	@Override
	@RequestMapping(value = "/update_MyInfo.do", method = RequestMethod.POST)
	public ModelAndView updateMyInfo(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		
		// ȸ������ ���� ������ 1, �ƴҰ�� 0 �� ��ȯ
		int flag = myPageService.updateMyInfo(userVO);
		String message = "";
		if (flag == 1) {
			
			UserVO loginUser = (UserVO) session.getAttribute("userInfo");
			message = "ȸ�� " + loginUser.getUser_id() + " ���� [" + userVO.getUser_id() + "]�� ������ �Ϸ��߽��ϴ�.";
		}
		else if (flag == 0) {
			message = "ȸ������ ������ ������ �߻��Ͽ����ϴ�.";
		}
		
		session.setAttribute("message", message);
		mav.setViewName("redirect:/myPage/myInfo.do");
		System.out.println("baroip : " + message);
		
		return mav;
	}


	@Override
	@RequestMapping(value = "/myOrder.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView myOrder(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		// get ��û�� �������, ������ session�� ����
		if (info.isEmpty()) {
			session.removeAttribute("search_option");
			session.removeAttribute("search_value");
		}
		List<Map<String, Object>> orderList = getFullList(info, request);
		
				
		String pageNo = info.get("pageNo");
		
		if (pageNo != null && pageNo != "") {
			int lastNo = (orderList.size()+4)/5;
			
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
	

	// �ֹ� ��ȸ ���� ����, ���ǿ� �ִ� �ֹ����� Ȯ�� �� ���񽺷� ó���ϴ� �޼ҵ�
	public List<Map<String, Object>> getFullList(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		// Map options���� ��ȸ�ϰ��� �ϴ� �������� option, ���ǿ� �ش��ϴ� value �� �ݵ�� ���ԵǾ���Ѵ�.
		// search_option(�˻� ����) = value [orderDate / productId / userId/ states ]
		// search_value(�˻� ��) = value [yyyy-mm-dd,yyyy-mm-dd / product_id / user_id / 0 or 1 or 2)]
		Map<String, String> options = new HashMap<String, String>();
		
		String paramOption = info.get("search_option");
		String paramValue = info.get("search_value");
		
		String sessionOption = (String) session.getAttribute("search_option");
		String sessionValue = (String) session.getAttribute("search_value");
			
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
		
		UserVO userVO = (UserVO)session.getAttribute("userInfo");
		
		options.put("user_id", userVO.getUser_id());
			
		
		List<Map<String, Object>> fullList = myPageService.myOrder(options);
		
		return fullList;
	}
	
	// �ֹ� ���� ���� ��Ʈ�ѷ�
	@Override
	@ResponseBody
	@RequestMapping(value = "/updateOrder.do", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/text; charset=UTF-8")
	public String updateOrder(HttpServletRequest request, @RequestParam Map<String, String> info) throws Exception {
		HttpSession session = request.getSession();
		
		String point = info.get("point");
		String message = "";
		myPageService.updateOrder(info);
		
		UserVO userVO = (UserVO)session.getAttribute("userInfo");
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("user_id", userVO.getUser_id());
		userMap.put("user_pw", userVO.getUser_pw());
		
		UserVO newUserInfo = userService.login(userMap);
		session.removeAttribute("userInfo");
		session.setAttribute("userInfo", newUserInfo);
		
		if(info.get("update_option").equals("deliveryCompleted")) {
			message = "�ش� �ֹ��� ���°� ����Ȯ������ ���� �Ǿ����ϴ�. ���� ����Ʈ : "+point;
		}
		else if (info.get("update_option").equals("cancelOrder")) {
			message = "�ش� �ֹ��� ���������� ��ҵǾ����ϴ�.";
		}
		else {
			message = "�ش� �ֹ��� ��ǰ/��ȯ ��û�� ���������� �Ϸ�Ǿ����ϴ�.";
		}
		

		return message;
	}
}
