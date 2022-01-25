// 2021.12.09 ������

package com.myspring.baroip.adminOrder.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface AdminOrderDAO {
	
	// ��ȸ ���ǿ� ���� �ֹ� ����Ʈ ��ȸ DAO
	public List<Map<String, Object>> selectOrderToOption( Map<String, String> option) throws DataAccessException;
	
	// �ֹ� ���� ���� DAO
	public void updateState(Map<String, String> option) throws DataAccessException;
	
}
