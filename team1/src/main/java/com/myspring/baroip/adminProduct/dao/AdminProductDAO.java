// 2021.12.09 ������

package com.myspring.baroip.adminProduct.dao;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.product.vo.ProductVO;

public interface AdminProductDAO {
	public String insertProduct(ProductVO productVO) throws DataAccessException;
}
