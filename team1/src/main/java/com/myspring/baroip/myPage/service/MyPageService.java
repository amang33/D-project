// 2022.01.24 ������

package com.myspring.baroip.myPage.service;

import com.myspring.baroip.user.vo.UserVO;

public interface MyPageService {

	// user_id �� �ش��ϴ� cart�� ��ü ���� select Service
	public int myPageCartCount(UserVO userVO) throws Exception;

	// user_id �� �ش��ϴ� order�� ��ۿϷ� ��ǰ�� ��ü ���� select Service
	public int myPageOrderCount(UserVO userVO) throws Exception;

}
