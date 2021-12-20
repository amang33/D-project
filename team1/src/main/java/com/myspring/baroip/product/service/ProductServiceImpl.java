package com.myspring.baroip.product.service;


import java.util.ArrayList;
import java.util.Base64;
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
			
		if(bestProducts != null && !bestProducts.isEmpty() ) {
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

				bestProductInfo.put("product" + (i+1), productInfo);
				
			}

		}
		}

		return bestProductInfo;

	}
	
	// product detail ��ȸ service
	@Override
	public Map<String, Map<String, Object>> productDetail(String product_id) throws Exception {
		
		// �̹������� / ��ǰ������ ���� ��ü ����
		Map<String, Map<String, Object>> productInfo= new HashMap<String, Map<String, Object>>();
		// �̹��� ������ �ҷ��� option ��ü ����
		Map<String, String> option = new HashMap<String, String>();
			
		option.put("match_id", product_id);
		// jsp���� sub�̹����� ������ŭ �ݺ��� ����� ���� ī��Ʈ ����

		// ī�װ� ������ ���� ��ü ���� �� ��ǰ�� �ش��ϴ� �̹��� ī�װ� ����
		List<String> categoryList = imageService.selectImageCategory(product_id);
		Map<String, Object> item = new HashMap<String, Object>();
		List<String> imageList = new ArrayList<String>();
		for (int i = 0 ; i<categoryList.size() ; i++) {
			option.put("image_category", categoryList.get(i));
			ImageVO productImage = imageService.selectProductImage(option);
			
			String encodeImage = Base64.getEncoder().encodeToString(productImage.getImage_file());
			if (categoryList.get(i).contains("body")) {
				imageList.add(encodeImage);

			} else {
				item.put(categoryList.get(i), encodeImage);
				productInfo.put("image", item);
			}

		}
		
		item.put("body", imageList);
		productInfo.put("image", item);
		
		ProductVO product = productDAO.selectProduct(product_id);
		String body = product.getProduct_body().replaceAll("(\r\n|\r|\n|\n\r)", "&#10;");
		product.setProduct_body(body);
		
		item.put("productVO", product);
		// ��ǰ ���� ����
		productInfo.put("product", item);
		
		return productInfo;
			
	}
	
	@Override
	public Map<String, Map<String, Object>> selectProductList(String product_states) throws Exception {
		
		
				List<ProductVO> productList = productDAO.selectProductList(product_states);
				String encodeImage = "";
				
				Map<String, Map<String, Object>> selectProductList = new HashMap<String, Map<String, Object>>();
				// �̹��� ȣ���� ���� option Map ��ü ����
				Map<String, String> option = new HashMap<String, String>();
					
				if(productList != null && !productList.isEmpty() ) {
								
				for (int i = 0; i < productList.size(); i++) {

					ProductVO product = productList.get(i);
					
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
						if(productImage != null) {
						 encodeImage = Base64.getEncoder().encodeToString(productImage.getImage_file());
						}
						
						
						productInfo.put("product_main_title", product.getProduct_main_title());
						productInfo.put("product_cre_date", product.getProduct_cre_date());
						productInfo.put("product_amount", product.getProduct_amount());
						productInfo.put("product_states", product.getProduct_states());
						productInfo.put("image_file", encodeImage);
						productInfo.put("product_id", product.getProduct_id());
						productInfo.put("user_id", product.getUser_id());

						selectProductList.put("product" + (i+1), productInfo);
						
					}

				}
				}
				else {
					System.out.println("baroip : �ӽõ�ϵ� ��ǰ�� �����ϴ�.");
				}

				return selectProductList;
	}
}
