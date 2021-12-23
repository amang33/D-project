// 2021.12.09 ������

package com.myspring.baroip.image.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myspring.baroip.image.service.ImageService;
import com.myspring.baroip.image.vo.ImageVO;

@Controller("imageController")
public class ImageControllerImpl implements ImageController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageVO imageVO;

	@Override
	public void ImageSetImageVO(MultipartHttpServletRequest multipartRequest, String match_id) throws Exception {

		// �Ķ���ͷ� ������ match_id�� notice_id / product_id�� �Ǿ����.

		// multipart�� ���޵� ÷�������� name �Ӽ��� ��ü ����
		Iterator<String> imageFileNames = multipartRequest.getFileNames();

		// ���� �ִ��� Ȯ�� �� while ���ǹ� �ݺ�
		while (imageFileNames.hasNext()) {
			// �ش� �ݺ����� name(product�� ��� body, main, sub) ����
			String imageCategory = imageFileNames.next();
			// category�� body�� ��� multiple�� �����̱⶧���� getFiles�� ���� ����Ʈȭ �Ѵ�.
			if (imageCategory.equals("body")) {

				List<MultipartFile> imageFiles = multipartRequest.getFiles(imageCategory);

				if (!imageFiles.isEmpty()) {

					for (int i = 0; i < imageFiles.size(); i++) {

						if (imageFiles.get(i).getOriginalFilename() != null
								&& imageFiles.get(i).getOriginalFilename() != "") {
							imageVO.setImage_match_id(match_id);
							imageVO.setImage_category(imageCategory+(i+1));
							imageVO.setImage_file_name(imageFiles.get(i).getOriginalFilename());
							imageVO.setImage_file(imageFiles.get(i).getBytes());

							// ���Ե� �ڷḦ mapper.image.insertImage �� ����
							String imageName = imageService.addImageFile(imageVO);

							System.out.printf("baroip : [%s] �̹��� ������ DataBase�� ����Ǿ����ϴ�.%n", imageName);

						}
					}
				}

			} else {

				// imageFile ��ü�� file��ü�� ��ü ������ ����
				MultipartFile imageFile = multipartRequest.getFile(imageCategory);
				if (imageFile != null) {
					if (imageFile.getOriginalFilename() != null && imageFile.getOriginalFilename() != "") {
						imageVO.setImage_match_id(match_id);
						imageVO.setImage_category(imageCategory);
						imageVO.setImage_file_name(imageFile.getOriginalFilename());
						imageVO.setImage_file(imageFile.getBytes());

						// ���Ե� �ڷḦ mapper.image.insertImage �� ����
						String imageName = imageService.addImageFile(imageVO);

						System.out.printf("baroip : [%s] �̹��� ������ DataBase�� ����Ǿ����ϴ�.%n", imageName);
					}
				}
			}
		}

	}
	// �ش� �̹��� ī�װ��� �Է°��� �������, ������ ����.. ������� ������Ʈ!
	@Override
	public void updateImage(MultipartHttpServletRequest multipartRequest, String match_id) throws Exception {

		// multipart�� ���޵� ÷�������� name �Ӽ��� ��ü ����. ���ε�� ������ ��� �Ҵ��
				Iterator<String> imageFileNames = multipartRequest.getFileNames();
				// �� name�� ���������� while ���ǹ� �ݺ�(body, main, ...)
				while (imageFileNames.hasNext()) {
					// �ش� �ݺ����� name(product�� ��� body, main, sub) ����
					String imageCategory = imageFileNames.next();
					
					if (imageCategory.equals("body")) {
						
						List<MultipartFile> imageFiles = multipartRequest.getFiles(imageCategory);
						// body �̹����� ������� ���� ���
						if (!imageFiles.isEmpty()) {
							
							if(imageFiles.get(0).getOriginalFilename() != null && imageFiles.get(0).getOriginalFilename() != "") {
								// body�� ���ε�� �̹��������� ������� �������, ������ body 1~ �̹��� ������ �����Ѵ�.
								imageService.clearBodyImage(match_id);
							}
						
							for (int i = 0; i < imageFiles.size(); i++) {

								if (imageFiles.get(i).getOriginalFilename() != null
										&& imageFiles.get(i).getOriginalFilename() != "") {
									imageVO.setImage_match_id(match_id);
									imageVO.setImage_category(imageCategory+(i+1));
									imageVO.setImage_file_name(imageFiles.get(i).getOriginalFilename());
									imageVO.setImage_file(imageFiles.get(i).getBytes());

									// ���Ե� �ڷḦ mapper.image.insertImage �� ����
									String imageName = imageService.addImageFile(imageVO);

									System.out.printf("baroip : [%s] ��ǰ�� [%s] �̹��� ������ [%s] �̹����� �����Ǿ����ϴ�.%n", match_id, imageCategory+(i+1), imageName);

								}
								// body �̹����� ������� ��� ���
								else {
									System.out.printf("baroip : [%s] ��ǰ [%s] ī�װ��� ���� ����� �̹����� �����ϴ�.%n", match_id, imageCategory);
								}
							}
						}
					
						

					} 
					// �ٸ� ī�װ� notice, cs, main, sub 1~3�� ��� ������Ʈ ����
					else {

						// imageFile ��ü�� file��ü�� ��ü ������ ����
						MultipartFile imageFile = multipartRequest.getFile(imageCategory);
						if (imageFile != null) {
							if (imageFile.getOriginalFilename() != null && imageFile.getOriginalFilename() != "") {
								imageVO.setImage_match_id(match_id);
								imageVO.setImage_category(imageCategory);
								imageVO.setImage_file_name(imageFile.getOriginalFilename());
								imageVO.setImage_file(imageFile.getBytes());

								
								String imageName = imageService.updateImageFile(imageVO);

								System.out.printf("baroip : [%s] ��ǰ�� [%s] �̹��� ������ [%s] �̹����� �����Ǿ����ϴ�.%n", match_id, imageCategory, imageName);
							}
							// �ش� ī�װ��� ���������� ���
							else {
								System.out.printf("baroip : [%s] ��ǰ [%s] ī�װ��� ���� ����� �̹����� �����ϴ�.%n", match_id, imageCategory);
							}
						}
						
					}
				

				}
	}

}
