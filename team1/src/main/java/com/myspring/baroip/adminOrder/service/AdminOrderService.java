// 2021.12.09 ������

package com.myspring.baroip.adminOrder.service;

import java.util.List;
import java.util.Map;

public interface AdminOrderService {
	
	public List<Map<String, Object>> orderListToOption( Map<String, String> option) throws Exception;
}
