// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface NoticeController {
	
//	�������� ����Ʈ ������
	public ModelAndView notice_01(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

//	�������� ��
	public ModelAndView notice_detail(@RequestParam("NoticeVO") String notice_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
