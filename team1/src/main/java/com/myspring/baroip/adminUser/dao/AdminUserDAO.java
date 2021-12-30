// 2021.12.28 ������

package com.myspring.baroip.adminUser.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.user.vo.UserVO;

public interface AdminUserDAO {
	
	// option�� ���� ȸ�� ��� ��ȸ DAO
	public List<UserVO> userList (Map<String, String> option) throws DataAccessException;
	
	// ȸ�� ���� ���� DAO
	public String updateRank (Map<String, String> option) throws DataAccessException;
	
	// ȸ�� ���� DAO
	public String deleteUser(Map<String, String> info) throws DataAccessException;
	
	// ȸ�� ���� DAO
	public void updateUser(UserVO userVO) throws DataAccessException;
	

}
