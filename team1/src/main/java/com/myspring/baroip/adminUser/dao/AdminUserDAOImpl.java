// 2021.12.28 ������

package com.myspring.baroip.adminUser.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.baroip.user.vo.UserVO;

@Repository("adminUserDAO")
public class AdminUserDAOImpl implements AdminUserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// ��ǰ ��� DAO, ��ȯ�� == ��ϵ� product_id
	@Override
	public List<UserVO> userList (Map<String, String> option) throws DataAccessException {
		
		List<UserVO> allUserList = sqlSession.selectList("mapper.adminUser.userList", option);
						
		return allUserList;
	}
	
}
