// 2021.12.09 ������

package com.myspring.baroip.adminProduct.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.adminProduct.dao.AdminProductDAO;
import com.myspring.baroip.product.vo.ProductVO;

@Service("adminProductService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminProductServiceImpl implements AdminProductService {
	
	@Autowired
	private AdminProductDAO adminProductDAO;
	
	// ��ǰ ��� ����
	@Override
	public String addProduct(ProductVO productVO) throws Exception {
		
		String product_id = adminProductDAO.insertProduct(productVO);
		
		return product_id;
	}
	
	// ��ǰ ���� ���� ����
	@Override
	public void updateAmount(Map<String, String> option) throws Exception {
		
		adminProductDAO.updateAmount(option);
		
	}
	
	// ��ǰ ���� ����
	@Override
	public void deleteProduct(String product_id) throws Exception {
		
		adminProductDAO.deleteProduct(product_id);
		
	}
	
	// ��ǰ ���� ����
	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		
		adminProductDAO.updateProduct(productVO);
		
	}
	
	// ��¥�� �������� �� ��ǰ ��ȸ ����
	@Override
	public Map<String, Map<String, Object>> productListToOption( Map<String, String> option) throws Exception {
		
		List<ProductVO> productList = adminProductDAO.productListToOption(option);
	}
}
