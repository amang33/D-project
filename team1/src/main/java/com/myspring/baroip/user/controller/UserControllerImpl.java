// 2021.12.04 �Ѱ���
package com.myspring.baroip.user.controller;

import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.user.naver.NaverLoginBO;
import com.myspring.baroip.user.service.UserService;
import com.myspring.baroip.user.vo.UserVO;


@Controller("userController")
@RequestMapping(value="/user")
public class UserControllerImpl implements UserController{
	@Autowired
	private UserService userService;
	@Autowired
	private UserVO userVO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
//		user ��ü���� ����
		@RequestMapping(value= "/*" ,method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			String viewName = (String)request.getAttribute("viewName");
			mav.setViewName(viewName);
			return mav;
		}
	
//	�α���
	@Override
	@RequestMapping(value="/login.do" ,method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> userMap,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			HttpSession session=request.getSession();
			userVO=userService.login(userMap);
		 // �޾ƿ� userVo�� ��ȿ�� ����
		 if(userVO!= null && userVO.getUser_id()!=null) {
			// ���� ����
			session=request.getSession();
			// ���ӿ��� ���� set
			session.setAttribute("loginOn", true);
			// ȸ������ ���� set
			session.setAttribute("userInfo",userVO);

			mav.setViewName("redirect:/main.do");
			System.out.printf("baroip : ����[%s]������ [%s]���� �α��� �ϼ̽��ϴ�.%n", userVO.getUser_rank(), userVO.getUser_id());
		}
		else {
			String message = "���̵�  ��й�ȣ�� Ʋ���ϴ�. �ٽ� �α������ּ���.";
			mav.addObject("message", message);
			mav.setViewName("/user/login_01");
		}
		return mav;
	}
	
//	���̹� �α��ν� �ʿ� ��
	@RequestMapping(value = "login_01.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView naverLogin(HttpSession session, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
	//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
	//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		//���̹�
		mav.addObject("url", naverAuthUrl);
		mav.setViewName(viewName);
		return mav;
	}
	
//	�α׾ƿ�
	@Override
	@RequestMapping(value = "/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("loginOn");
		session.removeAttribute("userInfo");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}
	
//	ȸ������
	@Override
	@RequestMapping(value="/addUser.do" ,method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("userVO") UserVO userVO,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String user_name = userService.addUser(userVO);
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("user_name", user_name);
//			System.out.println(user_name);
			mav.setViewName("/user/join_03");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
//	�ڵ�����ȣ ����
	@Override
	@RequestMapping(value= "/userMobileCheck.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public int userMobileCheck(@RequestParam("mobile") String mobile, HttpServletRequest request, HttpServletResponse response)throws Exception {
		int randomNumber = (int)((Math.random() * (9999 - 1000 * 1)) + 1000);
		userService.userPhoneCheck(mobile, randomNumber);
		return randomNumber;
	}
	
//	�̸��� ����
	@RequestMapping(value="/emailCheck.do",method={RequestMethod.POST,RequestMethod.GET})
	public int emailCheck(@RequestParam("user_email") String user_email, HttpServletRequest request, HttpServletResponse response)throws Exception {
		String subject = "�ٷ��� ȸ������ �̸��� ����";
		String content = "";
		String from = "baroipweb@gmail.com";
		String to = user_email;
		int randomNumber = (int)((Math.random() * (9999 - 1000 * 1)) + 1000);
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");;
			content = "�ٷ��� ȸ�������� ����帳�ϴ�. �̸��� ���� ��ȣ�� " + randomNumber + " �Դϴ�.";
            mailHelper.setFrom(from);
            // �� ���̵� ������ ���� �ܼ��� smtp ������ �ޱ� ���� ��� ���� ��������(setFrom())�ݵ�� �ʿ�
            // �������̿� �����ּҸ� �����ϴ��̰� ���� ��� ǥ�� �ǰ� ���ϽŴٸ� �Ʒ��� �ڵ带 ����Ͻø� �˴ϴ�.
            //mailHelper.setFrom("�������� �̸� <�������� ���̵�@�������ּ�>");
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            // true�� html�� ����ϰڴٴ� �ǹ��Դϴ�.
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
		return randomNumber;
	}
	
////	��ȸ�� �ֹ��� ���̵� ����
////	@Override
//	@RequestMapping(value="/guestLogin.do" ,method = RequestMethod.POST)
//	public String guestLogin (HttpServletRequest request, HttpServletResponse response) throws Exception {
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("utf-8");
////		ModelAndView mav = new ModelAndView();
//		HttpSession session=request.getSession();
////		String lastViewName = (String)request.getAttribute("lastViewName");
//		userService.createGuestId();
//		session.setAttribute("loginOn", true);
//		session.setAttribute("userInfo",userVO);
//		return "guest";
//	}
	
//	���̵� �ߺ� �˻�
	@Override
	@RequestMapping(value="/userIdOverlap.do" ,method = RequestMethod.POST)
	public ResponseEntity userIdOverlap(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEntity = null;
		String result = userService.userIdOverlap(id);
		resEntity =new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
	
}
