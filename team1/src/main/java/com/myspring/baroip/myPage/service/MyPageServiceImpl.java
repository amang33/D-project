// 2022.01.24 ������

package com.myspring.baroip.myPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.baroip.myPage.dao.MyPageDAO;
import com.myspring.baroip.user.vo.UserVO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	private MyPageDAO myPageDAO;
	
	// user_id �� �ش��ϴ� cart�� ��ü ���� select Service
	@Override
	public int myPageCartCount(UserVO userVO) throws Exception {
		int cartCount = myPageDAO.myPageCartCount(userVO);
				
		return cartCount;
	}
	
	// user_id �� �ش��ϴ� order�� ��ۿϷ� ��ǰ�� ��ü ���� select Service
	@Override
	public int myPageOrderCount(UserVO userVO) throws Exception {
		int orderCount = myPageDAO.myPageOrderCount(userVO);
				
		return orderCount;
	}


}
