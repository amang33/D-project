// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.baroip.image.service.ImageService;
import com.myspring.baroip.image.vo.ImageVO;
import com.myspring.baroip.notice.dao.NoticeDAO;
import com.myspring.baroip.notice.vo.NoticeVO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private ImageService imageService;
	
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
//	��ǰ�ı�
	@Override
	public Map<String, Object> productComment(String product_id) throws Exception {
		List<NoticeVO> productCommentList = noticeDAO.selectCommentList(product_id);
		List<ImageVO> productImg = imageService.selectImgOne(product_id);
		
		Map<String, Object> commentList = new HashMap<String, Object>();
		
		productImg.get(0).getImage_category().equals("main");
		
		commentList.put("noticeList", productCommentList);
		commentList.put("productImg", productImg);
		
		return commentList;
		
	}
	

}