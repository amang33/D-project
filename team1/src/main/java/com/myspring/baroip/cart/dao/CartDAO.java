package com.myspring.baroip.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.cart.vo.CartVO;

public interface CartDAO {
	
//	��ٱ��� ������
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;
	
//	��ٱ��� ���
	public void insertProductInCart(CartVO cartVO) throws DataAccessException;
	
//	�ش� ȸ���� ��ٱ��Ͽ� ��ǰ�� �ִ��� Ȯ��
	public boolean selectProductInCart(CartVO cartVO) throws DataAccessException;
	
//	�������� ���� ��ǰ �߰�
	public void overLapCartList(CartVO cartVO) throws DataAccessException;
	
//	��ٱ��� ��ǰ ����
	public void deleteCartList(List<CartVO> deleteList) throws DataAccessException;
}
