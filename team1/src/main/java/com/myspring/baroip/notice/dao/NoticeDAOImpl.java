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
	public List<NoticeVO> NTList() throws DataAccessException{
		List<NoticeVO> NTList = sqlSession.selectList("mapper.notice.NTList");
		return NTList;
	}
	
//	�������� ��
	@Override
	public NoticeVO NoticeDetail(String noticeId) throws DataAccessException {
		return sqlSession.selectOne("mapper.notice.NoticeDetail", noticeId);
	}

}
