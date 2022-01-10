package com.myspring.baroip.cs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.cs.vo.CsVO;

public interface CsDAO {
//	���� ���� ���� ����Ʈ
	public List<CsVO> QAList() throws DataAccessException;
//	1:1 ���� ����Ʈ
	public List<CsVO> questList() throws DataAccessException;
//	1:1 ���� �ۼ�
	public void insertNewQuest(CsVO csVO) throws DataAccessException;
//	1:1 ���� ��
	public CsVO questDetail(String noticeId) throws DataAccessException;
//	1:1 ���� ����
	public void updateQuest(CsVO csVO) throws  DataAccessException;
//	1:1 ���� ����
	public void questDelete(String notice_id) throws DataAccessException;
}
