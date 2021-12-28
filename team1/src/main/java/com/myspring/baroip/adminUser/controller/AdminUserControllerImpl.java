// 2021.12.28 ������

package com.myspring.baroip.adminUser.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.adminUser.service.AdminUserService;
import com.myspring.baroip.user.vo.UserVO;

@Controller("adminUserController")
@RequestMapping(value = "/admin/user")
public class AdminUserControllerImpl implements AdminUserController{
	
	@Autowired
	private AdminUserService adminUserService;
	// ��ü ȸ������ �޴� ��Ʈ�ѷ�
		@Override
		@RequestMapping(value = "/user_list.do", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView userList(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			
			//get ��û�� �������, ������ session�� ����
			if (info.isEmpty()) {
				session.removeAttribute("search_option");
				session.removeAttribute("search_value");
			}
			List<UserVO> userList = getFullList(info, request);

			
			String pageNo = info.get("pageNo");
			ModelAndView mav = new ModelAndView();
			String viewName = (String) request.getAttribute("viewName");
			
			if (pageNo != null && pageNo != "") {
				mav.addObject("pageNo", pageNo);
			} else {
				mav.addObject("pageNo", 1);
			}
			mav.addObject("userList", userList);
			mav.setViewName(viewName);

			return mav;
		}
		
		// ȸ�� ��ȸ ���� ����, ���ǿ� �ִ� ȸ�������� Ȯ�� �� ���񽺷� ó���ϴ� �޼ҵ�
		public List<UserVO> getFullList(@RequestParam Map<String, String> info, HttpServletRequest request) throws Exception {
			
			HttpSession session = request.getSession();
			
			// Map options���� ��ȸ�ϰ��� �ϴ� �������� option, ���ǿ� �ش��ϴ� value �� �ݵ�� ���ԵǾ���Ѵ�.
			// key "search_option" = value [userJoinDate / userId / all]
			// key "search_value" = value [yyyy-mm-dd,yyyy-mm-dd / product_main_title / 0 or 1(product_states) ]
			Map<String, String> options = new HashMap<String, String>();
			
			String paramOption = info.get("search_option");
			String paramValue = info.get("search_value");
			
			String sessionOption = (String) session.getAttribute("search_option");
			String sessionValue = (String) session.getAttribute("search_value");
			
			String viewName = (String) request.getAttribute("viewName");
	
			// param, session ��� option�� ���ε� �Ǿ��ִ� ���
			if (paramOption != null && sessionOption != null) {

					// �� �ɼ��� ��ġ�� ���, options�� ���� session�� ���� �����Ѵ�.
					if (paramOption.equals(sessionOption) && paramValue.equals(sessionValue)) {
						options.put("search_option", sessionOption);
						options.put("search_value", sessionValue);
					}

					// �� �ɼ��� ��ġ���� ���� ���, options�� paramOption�� �����ϰ�, ���� ������ Override �Ѵ�.
					else {
						options.put("search_option", paramOption);
						options.put("search_value", paramValue);

						session.setAttribute("search_option", paramOption);
						session.setAttribute("search_value", paramValue);
					}
				}

				// ���ǿ� ���ε��� option�� �������, options�� paramOption�� �����ϰ�, ���ǿ� set �Ѵ�.
				else if (paramOption != null && sessionOption == null) {
					options.put("search_option", paramOption);
					options.put("search_value", paramValue);

					session.setAttribute("search_option", paramOption);
					session.setAttribute("search_value", paramValue);
				}
			
			// param�� ���ε��� option�� �������, session�� option�� �����Ѵ�.
			else if (paramOption == null && sessionOption != null) {
				options.put("search_option", sessionOption);
				options.put("search_value", sessionValue);
			}
			
			// param�� session�� ���ε��� ������ �������, viewName�� ���� ��ü list�� �����ش�.
			else {
					options.put("search_option", "rank");
					options.put("search_value", "4");
					}
						
			List<UserVO> fullList = adminUserService.userList(options) ;
			
			return fullList;
		}
}
