// 2022.01.24 ������

package com.myspring.baroip.myPage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.user.vo.UserVO;

public interface MyPageDAO {

	// user_id �� �ش��ϴ� cart�� ��ü ���� select DAO
	public int myPageCartCount(UserVO userVO) throws DataAccessException;

	// user_id �� �ش��ϴ� order�� ��ۿϷ� ��ǰ�� ��ü ���� select DAO

	public int myPageOrderCount(UserVO userVO) throws DataAccessException;
	
	// ȸ������ ���� DAO
	public int updateMyInfo(UserVO userVO) throws DataAccessException;
	
	// ��ȸ ���ǿ� ���� �ֹ� ����Ʈ ��ȸ DAO
	public List<Map<String, Object>> myOrder( Map<String, String> option) throws DataAccessException;
	
	// ����Ȯ�� DAO
	public void deliveryCompleted(Map<String, String> option) throws DataAccessException;
}
