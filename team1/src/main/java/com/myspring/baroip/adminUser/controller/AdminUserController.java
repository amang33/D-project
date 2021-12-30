// 2021.12.28 ������

package com.myspring.baroip.adminUser.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminUserController {
	
	// ��ü ȸ�� ��� ����Ʈ
	public ModelAndView userList(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// ȸ�� ���� ���� ��Ʈ�ѷ�
	public String updateRank(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception;
	
	// ȸ�� ���� ��Ʈ�ѷ�
	public String delete_user(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception;
}
