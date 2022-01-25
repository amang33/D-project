package com.myspring.baroip.user.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.user.vo.UserVO;

public interface UserDAO {
	public UserVO login(Map loginMap) throws DataAccessException;
	
	public String insertNewUser(UserVO userVO) throws DataAccessException;
	
	public String selectIdOverlap(String id) throws DataAccessException;
	
//	��ȸ�� �ֹ��� ���̵� ����
	public String insertGuestId() throws DataAccessException;

//	naver�α��� ���̵� ����
	public void addNaverUser(UserVO userVO) throws DataAccessException;
	
//	���̵� ã��
	public String userIdFind(UserVO userVO) throws DataAccessException;
	
//	��й�ȣ ã�� �� ȸ�� ���� ��ġ Ȯ��
	public String inputUserCheck(UserVO userVO) throws DataAccessException;
	
}
