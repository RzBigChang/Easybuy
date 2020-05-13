package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.entity.DetailProduct;
import com.entity.EasybuyCollect;
import com.entity.EasybuyOrder;
import com.entity.EasybuyProduct;
import com.entity.EasybuyProductCategory;
import com.entity.EasybuyUser;
import com.service.address.EasybuyUserAddressService;
import com.service.address.Impl.EasybuyUserAddressServiceImpl;
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
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.PrintUtil;
import com.utils.ReturnResult;
import com.utils.SecurityUtils;
import com.utils.StringUtils;
import com.web.AbstractServlet;

@Controller
@Transactional
public class Backstage {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH = "D:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files";
	private File destinationDir;
	//订单
	@Autowired
	private OrderService oder;
	//用户
	@Autowired
	private EasybuyUserService euser;
	//地址
	@Autowired
	private EasybuyUserAddressService eaddress;
	//商品
	@Autowired
	private EasybuyProductService eproduct;
	@Autowired
	private ProductCategoryService cate;
	//配置图片路径
	public void init()throws ServletException{
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {//如果目录不存在，则新建目录
			tmpDir.mkdirs();
		}
		String realPath =servletContext.getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		destinationDir.mkdirs();
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}
	}
	//查询订单列表
	@RequestMapping("/index")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String index(HttpServletRequest request, HttpServletResponse response,int userId) throws ServletException, IOException {	
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 2 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		List<EasybuyOrder> orderList = oder.getEasybuyOrderAll(userId);
		int count = orderList.size();
		Pager pager = new Pager(count, rowPerPage, currentPage);
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);

		}
		pager.setUrl("/index?userId=" + userId);
		// 获取当前登录用户ID！
		// 调用三层！
		List<DetailProduct> listOrderDetail = oder.getEasybuyOrderDetail();
		// 放置内置对象！
		// 放置对象！
		if((pager.getRowPerPage()*currentPage)>=count) {
					request.setAttribute("orderList", orderList.subList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), orderList.size()));
		}else {
					request.setAttribute("orderList", orderList.subList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage()*currentPage));
		}
		request.setAttribute("listOrderDetail", listOrderDetail);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 1);
		return "backend/order/orderList";
		
		}
	//全部订单
	@RequestMapping("/queryAllOrder")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String queryAllOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取当前页数
		String currentPageStr=request.getParameter("currentPage");
		//获取页大小
		String pageSize=request.getParameter("pageSize");
		int rowPerPage=EmptyUtils.isEmpty(pageSize) ? 2 : Integer.parseInt(pageSize);
		int currentPage=EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		List<EasybuyOrder>orderList=oder.getEasybuyOrderAll(0);
		int total=oder.getTotalCount();
		Pager pager=new Pager(total,rowPerPage,currentPage);
		pager.setUrl("/queryAllOrder");
		List<DetailProduct>listOrderDetail=oder.getEasybuyOrderDetail();
		//放置对象
		if((pager.getRowPerPage()*currentPage)>=total) {
			request.setAttribute("orderList", orderList.subList((pager.getCurrentPage()-1)*pager.getRowPerPage(), orderList.size()));
		}else {
			request.setAttribute("orderList", orderList.subList((pager.getCurrentPage()-1)*pager.getRowPerPage(), pager.getRowPerPage()*currentPage));
		}
		request.setAttribute("listOrderDetail", listOrderDetail);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 9);
		return"/backend/order/orderList";
	}
	//用户信息
	@RequestMapping("/userIndex")
	@Transactional(propagation=Propagation.REQUIRED)
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//封装成对象
		EasybuyUser easyuyUser=new EasybuyUser();
		request.setAttribute("user",easyuyUser);
		request.setAttribute("menu", 2);
		return"backend/user/Member_User";
	}
	//加载用户列表
	@RequestMapping("/user")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String user(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception{
		//获取当前页数
		String currentPageStr=request.getParameter("currentPage");
		//获取页大小
		String pageSize=request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		//获取总页数
		int count=euser.getTotalCount();
		Pager pager=new Pager(count, rowPerPage, currentPage);
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);
		}
		pager.setUrl("/user");
		map.put("pager", pager);
		map.put("menu", 8);
		//调用三层
		List<EasybuyUser>list=euser.findEasybuyUserAll(pager);
		map.put("list", list);
		return"backend/user/Member_Packet";
	}
	/*
	 * 传递参数到修改页面
	 * */
	@RequestMapping("/toUpdateUser")
	public String toUpdateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取用户ID
		String id=request.getParameter("id");
		String currentPage=request.getParameter("currentPage");
		//调用三层
		EasybuyUser user=euser.findEasybuyUserById(Integer.parseInt(id));
		request.setAttribute("user",user);
		request.setAttribute("currentPage",currentPage);
		return"/backend/user/toUpdateUser";
	}
	//点击添加用户
	@RequestMapping("/tianjiauser")
	public String add(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception{
		//封装成对象
		EasybuyUser easyuyUser=new EasybuyUser();
		map.put("user",easyuyUser);
		return"/backend/user/toUpdateUser";
	}
	//添加and修改用户信息
	@RequestMapping("/modify")
	@Transactional(propagation=Propagation.REQUIRED)
	public ReturnResult modify(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map,@RequestParam("name")String name,@RequestParam(value="password",required=false)String password,@RequestParam("userName")String userName,@RequestParam("sex")int sex,@RequestParam("identityCode")String identityCode,@RequestParam("email")String email,@RequestParam("mobile")String mobile,@RequestParam("user")int user) throws Exception{
		ReturnResult result=new ReturnResult();
		name = request.getParameter("name");
		//设置密码为md5加密状态
		password = request.getParameter("password");
		userName = request.getParameter("userName");
		sex = Integer.parseInt(request.getParameter("sex"));
		identityCode = request.getParameter("identityCode");
		email = request.getParameter("email");
		mobile = request.getParameter("mobile");
		user =Integer.parseInt(request.getParameter("user"));
		//封装成对象
		EasybuyUser easybuyUser=new EasybuyUser();
		//获取用户需要更新的数据
		easybuyUser.setId(EmptyUtils.isEmpty(request.getParameter("thisId")) ? 0 :Integer.parseInt(request.getParameter("thisId")));
		easybuyUser.setLoginName(request.getParameter("name"));
		if(easybuyUser.getId()==0) {
			//调用查询用户名是否相同方法 并赋给count
			int count=euser.findLoginNameByName(easybuyUser.getLoginName());
			//判断count是否有值 有值则给出用户名不能相同提示  
			if(count==1) {
				result.returnFail("用户名已存在");
			}else {
				easybuyUser.setUserName(userName);
				easybuyUser.setLoginName(name);
				easybuyUser.setSex(sex);
				easybuyUser.setIdentityCode(identityCode);
				easybuyUser.setEmail(email);
				easybuyUser.setMobile(mobile);
				easybuyUser.setPassword(password);
				easybuyUser.setType(user);
				easybuyUser.setActivated(1); //1激活0未激活
				String codeUrl=CodeUtil.generateUniqueCode()+CodeUtil.generateUniqueCode();
				easybuyUser.setCodeUrl(codeUrl);
				//调用三层
				int modify=euser.addRegisterInfo(easybuyUser);
				//判断是否添加成功
				if(modify==1) {
					result.returnSuccess("添加用户信息成功 快去邮箱激活账户吧");
					PrintUtil.write(result, response);
				}else {
					result.returnFail("添加用户信息失败");
					PrintUtil.write(result, response);
				}
			}
		}else {
			easybuyUser.setUserName(request.getParameter(userName));
			easybuyUser.setSex(Integer.parseInt(request.getParameter("sex")));
			easybuyUser.setIdentityCode(request.getParameter(identityCode));
			easybuyUser.setEmail(request.getParameter(email));
			easybuyUser.setMobile(request.getParameter(mobile));
			easybuyUser.setType(Integer.parseInt(request.getParameter("user")));
			//调用三层
			int modify=euser.modifyEasybuyUserById(easybuyUser);
			//判断是否修改成功
			if(modify>0) {
				result.returnSuccess("修改用户信息成功");
				PrintUtil.write(result, response);
			}else {
				result.returnFail("修改用户信息失败");
				PrintUtil.write(result, response);
			}
		}
		return result;
	}
	//删除用户
	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,int id) throws Exception{
		ReturnResult result=new ReturnResult();
		//获取到要删除的用户Id
		//执行删除时 先判断用户是否有订单
		int find=oder.getUserByIdOrder(id);
		//执行删除时 先判断用户是否有地址
		int count=eaddress.getUserByIdAddress(id);
		//声明变量
		int del=0;
		//判断外键是否有数据
		if(find==0) {
			if(count==0) {
				//调用三层
				del=euser.delEasybuyUserById(id);
				if(del>0) {
					result.returnSuccess("删除成功");
					PrintUtil.write(result, response);
				}
			}else {
				//删除失败
				result.returnFail("该用户下有地址 无法删除");
				PrintUtil.write(result, response);
			}
		}else {
			//删除失败
			result.returnFail("该用户下有订单,无法删除");
			PrintUtil.write(result, response);
		}
	}
	//添加完成后跳转到当前页面
	@RequestMapping("/tzuser")
	public String adduser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取当前页数
		String currentPageStr=request.getParameter("currentPage");
		//获取页大小
		String pageSize=request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		//获取总页数
		int count=euser.getTotalCount();
		Pager pager=null;
		int pagerCount=(count% rowPerPage==0)?(count/rowPerPage):(count/rowPerPage)+1;
		//获取ID
		String sid=request.getParameter("thisId");
		int id=EmptyUtils.isEmpty(sid) ? 0 : Integer.parseInt(request.getParameter(sid));
		if(id==0) {
			pager=new Pager(count, rowPerPage, pagerCount);
		}else {
			pager=new Pager(count, rowPerPage, currentPage);
		}
		pager.setUrl("/user");
		//放置对象
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 8);
		//调用三层
		List<EasybuyUser>list=euser.findEasybuyUserAll(pager);
		request.setAttribute("list",list);
		return"/backend/user/Member_Packet";
	}
	//分类管理
	@RequestMapping("/category")
	public String category(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception {
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总页数！
		int count = cate.getTotalCount();
		Pager pager = new Pager(count, rowPerPage, currentPage);
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);

		}
		List<EasybuyProductCategory> listCategoryP = cate.getEasybuyProductCategoryAll(null);
		pager.setUrl("/category");
		map.put("pager", pager);
		map.put("menu", 4);
		// 调用三层！
		List<EasybuyProductCategory> listCategory = cate.getEasybuyProductCategoryAll(pager);
		map.put("listCategoryP", listCategoryP);
		map.put("listCategory", listCategory);
		return "backend/user/Member_Money";
	}
	//添加分类
	@RequestMapping("/AddProductCategory")
	public String AddProductCategory(HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception {
		List<EasybuyProductCategory> productCategoryList1 = cate.getProductCategoryListOne();
		map.put("productCategoryList1", productCategoryList1);
		return "backend/category/toAddProductCategory";
	}
	//添加分类根据一级分类获取二级分类信息
	@RequestMapping("/getProductCategoryTwo")
	public String getProductCategoryTwo(@RequestParam("parentId")String parentId,HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception {
		// 获取一级分类的商品ID！
		// 访问三层！
		List<EasybuyProductCategory> productCategoryList2 = cate.getProductCategoryListByparentId(2, Integer.parseInt(parentId));
		// 放置对象！
		map.put("listTwo", productCategoryList2);
		return "backend/product/toAddProduct";
	}
	//添加分类根据二级分类获取三级分类信息
	@RequestMapping("/getProductCategoryThree")
	public String getProductCategoryThree(@RequestParam("parentId")String parentId,HttpServletRequest request, HttpServletResponse response,Map<String,Object>map) throws Exception {
		// 访问三层！
		List<EasybuyProductCategory> productCategoryList3 = cate.getProductCategoryListByparentId(3, Integer.parseInt(parentId));
		map.put("listThree", productCategoryList3);
		return "backend/product/toAddProduct";
	}
	//新增
	@RequestMapping(value="/insertCategory",method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertCategory(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response,@Param("id")String id,@Param("name")String name,@Param("parentId")String parentId,@Param("type")String type) throws Exception {
		// 获取ID!
		// 分页！
		int id1 = EmptyUtils.isEmpty(id) ? 0 : Integer.parseInt(id);
		EasybuyProductCategory easybuyProductCategory = new EasybuyProductCategory(id1, name, Integer.parseInt(parentId),Integer.parseInt(type));
		// 访问三层！
		int ins = cate.insertEasybuyProductCategory(easybuyProductCategory);
		// 实例化向页面输出对象！
		ReturnResult result = new ReturnResult();
		// 判断是否删除成功！
		if (ins > 0) {
			result.returnSuccess("操作成功！");
			PrintUtil.write(result, response);
		} else {
			result.returnFail("操作失败！");
			PrintUtil.write(result, response);
		}
	}
	//添加二级分类
	@RequestMapping("/addCategoryLevel2")
	public String addCategoryLevel2(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取ID！
		String parentId = request.getParameter("parentId");
		// 访问三层！
		List<EasybuyProductCategory> productCategoryList2 = cate.getProductCategoryListByparentId(2, Integer.parseInt(parentId));
		// 放置对象！
		map.put("productCategoryList2", productCategoryList2);
		return "backend/category/toAddProductCategory";
	}
	//删除and添加后返回页面
	@RequestMapping("/ac")
	public String ac(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Pager pager = null;
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总页数！
		int count = cate.getTotalCount();
		int pageCount = (count % rowPerPage == 0) ? (count / rowPerPage) : (count / rowPerPage) + 1;
		String sid = request.getParameter("id");
		int id = EmptyUtils.isEmpty(sid) ? 0 : Integer.parseInt(sid);
		if (id == 0) {
			pager = new Pager(count, rowPerPage, pageCount);
		} else {
			pager = new Pager(count, rowPerPage, currentPage);
		}
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);

		}
		pager.setUrl("/category");
		map.put("pager", pager);
		map.put("menu", 4);
		// 调用三层！
		List<EasybuyProductCategory> listCategory = cate.getEasybuyProductCategoryAll(pager);
		// 放置对象！
		map.put("listCategory", listCategory);
		return "backend/user/Member_Money";
	}
	//删除
	@RequestMapping("/delCategory")
	@Transactional(propagation=Propagation.REQUIRED)
	public void delCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String type=request.getParameter("type");
		
		// 执行删除的时候先进去判断！
		int find = cate.getdParentId(Integer.parseInt(id));
		int count = cate.getProductById(type, Integer.parseInt(id));
		int del = -1;
		// 实例化向页面输出对象！
		ReturnResult result = new ReturnResult();
		// 判断外键是否有数据！
		if(find == 0&&count==0) {
			// 调用三层！
			del = cate.deleteEasybuyProductCategoryById(Integer.parseInt(id));
			// 判断是否删除成功！
			if (del > 0) {
				result.returnSuccess("删除成功！");
				PrintUtil.write(result, response);
			}else {
				// 删除失败！！
				result.returnFail("该分类下有商品，无法删除！");
				PrintUtil.write(result, response);
			}
		}else {
			// 删除失败！！
			result.returnFail("该分类下有商品，无法删除！");
			PrintUtil.write(result, response);
		}
	}
	//修改商品分类
	@RequestMapping("/upProductCategory")
	@Transactional(propagation=Propagation.REQUIRED)
	public String upProductCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		EasybuyProductCategory parentProductCategory = null;
		// 根据id查询商品分类信息
		EasybuyProductCategory productCategory = cate.getProductCategoryById(Integer.parseInt(id));
		if (productCategory.getType() == 3) {// 如果是三级分类查询父分类
			parentProductCategory = cate.getProductCategoryById(productCategory.getParentId());
		}
		// 一级分类列表
		List<EasybuyProductCategory> productCategoryList1 = cate.getProductCategoryListOne();
		// 二级分类列表
		List<EasybuyProductCategory> productCategoryList2 = cate.getProductCategoryListTwo();
		// 三级分类列表
		List<EasybuyProductCategory> productCategoryList3 = cate.getProductCategoryListThree();
		request.setAttribute("productCategory", productCategory);
		request.setAttribute("parentProductCategory", parentProductCategory);
		request.setAttribute("productCategoryList1", productCategoryList1);
		request.setAttribute("productCategoryList2", productCategoryList2);
		request.setAttribute("productCategoryList3", productCategoryList3);
		return "backend/category/toAddProductCategory";
	}
	//商品管理
	@RequestMapping("/spgl")
	@Transactional(propagation=Propagation.SUPPORTS)
	public String spgl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSize = request.getParameter("pageSize");
		//页面条数
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 5 : Integer.parseInt(pageSize);
		//当前页
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总记录数！
		int total = eproduct.getTotalCount();
		int pageCount=(total % rowPerPage == 0)?(total / rowPerPage):(total / rowPerPage)+1;
		Pager pager = new Pager(total, rowPerPage, currentPage);
		if(pager.getPageCount()<pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage-1);
		}
		pager.setUrl("/spgl");
		// 访问三层！
		List<EasybuyProduct> productList = eproduct.getEasybuyProductAll(pager);
		// 放置对象！
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 5);
		return "/backend/product/productList";
	}
	//商品下架
	@RequestMapping("/deleteById")
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 获取商品ID！
		String id = request.getParameter("id");
		// 调用三层！
		int del = eproduct.delEasybuyProductById(Integer.parseInt(id));
		// 判断是否删除成功！
		if (del > 0) {
			result.returnSuccess("操作成功！");
			PrintUtil.write(result, response);
		} else {
			result.returnFail("该商品可能在其他商品中有关联数据~ 不能删除哦！");
			PrintUtil.write(result, response);
		}
	}
	//修改商品信息
	@RequestMapping(value="/updateAndDel",method = RequestMethod.GET)
	public String updateAndDel(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response,String currentPage,String pageSize,String id) throws Exception {
		Pager pager = null;
		//页面条数
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 5 : Integer.parseInt(pageSize);
		//当前页
		int currentPage1 = EmptyUtils.isEmpty(currentPage) ? 1 : Integer.parseInt(currentPage);
		// 获取总记录数！
		int total = eproduct.getTotalCount();
		// 分页操作！
		int pageCount=(total % rowPerPage == 0)?(total / rowPerPage):(total / rowPerPage)+1;
		int ids=EmptyUtils.isEmpty(id)?0:Integer.parseInt(id);
		if(ids==0) {
			pager = new Pager(total, rowPerPage, pageCount);
		}else {
			pager= new Pager(total, rowPerPage, currentPage1);
		}
		if(pager.getPageCount()<pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage1-1);
			
		}
		pager.setUrl("/spgl");
		// 访问三层！
		List<EasybuyProduct> productList = eproduct.getEasybuyProductAll(pager);
		// 放置对象！
		map.put("productList", productList);
		map.put("pager", pager);
		map.put("menu", 5);
		return "/backend/product/productList";
	}
	//把商品信息传递到修改页面
	@RequestMapping("/getProduct")
	public String getProduct(Map<String,Object>map,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取ID！
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		// 调用三层！
		EasybuyProduct easybuyProduct = eproduct.getEasybuyProductById(Integer.parseInt(id));
		// 调用三层！
		List<EasybuyProductCategory> listOne = cate.getProductCategoryListOne();
  		List<EasybuyProductCategory> listTwo = cate.getProductCategoryListTwo();
 		List<EasybuyProductCategory> listThree = cate.getProductCategoryListThree();
		// 放到内置对象！
		map.put("listOne", listOne);
		map.put("listTwo", listTwo);
		map.put("listThree", listThree);
		map.put("currentPage", currentPage);
		// 存放在内置对象中！
		map.put("easybuyProduct", easybuyProduct);
		return "/backend/product/toAddProduct";
	}
	//上传图片
	@RequestMapping("/getImgs")
	public void getImgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取tomcat路径
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {//如果目录不存在，则新建目录
			tmpDir.mkdirs();
		}
		String realPath =servletContext.getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		destinationDir.mkdirs();
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}
//-------------------------------------------------------------------------
		ReturnResult result = new ReturnResult();
		// ************
		EasybuyProduct product = new EasybuyProduct();
		String fieldName = ""; // 表单字段元素的name属性值
		// 请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);		
		if (isMultipart) {// 是多文件类型
			// 创建对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小4kb
			factory.setSizeThreshold(4096);			
			factory.setRepository(tmpDir);
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置单个文件的最大限制
			upload.setFileSizeMax(1024 * 30*1024);
		
			// 解析表单中所有信息
			try {
				// 获得request中的FileItem集合
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (!item.isFormField()) { // 文件表单字段
						String fileName  = item.getName().substring(
						 item.getName().lastIndexOf("."));
						File file = new File(destinationDir, fileName);
						 fileName = StringUtils.randomUUID()
						 + item.getName().substring(
						 item.getName().lastIndexOf("."));
						 file = new File(destinationDir, fileName);//图片名与商品ID一致
						 //打印路径
						System.out.println("destinationDir"+destinationDir);
						System.out.println("uuid"+StringUtils.randomUUID());
						System.out.println("item.getName()"+item.getName());
						System.out.println("item.getName()"+item.getName());
						
						System.out.println("item.getName().lastIndexOf"+item.getName().lastIndexOf("."));
					 
						System.out.println(item.getName().substring( item.getName().lastIndexOf(".")));
						System.out.println("filename"+"\t"+fileName);
						// 通过Arrays类的asList()方法创建固定长度的集合
						List<String> filType = Arrays.asList("gif", "bmp", "jpg");
						String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
						if (!filType.contains(ext)) // 判断文件类型是否在允许范围内
							result.returnFail("上传失败，文件类型只能是gif、bmp、jpg");
							
						else {
							if (fileName != null && !fileName.equals("")) {		
								// ************
								product.setFileName(file.getName());
								// ************
								item.write(file);//保存商品图片
								
							}
						}
					} else {// 普通表单信息
							// ************
						fieldName = item.getFieldName();
						if (fieldName != null && !fieldName.equals("")) {
							if (fieldName.equals("categoryLevel1Id")) {
								product.setCategoryLevel1(Integer.parseInt(item.getString("UTF-8")));
							} else if (fieldName.equals("categoryLevel2Id")) {
								product.setCategoryLevel2(Integer.parseInt(item.getString("UTF-8")));
							} else if (fieldName.equals("categoryLevel3Id")) {
								product.setCategoryLevel3(Integer.parseInt(item.getString("UTF-8")));
							} else if (fieldName.equals("name")) {
								product.setName(item.getString("UTF-8"));
							} else if (fieldName.equals("price")) {
								product.setPrice(Float.parseFloat(item.getString("UTF-8")));
							} else if (fieldName.equals("stock")) {
								product.setStock(Integer.parseInt(item.getString("UTF-8")));
							} else if (fieldName.equals("description")) {
								product.setDescription(item.getString("UTF-8"));
							} else if (fieldName.equals("id")) {
								product.setId(Integer.parseInt(item.getString("UTF-8")));
							}
							
						}
						// ************
					}
				}
				// 调用三层，添加数据到数据库中
								
			} catch (FileUploadBase.SizeLimitExceededException ex) {
				result.returnFail("上传失败，文件太大，单个文件的最大限制是：" + upload.getSizeMax() + "bytes!");
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		// 访问三层！
		int count = eproduct.addEasybuyProduct(product);
		// 判断是否上传成功！
		if(count>0) {
		  result.returnSuccess("操作成功！");
	  PrintUtil.write(result, response);
		}else {			  
			result.returnFail("操作失败！请联系管理员。。");
			PrintUtil.write(result, response);
		}
	}
	//上架操作
	@RequestMapping("/tomodify")
	public void tomodify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 封装成对象！
		EasybuyProduct easybuyProduct = new EasybuyProduct();
		// 获取用户需要上架的商品信息！
		easybuyProduct.setId(
				EmptyUtils.isEmpty(request.getParameter("id")) ? 0 : Integer.parseInt(request.getParameter("id")));
		easybuyProduct.setCategoryLevel1(Integer.parseInt(request.getParameter("one")));
		easybuyProduct.setCategoryLevel2(Integer.parseInt(request.getParameter("two")));
		easybuyProduct.setCategoryLevel3(Integer.parseInt(request.getParameter("three")));
		easybuyProduct.setFileName(request.getParameter("img"));
		easybuyProduct.setName(request.getParameter("name"));
		easybuyProduct.setPrice(Float.parseFloat(request.getParameter("price")));
		easybuyProduct.setStock(Integer.parseInt(request.getParameter("stock")));
		easybuyProduct.setDescription(request.getParameter("description"));
		// 调用三层！
		int toModify = eproduct.addEasybuyProduct(easybuyProduct);
		 
		 
		// 实例化向页面输出对象！
		ReturnResult result = new ReturnResult();
		// 判断是否删除成功！
		if (toModify > 0) {
			result.returnSuccess("操作成功！");
			PrintUtil.write(result, response);
		} else {
			result.returnFail("操作失败！请联系管理员。。");
			PrintUtil.write(result, response);
		}
	 
	}
	//上架
	@RequestMapping("/toAddUpdate")
	public String toAddUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getCategoryList(request, response);
		EasybuyProduct easybuyProduct = new EasybuyProduct();
		// 放一个空的商品信息对象！
		request.setAttribute("easybuyProduct", easybuyProduct);
		request.setAttribute("menu", 6);
		return "/backend/product/toAddProduct";
	}
	//商品的三级分类
	@RequestMapping("/getCategoryList")
	public void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用三层！
		List<EasybuyProductCategory> listOne = cate.getProductCategoryListOne();
		List<EasybuyProductCategory> listTwo = cate.getProductCategoryListTwo();
		List<EasybuyProductCategory> listThree = cate.getProductCategoryListThree();
		// 放到内置对象！
		request.setAttribute("listOne", listOne);
		request.setAttribute("listTwo", listTwo);
		request.setAttribute("listThree", listThree);
	}
	//主页面的搜索框
	@RequestMapping("/queryLikeProductList")
	public String queryLikeProductList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("keyWord");// 名字
		
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = eproduct.getProductRowCountByName(category);
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/queryLikeProductList?category=" + category);
		// 访问三层！
		List<EasybuyProduct> productList = eproduct.getEasybuyProductListByCategoryName(category, pager);
		List<EasybuyProductCategory> list = cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = cate.getProductCategoryList(3);
		// 放置对象！
		List<EasybuyCollect> listCollect=getUserFromSession(request);
		request.setAttribute("listCollect", listCollect);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("total", total);
		return "/pre/product/queryProductList";
	}
	//获取一级分类
	@RequestMapping("/queryProductList")
	public String queryProductList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");// id
		String menu = request.getParameter("menu");// id
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = eproduct.getProductRowCount(Integer.parseInt(category));
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/queryProductList?category=" + category);
		// 访问三层！
		List<EasybuyProduct> productList = eproduct.getEasybuyProductListByCategoryId(Integer.parseInt(category), pager);
		List<EasybuyProductCategory> list = cate.getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = cate.getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = cate.getProductCategoryList(3);
		List<EasybuyCollect> listCollect=getUserFromSession(request);
		request.setAttribute("listCollect", listCollect);
		// 放置对象！
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("total", total);
		request.setAttribute("menu", menu);
		return "/pre/product/queryProductList";
	}
	
	public void print(Object msg,HttpServletResponse rep) throws IOException {
		String info=JSON.toJSONString(msg);
		System.out.println(info);
		PrintWriter out=rep.getWriter();
		out.print(info);
	}
	private List<EasybuyCollect> getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 获取对象！
		EasybuyUser user = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		List<EasybuyCollect> list=null;
		if(user!=null) {
			list=new CartServiceImpl().selectByUserId(user.getId());
		}
		
		return list;
	}
}
