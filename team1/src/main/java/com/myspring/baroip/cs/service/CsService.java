package com.myspring.baroip.cs.service;

import java.util.List;
import java.util.Map;

import com.myspring.baroip.cs.vo.CsVO;

public interface CsService {
	
//	���� ���� ���� ����Ʈ
	public List<CsVO> QAList() throws Exception;
	
//	1:1 ���� ����Ʈ
	public List<CsVO> questList() throws Exception;
	
//	1:1���� �ۼ�
	public void addNewQuest(CsVO csVO) throws Exception;

//	1:1 ���� ��
	public String questDetail() throws Exception;
}
