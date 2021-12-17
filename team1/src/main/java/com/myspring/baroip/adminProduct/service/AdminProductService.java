// 2021.12.09 ������

package com.myspring.baroip.adminProduct.service;

import java.util.Map;

import com.myspring.baroip.product.vo.ProductVO;

public interface AdminProductService {
	
	// ��ǰ ��� ����
	public String addProduct(ProductVO productVO) throws Exception;
	
	// ��ǰ ���� ���� ����
	public void updateAmount(Map<String, String> option) throws Exception;
	
	// ��ǰ ���� ����
	public void deleteProduct(String product_id) throws Exception;
}
