package com.myspring.baroip.user.service;

import java.util.Map;

import com.myspring.baroip.user.vo.UserVO;

public interface UserService {
	public UserVO login(Map loginMap) throws Exception;
	
	public String addUser(UserVO _userVO) throws Exception;
	
	public String userIdOverlap(String id) throws Exception;

//	��ȸ�� �ֹ��� ���̵� ����
	public String guestJoin() throws Exception;
	
//	naver�α��� �� ���̵� ����
	public void naverLogin(UserVO userVO) throws Exception;
	
//	�ڵ��� ����
	public void userPhoneCheck(String mobile, int randomNumber);
	
//	���̵� ã��
	public String userIdFind(UserVO userVO) throws Exception;
}
