// 2021.12.28 ������

package com.myspring.baroip.adminUser.service;

import java.util.List;
import java.util.Map;

import com.myspring.baroip.user.vo.UserVO;

public interface AdminUserService {

	// ȸ�� ��ü ����Ʈ ��ȸ
	public List<UserVO> userList (Map<String, String> option) throws Exception;
}
