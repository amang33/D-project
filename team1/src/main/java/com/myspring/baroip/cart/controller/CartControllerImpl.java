package com.myspring.baroip.cart.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.baroip.cart.service.CartService;
import com.myspring.baroip.cart.vo.CartVO;
import com.myspring.baroip.product.service.ProductService;
import com.myspring.baroip.user.vo.UserVO;


@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl implements CartController{
	@Autowired
	private CartService cartService;
	@Autowired
	private CartVO cartVO;
	@Autowired
	private UserVO userVO;
	@Autowired
	private ProductService productService;
	
	
	// ��ٱ��� ������
	@Override
	@RequestMapping(value= "/cartList.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView mycartList(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session=request.getSession();
		userVO = (UserVO)session.getAttribute("userInfo");
		if(userVO != null) {
			String user_id = userVO.getUser_id();
			cartVO.setUser_id(user_id);
			Map<String, Map<String, Map<String, Object>>> userCartListInfo = cartService.myCartList(cartVO);
			mav.addObject("userCartListInfo", userCartListInfo);
		}
//		��ȸ�� ��ٱ��� ����Ʈ
		else {
			List<CartVO> notUserCart = (List<CartVO>) session.getAttribute("guestCartAdd");
			Map<String, Map<String, Map<String, Object>>> userCartListInfo = new HashMap<String, Map<String, Map<String, Object>>>();
			if(notUserCart != null) {
				for(int i = 0; i < notUserCart.size(); i++) {
					Map<String, Object> cartItem = new HashMap<String, Object>();
					Map<String, Map<String, Object>> guestCart = new HashMap<String, Map<String, Object>>();
					
					String ProductId=notUserCart.get(i).getProduct_id();
					
					guestCart=productService.productDetail(ProductId);
					cartItem.put("cartVO", notUserCart.get(i));
					guestCart.put("cart", cartItem);
					userCartListInfo.put("myCartList" + (i+1), guestCart);
				}
			}
			else {
				userCartListInfo = null;
			}
			session.setAttribute("userCartListInfo", userCartListInfo);
		}
		mav.setViewName(viewName);
		return mav;
		
	}
	
//	��ǰ �� ������ > ��ٱ��� ���
	@Override
	@ResponseBody
	@RequestMapping(value= "/addProductInCart.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public String addProductInCart(@RequestParam("product_id") String product_id, @RequestParam("cart_count") int cart_count,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		userVO = (UserVO)session.getAttribute("userInfo");
//		�α��� ���� ��ٱ��� ���
		if(userVO != null) {
			String user_id = userVO.getUser_id();
			cartVO.setUser_id(user_id);
			cartVO.setProduct_id(product_id);
			cartVO.setCart_count(cart_count);
			boolean productInCart = cartService.selectProductInCart(cartVO);
//		��ٱ��Ͽ� �ش� ��ǰ�� �ִ��� Ȯ��
			if(productInCart == true) {
				return "overLapProduct";
			}
			else {
				cartService.addProductInCart(cartVO);
				return "addProduct";
			}
		}
//		��α��� ��ٱ��� ���
		else {
			List<CartVO> guestCartList = new ArrayList<CartVO>();
			@SuppressWarnings("unchecked")
			List<CartVO> sessionCart = (List<CartVO>)session.getAttribute("guestCartAdd");
			if (sessionCart != null) {
				guestCartList = sessionCart;
			}
			cartVO = new CartVO();
			cartVO.setCart_count(cart_count);
			cartVO.setProduct_id(product_id);
			guestCartList.add(cartVO);
			for(int i = 0; i < guestCartList.size(); i++) {
				session.setAttribute("guestCartAdd", guestCartList);
			}
			return "null";
		}
		
	}
	
//	�������� ���� ��ǰ �߰�
	@Override
	@ResponseBody
	@RequestMapping(value= "/cartInProductOverLap.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public String cartInProductOverLap(@RequestParam("product_id") String product_id, @RequestParam("cart_count") int cart_count,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		userVO = (UserVO)session.getAttribute("userInfo");
			String user_id = userVO.getUser_id();
			cartVO.setUser_id(user_id);
			cartVO.setProduct_id(product_id);
			cartVO.setCart_count(cart_count);
			cartService.ProductOverLap(cartVO);
			return "cart_count : " + cartVO.getCart_count();
	}
	
//	��ٱ��� ��ǰ ����
	@Override
	@ResponseBody
	@RequestMapping(value= "/cartListDelete.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public String cartListDelete(@RequestParam("product_id") String product_id, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		userVO = (UserVO)session.getAttribute("userInfo");
		if(userVO != null) {
			Map<String, String> deleteList = new HashMap<String, String>();
			String user_id = userVO.getUser_id();
			deleteList.put("user_id", user_id);
			deleteList.put("product_id", product_id);
			cartService.deleteCartItem(deleteList);
			return product_id;
		} 
//		��ȸ�� ��ٱ��� ��ǰ ����
		else {
			List<CartVO> guestCartList = new ArrayList<CartVO>();
			guestCartList = (List<CartVO>) session.getAttribute("guestCartAdd");
			cartVO.setProduct_id(product_id);
			System.out.println(guestCartList.size());
			for(int i=0; guestCartList.size()>i; i++) {
				if(guestCartList.get(i).equals(cartVO)) {
					System.out.println(guestCartList.get(i).getProduct_id());
					guestCartList.remove(i);
				}
			}
			session.setAttribute("guestCartAdd", guestCartList);
			return product_id;
		}
	}
	
}
