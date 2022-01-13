// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.baroip.notice.dao.NoticeDAO;
import com.myspring.baroip.notice.vo.NoticeVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
//	�������� ����Ʈ������
	public List<NoticeVO> noticeList() throws Exception {
		List<NoticeVO> NTList = noticeDAO.noticeList();
		return NTList;
	}
	
//	�������� ��
	@Override
	public NoticeVO noticeDetail(String notice_id) throws Exception {
		NoticeVO noticeVO = noticeDAO.noticeDetail(notice_id);
		return noticeVO;
	}
	

}