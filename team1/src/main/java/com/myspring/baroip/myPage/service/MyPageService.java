// 2022.01.24 ������

package com.myspring.baroip.myPage.service;

import java.util.List;
import java.util.Map;

import com.myspring.baroip.user.vo.UserVO;

public interface MyPageService {

	// user_id �� �ش��ϴ� cart�� ��ü ���� select Service
	public int myPageCartCount(UserVO userVO) throws Exception;

	// user_id �� �ش��ϴ� order�� ��ۿϷ� ��ǰ�� ��ü ���� select Service
	public int myPageOrderCount(UserVO userVO) throws Exception;
	
	// ȸ������ ���� ����
	public int updateMyInfo(UserVO userVO) throws Exception;

	// ��ȸ ���ǿ� ���� �ֹ� ����Ʈ ��ȸ ����
	public List<Map<String, Object>> myOrder( Map<String, String> option) throws Exception;
	
	// ���� Ȯ�� ����
	public void deliveryCompleted(Map<String, String> option) throws Exception;
}
