// 2022.01.24 ������

package com.myspring.baroip.myPage.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.myPage.service.MyPageService;
import com.myspring.baroip.user.vo.UserVO;



@Controller("myPageController")
@RequestMapping(value="/myPage")
public class MyPageControllerImpl implements MyPageConroller{
	
	@Autowired
	private MyPageService myPageService;

	

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
	public ModelAndView user_mypage_02(@RequestParam("user_pw") String user_pw, HttpServletRequest request, HttpServletResponse response) throws Exception{
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


	
//	
	
//	
//	// ȸ��Ż��
//	@RequestMapping(value= "/dropOut_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView dropOut_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ȸ��Ż�� �Ϸ�
//	@RequestMapping(value= "/dropOut_02.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView dropOut_02(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//
//
//	@RequestMapping(value= "/myPage_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	

//	// ȸ������ ���� �Է�
//	@RequestMapping(value= "/myPage_02_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_02_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// �ֹ� ��� ��ȸ
//	@RequestMapping(value= "/myPage_03.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_03(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// �ֹ� �� ����
//	@RequestMapping(value= "/myPage_03_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_03_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ��ǰ ��ȯ ��û
//	@RequestMapping(value= "/myPage_03_02.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_03_02(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ��ǰ �ı�
//	@RequestMapping(value= "/myPage_03_03.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_03_03(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ����Ʈ ����
//	@RequestMapping(value= "/myPage_04.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_04(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ȸ����� �ȳ�
//	@RequestMapping(value= "/myPage_05.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_05(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ���� ����
//	@RequestMapping(value= "/myPage_06.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_06(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ���� ���� ��
//	@RequestMapping(value= "/myPage_06_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_06_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	// ��ǰ �ı�
//	@RequestMapping(value= "/myPage_07.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_07(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}
//	
//	
//	// ��ǰ �ı� ����
//	@RequestMapping(value= "/myPage_07_01.do" ,method={RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView user_mypage_07_01(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		// HttpSession session;
//		ModelAndView mav = new ModelAndView();
//		String viewName = (String)request.getAttribute("viewName");
//		mav.setViewName(viewName);
//		return mav;
//	}



	
}
