package com.myspring.baroip.cart.service;

import java.util.Map;

import com.myspring.baroip.cart.vo.CartVO;

public interface CartService {
	
//	��ٱ��� ������
	public Map<String, Map<String, Map<String, Object>>> myCartList(CartVO cartVO) throws Exception;
	
//	��ٱ��� ���
	public void addProductInCart(CartVO cartVO) throws Exception;
	
}
