package com.myspring.baroip.adminNotice.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface AdminNoticeService {
	
//	������������ ��������������
	public List<NoticeVO> AdminNTList() throws Exception;

}
