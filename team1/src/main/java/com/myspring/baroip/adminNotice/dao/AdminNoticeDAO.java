// 2022.01.10 ������

package com.myspring.baroip.adminNotice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.baroip.notice.vo.NoticeVO;

public interface AdminNoticeDAO {
	
	// �ɼǿ� ���� notice select DAO
	public List<NoticeVO> noticeListToOption( Map<String, String> option) throws DataAccessException;

}
