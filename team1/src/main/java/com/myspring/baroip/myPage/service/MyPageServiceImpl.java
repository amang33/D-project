// 2022.01.24 ������

package com.myspring.baroip.myPage.service;

import java.util.List;
import java.util.Map;

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
	
	// ȸ������ ���� ����
	@Override
	public int updateMyInfo(UserVO userVO) throws Exception {
		
		int flag = myPageDAO.updateMyInfo(userVO);
		
		return flag;
	}
	
	// ��ȸ ���ǿ� ���� �ֹ� ����Ʈ ��ȸ ����
	@Override
	public List<Map<String, Object>> myOrder( Map<String, String> option) throws Exception {
		
		
		
		if(option.get("search_option") != null && option.get("search_option").equals("orderDate")) {
			String[] date = option.get("search_value").split(",");
		
			option.remove("search_value");
			option.put("begin", date[0]);
			option.put("end", date[1]);
 
		}

		List<Map<String, Object>> orderList = myPageDAO.myOrder(option);
		
		return orderList;
	}

	// �ֹ����� ���� ����
	@Override
	public void updateOrder(Map<String, String> option) throws Exception {
		
		myPageDAO.updateOrder(option);
		
	}

}
