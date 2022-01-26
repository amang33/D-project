// 2021.12.24 �Ӽ���

package com.myspring.baroip.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.baroip.notice.vo.NoticeVO;

@Repository("noticeDAO")
public class NoticeDAOImpl implements NoticeDAO {
	@Autowired
	private SqlSession sqlSession;
	
//	�������� ����Ʈ
	@Override
	public List<NoticeVO> noticeList() throws DataAccessException{
		List<NoticeVO> NTList = sqlSession.selectList("mapper.notice.noticeList");
		return NTList;
	}
	
//	�������� ��
	@Override
	public NoticeVO noticeDetail(String notice_id) throws DataAccessException {
		NoticeVO noticeVO = sqlSession.selectOne("mapper.notice.noticeDetail", notice_id);
		return noticeVO;
	}
	
//	��ǰ�ı�
	@Override
	public List<NoticeVO> selectCommentList(String product_id) throws DataAccessException {
		List<NoticeVO> commentList = sqlSession.selectList("mapper.notice.selectProductComment", product_id);
		System.out.println("DAO : " + commentList);
		return commentList;
	}

}
