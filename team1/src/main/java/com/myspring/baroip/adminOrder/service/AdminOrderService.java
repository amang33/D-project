// 2021.12.09 ������

package com.myspring.baroip.adminOrder.service;

import java.util.List;
import java.util.Map;

public interface AdminOrderService {
	
	// �ɼǿ� ���� �ֹ� ����Ʈ ��ȸ Service
	public List<Map<String, Object>> orderListToOption( Map<String, String> option) throws Exception;
	
	// �ֹ� ���� ���漭��
	public void updateState(Map<String, String> option) throws Exception;
}
