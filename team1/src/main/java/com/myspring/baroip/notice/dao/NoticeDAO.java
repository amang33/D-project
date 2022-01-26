// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface NoticeDAO {
	
//	�������� ����Ʈ������
	public List<NoticeVO> noticeList() throws DataAccessException;
	
//	�������� ��
	public NoticeVO noticeDetail(String notice_id) throws DataAccessException;

	public List<NoticeVO> selectCommentList(String product_id) throws DataAccessException;

}
