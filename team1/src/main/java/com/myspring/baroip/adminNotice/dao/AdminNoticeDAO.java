package com.myspring.baroip.adminNotice.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface AdminNoticeDAO {
	
//	������������ ��������������
	public List<NoticeVO> AdminNTList() throws DataAccessException;

}
