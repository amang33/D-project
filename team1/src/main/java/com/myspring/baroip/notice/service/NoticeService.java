// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.service;

import java.util.List;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface NoticeService {
	
//	�������� ����Ʈ������
	public List<NoticeVO> NTList() throws Exception;
	
//	�������� ��
	public NoticeVO NoticeDetail(String notice_id) throws Exception;
	

}
