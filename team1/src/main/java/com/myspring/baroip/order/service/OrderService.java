// 2022.01.14 ������

package com.myspring.baroip.order.service;

import com.myspring.baroip.cart.vo.CartVO;

public interface OrderService {

	// ��ǰ�� ���� īƮ ���� ��ȸ Service
	public int selectCount(CartVO cartVO) throws Exception;
}
