package com.myspring.baroip.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.user.dao.UserDAO;
import com.myspring.baroip.user.vo.UserVO;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO login(Map  loginMap) throws Exception {
		return userDAO.login(loginMap);
	}
	
	@Override
	public void addUser(UserVO userVO) throws Exception {
		userDAO.insertNewUser(userVO);
	}
	
	@Override
	public String userIdOverlap(String id) throws Exception{
		return userDAO.selectIdOverlap(id);
	}

}
