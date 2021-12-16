// 2021.12.09 ������

package com.myspring.baroip.image.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.image.dao.ImageDAO;
import com.myspring.baroip.image.vo.ImageVO;

@Service("imageService")
@Transactional(propagation = Propagation.REQUIRED)
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDAO imageDAO;

	@Override
	public String addImageFile(ImageVO imageVO) throws Exception {

		String imageName = imageDAO.insertImageFile(imageVO);

		return imageName;
	}

	// option�� �Ķ���ʹ� match_id/�� , image_category/���� �ΰ��� Map�� �����Ѵ�..
	@Override
	public ImageVO selectProductImage(Map<String, String> option) throws Exception {
		ImageVO image = imageDAO.selectProductImages(option);

		return image;

	}

	@Override
	public List<String> selectImageCategory(String match_id) throws Exception {

		List<String> categoryList = imageDAO.selectImageCategory(match_id);

		return categoryList;
	}
	

}
