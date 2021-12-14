package com.myspring.baroip.product.service;


import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.image.service.ImageService;
import com.myspring.baroip.image.vo.ImageVO;
import com.myspring.baroip.product.dao.ProductDAO;
import com.myspring.baroip.product.vo.ProductVO;

@Service("productService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ImageService imageService;

	// best product select
	@Override
	public Map<String, Map<String, Object>> bestProductList() throws Exception {

		// ����Ʈ ��ǰ ����Ʈ ����
		List<ProductVO> bestProducts = productDAO.selectBestProduct();
		// ����ȭ�鿡 ȣ���� ������ ��ǰ���� + �̹����� ���� ��ü ���� (mainProduct 1~3)
		Map<String, Map<String, Object>> bestProductInfo = new HashMap<String, Map<String, Object>>();
		// �̹��� ȣ���� ���� option Map ��ü ����
		Map<String, String> option = new HashMap<String, String>();
			

		for (int i = 0; i < bestProducts.size(); i++) {

			ProductVO product = bestProducts.get(i);

			if (product != null) {

				String match_id = product.getProduct_id();
				String image_category = "main";


				option.put("match_id", match_id);
				option.put("image_category", image_category);

				// �ش� ��ǰ�� ������ ���� �̹��� ȣ��
				ImageVO productImage = imageService.selectProductImage(option);
				// byte�� img�� ��ȯ�ϱ� ���� encode
				
				// ��ǰ ����� �̹����� ���� ��ü ����
				Map<String, Object> productInfo = new HashMap<String, Object>();
				
				// byte[] �ڷḦ img �±׿� ��밡���ϵ��� encode
				String encodeImage = Base64.getEncoder().encodeToString(productImage.getImage_file());
				
				productInfo.put("product_main_title", product.getProduct_main_title());
				productInfo.put("product_sub_title", product.getProduct_sub_title());
				productInfo.put("product_price", product.getProduct_price());
				productInfo.put("product_discount", product.getProduct_discount());
				productInfo.put("image_file", encodeImage);
				productInfo.put("product_id", product.getProduct_id());

				bestProductInfo.put("mainProduct" + (i+1), productInfo);
				
			}

		}

		return bestProductInfo;

	}
	
	// product detail ��ȸ service
	@Override
	public Map<String, Object> productDetail(String product_id) throws Exception {
		
		// �̹������� / ��ǰ������ ���� ��ü ����
		Map<String, Object> productInfo= new HashMap<String, Object>();
		// �̹��� ������ �ҷ��� option ��ü ����
		Map<String, String> option = new HashMap<String, String>();
			
		option.put("match_id", product_id);
		
		// ī�װ� ������ ���� ��ü ���� �� ��ǰ�� �ش��ϴ� �̹��� ī�װ� ����
		List<String> categoryList = imageService.selectImageCategory(product_id);
		
		for (int i = 0 ; i<categoryList.size() ; i++) {
			option.put("image_category", categoryList.get(i));
			ImageVO productImage = imageService.selectProductImage(option);
			
			String encodeImage = Base64.getEncoder().encodeToString(productImage.getImage_file());
			
			productInfo.put(categoryList.get(i), encodeImage);
			
			
		}
		
		// ��ǰ ���� ����
		productInfo.put("productVO", productDAO.selectProduct(product_id));
		
		return productInfo;
		
		
		
		
		
		
		
	}
}
