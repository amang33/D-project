package com.myspring.baroip.user.controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.user.vo.UserVO;



public interface UserController {
	
//	�α���
	public ModelAndView login(@RequestParam Map<String, String> loginMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

//	�α׾ƿ�
	public ModelAndView logout(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
//	ȸ������
	public ModelAndView addUser(@ModelAttribute("user") UserVO user,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

//	���̵� �ߺ� �˻�
	public ResponseEntity userIdOverlap(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	�ڵ��� ���� ����
	public int userMobileCheck(@RequestParam("mobile") String mobile)throws Exception;
	
//	�̸��� ����
	public int emailCheck(@RequestParam("user_email") String user_email)throws Exception;
	
//	���̵� ã��
	public ModelAndView userIdFind(@RequestParam("user_name") String user_name, @RequestParam("user_mobile") String user_mobile, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	��й�ȣ ã��
	public int userPwdFind(@RequestParam("user_id") String user_id, @RequestParam("pwdFindType") String pwdFindType) throws Exception;
	
}

	