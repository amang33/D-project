// 2021.12.09 윤상현

package com.myspring.baroip.image.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.baroip.image.dao.ImageDAO;
import com.myspring.baroip.image.vo.ImageVO;

@Service("imageService")
@Transactional(propagation=Propagation.REQUIRED)
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageDAO imageDAO;

	@Override
	public String addImageFile(ImageVO imageVO) throws Exception {
		
		String imageName = imageDAO.insertImageFile(imageVO);
		
		return imageName;
	}
	
	// option 에는 match_id / image_category 가 대입되어야 한다.
	@Override
	public ImageVO selectProductImage(Map<String, String> option) throws Exception {
		ImageVO image = imageDAO.selectProductImages(option);
		
		return image;
		
		
	}
}
