package com.myspring.baroip.notice.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface NoticeDAO {
	
//	�������� ����Ʈ������
	public List<NoticeVO> NTList() throws DataAccessException;

}
