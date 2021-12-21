package com.myspring.baroip.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.cart.dao.CartDAO;
import com.myspring.baroip.cart.vo.CartVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public Map<String ,List> myCartList(CartVO cartVO) throws Exception {
		Map<String,List> cartMap=new HashMap<String,List>();
		List<CartVO> myCartList=cartDAO.selectCartList(cartVO);
		if(myCartList.size()==0){ //īƮ�� ����� ��ǰ�̾��� ���
			return null;
		}
		List<ProductVO> myGoodsList=cartDAO.selectGoodsList(myCartList);
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList",myGoodsList);
		return cartMap;
	}
	

}
