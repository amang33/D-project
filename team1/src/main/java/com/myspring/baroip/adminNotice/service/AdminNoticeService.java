// 2022.01.10 ������

package com.myspring.baroip.adminNotice.service;

import java.util.Map;

public interface AdminNoticeService {
	
//	�ɼǿ� ���� �Խñ� ����Ʈ ��ȸ Service
	public Map<String, Map<String, Object>> noticeListToOption( Map<String, String> option) throws Exception;
//	�Խù� ����
	public String deleteNotice(String notice_id) throws Exception;
}
