package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.entity.EasybuyCollect;
import com.entity.EasybuyNews;
import com.entity.EasybuyOrder;
import com.entity.EasybuyProduct;
import com.entity.EasybuyProductCategory;
import com.entity.EasybuyUser;
import com.entity.EasybuyUserAddress;
import com.service.address.EasybuyUserAddressService;
import com.service.address.Impl.EasybuyUserAddressServiceImpl;
import com.service.news.NewsService;
import com.service.news.impl.NewsServiceImpl;
import com.service.order.CartService;
import com.service.order.OrderService;
import com.service.order.Impl.CartServiceImpl;
import com.service.order.Impl.OderServiceImpl;
import com.service.product.EasybuyProductService;
import com.service.product.ProductCategoryService;
import com.service.product.impl.EasybuyProductServiceImpl;
import com.service.product.impl.ProductCategoryServiceImpl;
import com.service.user.EasybuyUserService;
import com.service.user.impl.EasybuyUserServiceImpl;
import com.utils.CodeUtil;
import com.utils.Constants;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.PrintUtil;
import com.utils.ReturnResult;
import com.utils.SecurityUtils;
import com.utils.ShoppingCart;
import com.utils.ShoppingCartItem;

@Controller
@Transactional
public class Servlet {
	//登录 注册等用户相关的功能
	@Autowired
	private EasybuyUserService eus;
	
	//商品相关的功能
	@Autowired
	private ProductCategoryService ecs;
	//新闻
	@Autowired
	private NewsService news;
	//商品
	@Autowired
	private ProductCategoryService cate;
	//购物车
	@Autowired
	private CartService carts;
	//收藏夹
	@Autowired
	private EasybuyProductService product;
	//订单
	@Autowired
	private OrderService orders;
	//地址
	@Autowired
	private EasybuyUserAddressService addresse;
	@RequestMapping("/deng")
	@Transactional(propagation=Propagation.SUPPORTS)
	public void loginBtn(@RequestParam("name")String name,@RequestParam("pwd")String pwd,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = SecurityUtils.md5Hex(pwd);
		ReturnResult result = new ReturnResult();
			//加密密碼！！！！！！
			EasybuyUser easybuyUserLogin = eus.findEasybuyUserInfo(name, password);
			
			// 判断用户是否注册过
			if (easybuyUserLogin != null) {
				request.getSession().setAttribute("easybuyUserLogin", easybuyUserLogin);
				PrintUtil.write(1,response);
				//return "/pre/Index";
			} else {
				//return result.returnFail("用户或密码输入有误");
				PrintUtil.write(null,response);
			}
		}
	
