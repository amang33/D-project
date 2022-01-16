// 2022.01.14 ������

package com.myspring.baroip.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.baroip.cart.vo.CartVO;
import com.myspring.baroip.order.dao.OrderDAO;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDAO;
	
	// ��ǰ�� ���� īƮ ���� ��ȸ Service
	@Override
	public int selectCount(CartVO cartVO) throws Exception {
		
		int count = orderDAO.selectCount(cartVO);

		return count;

	}

}
