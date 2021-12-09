package com.myspring.baroip.image.controller;


import java.sql.Blob;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myspring.baroip.image.service.ImageService;
import com.myspring.baroip.image.vo.ImageVO;

@Controller("imageController")
public class ImageControllerImpl implements ImageController{
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageVO imageVO;
	
	@Override
	public void ImageSetImageVO(MultipartHttpServletRequest multipartRequest, String match_id) throws Exception {
		

		// multipart�� ���޵� ÷�������� name �Ӽ��� ��ü ����
		Iterator<String> imageFileNames = multipartRequest.getFileNames();

		while (imageFileNames.hasNext()) {
			// imageFile ��ü�� �ϳ��� ����

			MultipartFile imageFile = multipartRequest.getFile(imageFileNames.next());
			if (imageFile != null) {
				imageVO.setImage_match_id(match_id);
				imageVO.setImage_category(imageFileNames.next());
				imageVO.setImage_file_name(imageFile.getOriginalFilename());
				byte[] bytes = imageFile.getBytes();
				Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
				
				imageVO.setImage_file(blob);

				// ���Ե� �ڷḦ mapper.image.insertImage �� ����
				String imageName = imageService.addImageFile(imageVO);

				System.out.printf("[%s] �̹��� ������ DataBase�� ����Ǿ����ϴ�.%n", imageName);
			}
		}

	}

}
