// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface NoticeService {
	
//	�������� ����Ʈ������
	public List<NoticeVO> NTList() throws Exception;
	
	
	public List<NoticeVO> listArticles() throws Exception;
	public NoticeVO viewArticle(String notice_id) throws Exception;
	public void modArticle(Map articleMap) throws Exception;

}
