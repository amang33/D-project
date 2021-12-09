// 2021.12.09 ������

package com.myspring.baroip.adminProduct.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.baroip.product.vo.ProductVO;

@Repository("adminProductDAO")
public class AdminProductDAOImpl implements AdminProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public String insertProduct(ProductVO productVO) throws DataAccessException {
		// product insert query
		sqlSession.insert("mapper.adminProduct.insertProduct",productVO);
		
		// �߰��� product ��ǰ�� ��ȯ
		String addProductName = productVO.getProduct_main_title();
		
		return addProductName;
	}

}
