// 2022.01.14 ������

package com.myspring.baroip.order.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.cart.vo.CartVO;

public interface OrderDAO {
	
	public int selectCount(CartVO cartVO) throws DataAccessException;

}
