// 2021.12.28 ������

package com.myspring.baroip.adminUser.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.user.vo.UserVO;

public interface AdminUserDAO {
	
	// ��ü ȸ�� ����Ʈ DAO
	public List<UserVO> userList (Map<String, String> option) throws DataAccessException;

}
