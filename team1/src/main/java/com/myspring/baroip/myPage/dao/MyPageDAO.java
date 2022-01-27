// 2022.01.24 ������

package com.myspring.baroip.myPage.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.user.vo.UserVO;

public interface MyPageDAO {

	// user_id �� �ش��ϴ� cart�� ��ü ���� select DAO
	public int myPageCartCount(UserVO userVO) throws DataAccessException;

	// user_id �� �ش��ϴ� order�� ��ۿϷ� ��ǰ�� ��ü ���� select DAO

	public int myPageOrderCount(UserVO userVO) throws DataAccessException;
	
	// ȸ������ ���� DAO
	public int updateMyInfo(UserVO userVO) throws DataAccessException;
}
