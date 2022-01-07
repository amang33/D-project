package com.myspring.baroip.cs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.cs.vo.CsVO;

public interface CsController {
	
//	���� ���� ���� ����Ʈ
	public ModelAndView cs_01(@RequestParam Map<String, String> info, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
//	1:1 ���� ����Ʈ
	public ModelAndView cs_02(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception;

//	1:1 ���� ��
	public ModelAndView quest_datail(@RequestParam("CsVO") String notice_id, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
//	1:1 ���� �ۼ�
	public ModelAndView addQuest(@ModelAttribute("csVO") CsVO csVO, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
//	1:1 ���� ����
	public String quest_Delete(@RequestParam("noticeId") String noticeId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
