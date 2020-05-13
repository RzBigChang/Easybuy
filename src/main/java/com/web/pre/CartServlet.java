package com.web.pre;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrder;
import com.entity.EasybuyProduct;
import com.entity.EasybuyUser;
import com.entity.EasybuyUserAddress;
import com.entity.EasybuyProductCategory;
import com.service.address.Impl.EasybuyUserAddressServiceImpl;
import com.service.order.Impl.CartServiceImpl;
import com.service.order.Impl.OderServiceImpl;
import com.service.product.impl.EasybuyProductServiceImpl;
//import com.service.address.impl.EasybuyUserAddressServiceImpl;
//import com.service.order.impl.CartServiceImpl;
//import com.service.order.impl.EasybuyOrderServiceImpl;
import com.service.product.impl.ProductCategoryServiceImpl;
import com.dao.impl.EasybuyProductDaoImpl;
import com.dao.ProductDao;
import com.utils.Constants;
import com.utils.EmptyUtils;
import com.utils.ReturnResult;
import com.utils.ShoppingCart;
import com.utils.ShoppingCartItem;
import com.web.AbstractServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CartServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
	/**
	 * 添加到购物车！
	 *
	 * @return
	 */
	public ReturnResult add(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReturnResult result = new ReturnResult();
		 //获取用户信息
		EasybuyUser user = getUserFromSession(request);
		if (user == null) {
			return result.returnFail("您未登录，请先登录！");
		}
		String id = request.getParameter("entityId");
		String quantityStr = request.getParameter("quantity");
		Integer quantity = 1;
		if (!EmptyUtils.isEmpty(quantityStr))
			quantity = Integer.parseInt(quantityStr);
		// 查询出商品
		EasybuyProduct product = new EasybuyProductServiceImpl().findById(Integer.parseInt(id));
		if (product.getStock() < quantity) {
			return result.returnFail("商品数量不足");
		}
		// 获取购物车
		ShoppingCart cart = getCartFromSession(request);
		// 往购物车放置商品
		result = cart.addItem(product, quantity);
		if (result.getStatus() == Constants.ReturnResult.SUCCESS) {
			cart.setSum(
					(EmptyUtils.isEmpty(cart.getSum()) ? 0.0 : cart.getSum()) + (product.getPrice() * quantity * 1.0));
		}
		return result;
	}
	private ShoppingCart getCartFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取对象！
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	/**
	 * 刷新购物车！
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = new CartServiceImpl().calculate(cart);
		session.setAttribute("cart", cart);// 全部的商品
		return "/common/pre/searchBar";
	}
	/**
	 * 跳到结算页面！
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public String toSettlement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 访问三层！
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
		// 放置对象！
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		return "/pre/settlement/toSettlement";

	}
	/**
	 * 添加到收藏！
	 *
	 * @return
	 */
	public ReturnResult addCollect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// HttpSession session = request.getSession();
		ReturnResult result = new ReturnResult();

		// 获取用户信息
		EasybuyUser user = getUserFromSession(request);

		if (user == null) {
			return result.returnFail("您未登录，请先登录！");
		}
		String id = request.getParameter("id");
		// 查询出商品
		EasybuyProduct product = new EasybuyProductServiceImpl().findById(Integer.parseInt(id));

		String typeId = request.getParameter("type");
		int type = Integer.parseInt(typeId);
		if (type == 0) {
			// 访问三层！
			EasybuyCollect easybuyCollect = new CartServiceImpl().selectId(user.getId(), product.getId());
			if (easybuyCollect == null) {
				int count = new CartServiceImpl().addCollect(user.getId(), product.getId(), 0, type);
				if (count > 0) {
					result.returnSuccess("收藏成功");
				} else {
					result.returnFail("收藏失败");
				}
			} else {
				int count=new CartServiceImpl().delCollect(easybuyCollect);
				if(count>0) {
					result.returnSuccess("成功取消收藏");
				}else {
					result.returnFail("取消失败");
				}
				
			}
		}
		return result;
	}
	private EasybuyUser getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取对象！
		EasybuyUser user = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		return user;
	}
	/**
	 * 判断购物车信息！       
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ReturnResult checkCart(HttpServletRequest request) throws Exception {
		ReturnResult result = new ReturnResult();
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = new CartServiceImpl().calculate(cart);
		// 循环！
		for (ShoppingCartItem item : cart.getItems()) {
			// 访问三层！
			EasybuyProduct product = new EasybuyProductServiceImpl().findById(item.getProduct().getId());
			if (product.getStock() < item.getQuantity()) {
				return result.returnFail(product.getName() + "商品数量不足");
			}
		}
		return result.returnSuccess();
	}
	/**
	 * 清空购物车！
	 *
	 * @param request
	 * @param response
	 */
	public ReturnResult clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 结账后清空购物车、
		request.getSession().removeAttribute("cart");
		result.returnSuccess(null);
		return result;
	}
	/**
	 * 修改购物车信息
	 *
	 * @param request
	 * @return
	 */
	public ReturnResult modCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 创建对象！
		HttpSession session = request.getSession();
		// 获取ID！
		String id = request.getParameter("entityId");
		String quantityStr = request.getParameter("quantity");
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		EasybuyProduct product = new EasybuyProductServiceImpl().findById(Integer.parseInt(id));
		if (EmptyUtils.isNotEmpty(quantityStr)) {
			if (Integer.parseInt(quantityStr) > product.getStock()) {
				return result.returnFail("商品数量不足");
			}
		}
		// 访问三层！
		cart = new CartServiceImpl().modifyShoppingCart(id, quantityStr, cart);
		session.setAttribute("cart", cart);// 全部的商品
		return result.returnSuccess();
	}
	/**
	 * 生成订单！
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public Object settlement3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = new CartServiceImpl().calculate(cart);
		//合算金额
		EasybuyUser user = getUserFromSession(request);
		//用户信息
		ReturnResult result = checkCart(request);
		//servlet判断购物车信息
		if (result.getStatus() == Constants.ReturnResult.FAIL) {
			return result;
		}
		// 新增地址
		String addressId = request.getParameter("addressId");
		String newAddress = request.getParameter("newAddress");
		String newRemark = request.getParameter("newRemark");
		// 创建对象！
		EasybuyUserAddress userAddress = new EasybuyUserAddress();
		if (addressId.equals("-1")) {
			HttpSession session = request.getSession();
			//添加地址
			EasybuyUser user1 = (EasybuyUser) session.getAttribute("easybuyUserLogin");
			userAddress.setRemark(newRemark);
			userAddress.setAddress(newAddress);
			userAddress.setUserId(user1.getId());
			userAddress.setId(new EasybuyUserAddressServiceImpl().addUserAddress(userAddress));
		} else {
			userAddress = new EasybuyUserAddressServiceImpl().getUserAddressById(Integer.parseInt(addressId));
		}

		// 生成订单
		EasybuyOrder order = new OderServiceImpl().payShoppingCart(cart, user, userAddress.getAddress());
		clearCart(request, response);
		request.setAttribute("currentOrder", order);
		return "/pre/settlement/settlement3";
	}
	/**
	 * 跳转到购物车页面！
	 *
	 * @param request
	 * @param response
	 * @return
	 * 核算金额
	 */
	public String settlement1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = new CartServiceImpl().calculate(cart);
		// 放置对象！
		request.getSession().setAttribute("cart", cart);
		return "/pre/settlement/settlement1";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 地址查询
	 */

	public String settlement2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EasybuyUser user = getUserFromSession(request);
		// 访问三层！
		List<EasybuyUserAddress> userAddressList = new EasybuyUserAddressServiceImpl()
				.getEasybuyUserAddressAll(user.getId());// 获取用户收货地址
		// 放置对象！
		request.setAttribute("userAddressList", userAddressList);
		return "/pre/settlement/settlement2";
	}

	@Override
	public Class getServletClass() {
		
		return CartServlet.class;
	}

}