	@RequestMapping("/zhu")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String index(Map<String,Object>map){
		 int id=1;
		 int id1=2;
		 int id2=3;
		// 访问三层！
		List<EasybuyProductCategory> list = ecs.getProductCategoryList(id);
		List<EasybuyProductCategory> list2 = ecs.getProductCategoryList(id1);
		List<EasybuyProductCategory> list3 = ecs.getProductCategoryList(id2);
		List<EasybuyNews> newsList = news.queryNewsList(null);
		// 访问三层！
		List<EasybuyProduct> productList = product.getEasybuyProductList();

		map.put("productList", productList);
		map.put("newsList", newsList);
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		
		return "pre/Index";
	}
	//转JSON字符串
	public void print(Object msg,HttpServletResponse rep) throws IOException {
		String info=JSON.toJSONString(msg);
		System.out.println(info);
		PrintWriter out=rep.getWriter();
		out.print(info);
	}
	//注册
	@RequestMapping("/addRegisterInfo")
	@Transactional(propagation=Propagation.REQUIRED)
	public String addRegisterInfo(@RequestParam("name")String name,@RequestParam("password")String pwd,@RequestParam("numName")String numName,@RequestParam("sex")int sex,@RequestParam("mem")String mem,@RequestParam("email")String email,@RequestParam("phone")String phone,HttpServletRequest request, HttpServletResponse response)throws Exception {
		name = request.getParameter("name");
		//设置密码为md5加密状态
		String passwords = SecurityUtils.md5Hex(request.getParameter("password"));
		numName = request.getParameter("numName");
		sex = Integer.parseInt(request.getParameter("sex"));
		mem = request.getParameter("mem");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		
		EasybuyUser easybuyUser=new EasybuyUser();
		easybuyUser.setUserName(numName);
		easybuyUser.setLoginName(name);
		easybuyUser.setSex(sex);
		easybuyUser.setIdentityCode(mem);
		easybuyUser.setEmail(email);
		easybuyUser.setMobile(phone);
		easybuyUser.setPassword(passwords);
		//生成随机码
		String codeUrl=CodeUtil.generateUniqueCode()+CodeUtil.generateUniqueCode();
		//默认生成一个随机码
		easybuyUser.setCodeUrl(codeUrl);
		//默认刚注册的状态为未激活状态 0未激活 1激活
		easybuyUser.setActivated(1);
		ReturnResult results=new ReturnResult();
		int result=eus.addRegisterInfo(easybuyUser);
		PrintWriter out=response.getWriter();
		if(result==1) {
			results.returnSuccess("注册成功了 快去激活把");
			PrintUtil.write(results, response);
		}else {
			results.returnFail("注册失败了哦 请联系管理员~.~");
			PrintUtil.write(results, response);
		}
		return "/pre/Login";
	}
	//验证用户名是否重复
	@RequestMapping("/loginNameCount")
	public void loginNameCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 获取用户昵称！
		String name = request.getParameter("name");
		int count= eus.findLoginNameByName(name);
		// 判断是否注册成功！
		if (count>0) {
			 result.returnFail("该用户名已经存在");
			PrintUtil.write(result, response);
		} else {
			result.returnSuccess("用户名可用");
			PrintUtil.write(result, response);
		}
	}
	//新闻列表
	@RequestMapping("/News")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String queryNewsList(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response)throws Exception {
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int count = news.getTotalCount();
		Pager pager = new Pager(count, rowPerPage, currentPage);
		pager.setUrl("/News");
		List<EasybuyNews>newsList=new ArrayList<EasybuyNews>();
		newsList=news.queryNewsList(pager);
		map.put("newsList", newsList);
		return "/backend/news/newsList";
	}
	//新闻详情
	@RequestMapping("/newsDeatil")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String newsDeatil(String id,HttpServletRequest request,HttpServletResponse response,Map<String,Object>map)throws Exception {
		EasybuyNews newsone=news.findNewsById(Integer.parseInt(id));
		// 放置内置对象！
		map.put("news",newsone);
		map.put("menu", 7);
		return "/backend/news/newsDetail";
	}
	//查询商品信息
	@RequestMapping("/queryProductDeatil")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String queryProductDeatil(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map,int id) throws Exception {
		EasybuyProduct products=product.findById(id);
		List<EasybuyProductCategory> list =	cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 =cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 =cate.getProductCategoryList(3);
		List<EasybuyCollect> listCollect=getUserFromSession2(request);
		if(listCollect!=null) {
			for (EasybuyCollect easybuyCollect : listCollect) {
				if(products.getId()==easybuyCollect.getProductId()) {
					request.setAttribute("easybuyCollect", easybuyCollect);
				}
			}
		}
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		map.put("product", products);
		return "/pre/product/productDeatil";
	}
	//一级分类
	@RequestMapping("/getProductCategoryListOne")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String getProductCategoryListOne(String keyWord,HttpServletRequest request,HttpServletResponse response,Map<String,Object>map)throws Exception {
		String category = request.getParameter(keyWord);// 名字
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = product.getProductRowCountByName(category);
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/getProductCategoryListOne?category=" + category);
		// 访问三层！
		List<EasybuyProduct> productList = product.getEasybuyProductListByCategoryName(category, pager);
		List<EasybuyProductCategory> list =cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 =cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 =cate.getProductCategoryList(3);
		List<EasybuyCollect> listCollect=getUserFromSession2(request);
		// 放置对象！
		map.put("listCollect", listCollect);
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		map.put("productList", productList);
		map.put("pager", pager);
		map.put("total", total);
		return "/pre/product/queryProductList";
	}
	
	//二级分类
	@RequestMapping("/queryProductList2")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String queryProductList2(String category,HttpServletRequest request,HttpServletResponse response,Map<String,Object>map)throws Exception {
		String categorys = request.getParameter(category);// id

		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = new EasybuyProductServiceImpl().getProductRowCount2(Integer.parseInt(categorys));
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/queryProductList2?category=" + categorys);
		// 访问三层！
		List<EasybuyProduct> productList =product.getEasybuyProductListByCategoryId2(Integer.parseInt(category), pager);
		List<EasybuyProductCategory> list = cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = cate.getProductCategoryList(3);
		// 放置对象！
		List<EasybuyCollect> listCollect=getUserFromSession2(request);
		map.put("listCollect", listCollect);
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		map.put("productList", productList);
		map.put("pager", pager);
		map.put("total", total);
		return "/pre/product/queryProductList";
	}
	//三级分类
	@RequestMapping("/queryProductList3")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String queryProductList3(String category,HttpServletRequest request,HttpServletResponse response,Map<String,Object>map)throws Exception {
		String categorys = request.getParameter("category");// id

		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = product.getProductRowCount3(Integer.parseInt(categorys));
		Pager pager = new Pager(total, rowPerPage, currentPage);
		// 访问三层！
		pager.setUrl("/queryProductList3?category=" + categorys);
		List<EasybuyProduct> productList = product.getEasybuyProductListByCategoryId3(Integer.parseInt(category), pager);
		List<EasybuyProductCategory> list = cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = cate.getProductCategoryList(3);
		// 放置对象！
		List<EasybuyCollect> listCollect=getUserFromSession2(request);
		map.put("listCollect", listCollect);
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		map.put("productList", productList);
		map.put("pager", pager);
		map.put("total", total);
		return "/pre/product/queryProductList";
	}
	
	//收藏
	@RequestMapping("/Collect")
	@Transactional(propagation=Propagation.SUPPORTS)
	public ReturnResult Collect(Map<String,Object>map,HttpServletRequest request,HttpServletResponse response,@RequestParam("id")String id,@RequestParam("type")int type)throws Exception {
		ReturnResult result=new ReturnResult();
		// 获取用户信息
		EasybuyUser user = getUserFromSession(request);
		if (user == null) {
			return result.returnFail("您未登录，请先登录！");
			}
			// 查询出商品
			EasybuyProduct products = product.findById(Integer.parseInt(id));

				String typeId = request.getParameter("type");
				int type1 = Integer.parseInt(typeId);
				if (type1 == 0) {
				// 访问三层！
				EasybuyCollect easybuyCollect = carts.selectId(user.getId(), products.getId());
				if (easybuyCollect == null) {
					int count = carts.addCollect(user.getId(), products.getId(), 0, type);
					if (count > 0) {
						result.returnSuccess("收藏成功");
						PrintUtil.write(result, response);
					} else {
						result.returnFail("收藏失败");
						PrintUtil.write(result, response);
					}
				} else {
					int count=carts.delCollect(easybuyCollect);
					if(count>0) {
						result.returnSuccess("成功取消收藏");
						PrintUtil.write(result, response);
					}else {
						result.returnFail("取消失败");
						PrintUtil.write(result, response);
			}		
		}
	}
				return result;
}
	//查询收藏
	@RequestMapping("/queryProductList4")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String queryProductList4(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		HttpSession session = request.getSession();
		EasybuyUser user1 = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		List<EasybuyCollect> listCollect = carts.selectByUserId(user1.getId());
		int total = listCollect.size();
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/queryProductList4");
	
		List<EasybuyProduct> productList = product.getEasybuyProductListByUser(listCollect);
		List<EasybuyProductCategory> list = cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = cate.getProductCategoryList(3);
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		List<EasybuyCollect> listCollecta=getUserFromSession2(request);
		
		map.put("listCollect", listCollecta);
		if((pager.getRowPerPage()*currentPage)>=total) {
			request.setAttribute("productList", productList.subList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), productList.size()));
		}else {
			request.setAttribute("productList", productList.subList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage()*currentPage));
		}
		
		map.put("pager", pager);
		map.put("total", total);
		return "/pre/product/queryProductList";
	}

	private List<EasybuyCollect> getUserFromSession2(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取对象！
		EasybuyUser user1 = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		List<EasybuyCollect> list=null;
		if(user1!=null) {
			list=carts.selectByUserId(user1.getId());
		}
		return list;
	}
	
	private EasybuyUser getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取对象！
		EasybuyUser user = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		return user;
	}
	//购物车部分
	//从session获取购物车
	private ShoppingCart getCartFromSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		// 获取对象！
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	//添加购物车
	@RequestMapping("/add")
	@Transactional(propagation=Propagation.REQUIRED)
	public ReturnResult add(HttpServletRequest request, HttpServletResponse response,String entityId,String quantity,Map<String,Object>map) throws Exception {
		ReturnResult result = new ReturnResult();
		 //获取用户信息
		EasybuyUser user = getUserFromSession(request);
		if (user == null) {
			return result.returnFail("您未登录，请先登录！");
		}
		Integer quantity1 = 1;
		if (!EmptyUtils.isEmpty(quantity))
			quantity1 = Integer.parseInt(quantity);
		// 查询出商品
		EasybuyProduct products =product.findById(Integer.parseInt(entityId));
		if (products.getStock() < quantity1) {
			return result.returnFail("商品数量不足");
		}
		// 获取购物车
		ShoppingCart cart = getCartFromSession(request);
		// 往购物车放置商品
		result = cart.addItem(products, quantity1);
		PrintUtil.write(result, response);
		if (result.getStatus() == Constants.ReturnResult.SUCCESS) {
			cart.setSum(
					(EmptyUtils.isEmpty(cart.getSum()) ? 0.0 : cart.getSum()) + (products.getPrice() * quantity1 * 1.0));
			PrintUtil.write(result, response);
		}
		return result;
	}
	//刷新购物车
	@RequestMapping("/refreshCart")
	@Transactional(propagation=Propagation.REQUIRED)
	public String refreshCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart =carts.calculate(cart);
		session.setAttribute("cart", cart);// 全部的商品
		return "/common/pre/searchBar";
	}
	//跳转到结算页面
	@RequestMapping("/toSettlement")
	@Transactional(propagation=Propagation.REQUIRED)
	public String toSettlement(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception {
		// 访问三层！
		List<EasybuyProductCategory> list = cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = cate.getProductCategoryList(3);
		// 放置对象！
		map.put("list", list);
		map.put("list2", list2);
		map.put("list3", list3);
		return "/pre/settlement/toSettlement";
	}
	//跳转到购物车
	@RequestMapping("/settlement1")
	@Transactional(propagation=Propagation.REQUIRED)
	public String settlement1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = carts.calculate(cart);
		// 放置对象！
		request.getSession().setAttribute("cart", cart);
		return "/pre/settlement/settlement1";
	}
	//地址
	@RequestMapping("/settlement2")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String settlement2(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception {
		EasybuyUser user = getUserFromSession(request);
		// 访问三层！
		List<EasybuyUserAddress> userAddressList = addresse.getEasybuyUserAddressAll(user.getId());// 获取用户收货地址
		// 放置对象！
		map.put("userAddressList", userAddressList);
		return "/pre/settlement/settlement2";
	}
	//生成订单
	@RequestMapping("/settlement3")
	@Transactional(propagation=Propagation.REQUIRED)
	public void settlement3(HttpServletRequest request, HttpServletResponse response,String addressId,String newAddress,String newRemark,Map<String,Object>map) throws Exception {
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart =carts.calculate(cart);
		//合算金额
		EasybuyUser user = getUserFromSession(request);
		//用户信息
		ReturnResult result = checkCart(request);
		//servlet判断购物车信息
		if (result.getStatus() == Constants.ReturnResult.FAIL) {
			PrintUtil.write(result, response);
		}
		// 创建对象！
		EasybuyUserAddress userAddress = new EasybuyUserAddress();
		if (addressId.equals("-1")) {
			HttpSession session = request.getSession();
			//添加地址
			EasybuyUser user1 = (EasybuyUser) session.getAttribute("easybuyUserLogin");
			userAddress.setRemark(newRemark);
			userAddress.setAddress(newAddress);
			userAddress.setUserId(user1.getId());
			userAddress.setId(addresse.addUserAddress(userAddress));
		} else {
			userAddress =addresse.getUserAddressById(Integer.parseInt(addressId));
		}

		// 生成订单
		EasybuyOrder order =orders.payShoppingCart(cart, user, userAddress.getAddress());
		clearCart(request, response);
		map.put("currentOrder", order);
	}
	//判断购物信息
	@RequestMapping("/checkCart")
	private ReturnResult checkCart(HttpServletRequest request) throws Exception {
		ReturnResult result = new ReturnResult();
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		cart = carts.calculate(cart);
		// 循环！
		for (ShoppingCartItem item : cart.getItems()) {
			// 访问三层！
			EasybuyProduct products = product.findById(item.getProduct().getId());
			if (products.getStock() < item.getQuantity()) {
				return result.returnFail(products.getName() + "商品数量不足");
			}
		}
		return result.returnSuccess();
	}
	//清空购物车
	@RequestMapping("/clearCart")
	public ReturnResult clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 结账后清空购物车、
		request.getSession().removeAttribute("cart");
		result.returnSuccess(null);
		PrintUtil.write(result, response);
		return result;
	}
	//修改购物车信息
	@RequestMapping("/modCart")
	//@ResponseBody 解决当写入数据到Response中时，却用Request的方式。虽然前台可以获得 request中的数据，会因为转换问题，而抛异常
	@ResponseBody
	@Transactional(propagation=Propagation.REQUIRED)
	public ReturnResult modCart(HttpServletRequest request, HttpServletResponse response,String entityId,String quantity) throws Exception {
		ReturnResult result = new ReturnResult();
		// 创建对象！
		HttpSession session = request.getSession();
		ShoppingCart cart = getCartFromSession(request);
		// 访问三层！
		EasybuyProduct products = product.findById(Integer.parseInt(entityId));
		if (EmptyUtils.isNotEmpty(quantity)) {
			if (Integer.parseInt(quantity) > products.getStock()) {
				return result.returnFail("商品数量不足");
			}
		}
		// 访问三层！
		cart =carts.modifyShoppingCart(entityId, quantity, cart);
		session.setAttribute("cart", cart);// 全部的商品
		PrintUtil.write(result, response);
		return result.returnSuccess();
	}
	//注销
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return "pre/Login";
	}
}
