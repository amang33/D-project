// 2022.01.10 ������

package com.myspring.baroip.adminNotice.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminNoticeController {
	
//	������������ �������� ��Ʈ�ѷ�
	public ModelAndView adminNotice(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
