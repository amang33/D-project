package com.myspring.baroip.product.service;

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


				// ��ǰ ����� �̹����� ���� ��ü ����
				Map<String, Object> productInfo = new HashMap<String, Object>();

				productInfo.put("product_main_title", product.getProduct_main_title());
				productInfo.put("product_sub_title", product.getProduct_sub_title());
				productInfo.put("product_price", product.getProduct_price());
				productInfo.put("product_discount", product.getProduct_discount());
				productInfo.put("image_file", productImage.getImage_file());

				bestProductInfo.put("mainProduct" + (i+1), productInfo);
				
			}
			// ���� ��ǰ����� 3�� �̸��� ��츦 ����� else ��
			else {
				// ��ǰ ����� �̹����� ���� ��ü ����
				Map<String, Object> productInfo = new HashMap<String, Object>();

				productInfo.put("product_main_title", "��ǰ��"+(i+1));
				productInfo.put("product_sub_title", "��ǰ����"+(i+1));
				productInfo.put("product_price", 10000);
				productInfo.put("product_discount", 0);
				productInfo.put("image_file", "");

				bestProductInfo.put("mainProduct" + (i+1), productInfo);
			}
		}

		return bestProductInfo;

	}
}
