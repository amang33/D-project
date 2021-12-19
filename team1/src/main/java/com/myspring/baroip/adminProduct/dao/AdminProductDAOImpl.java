// 2021.12.09 ������

package com.myspring.baroip.adminProduct.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.baroip.product.vo.ProductVO;

@Repository("adminProductDAO")
public class AdminProductDAOImpl implements AdminProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// ��ǰ �ӽõ��
	@Override
	public String insertProduct(ProductVO productVO) throws DataAccessException {
		
		sqlSession.insert("mapper.adminProduct.insertProduct",productVO);
				
		String product_id = "product_"+sqlSession.selectOne("mapper.adminProduct.selectMatchID");
		
		return product_id;
	}
	
	// ��ǰ ���� ����
	@Override
	public void updateAmount(Map<String, String> option) throws DataAccessException {
		sqlSession.update("mapper.adminProduct.updateAmount", option);
		
	}
	
	// ��ǰ ����
	@Override
	public void deleteProduct(String product_id) throws DataAccessException {
		sqlSession.delete("mapper.adminProduct.deleteProduct", product_id);
		sqlSession.delete("mapper.adminProduct.deleteImage", product_id);
		
	}
}
