// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.service;

import java.util.List;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface NoticeService {
	
//	�������� ����Ʈ������
	public List<NoticeVO> noticeList() throws Exception;
	
//	�������� ��
	public NoticeVO noticeDetail(String notice_id) throws Exception;
	

}
