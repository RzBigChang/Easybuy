package com.web.pre;

import com.entity.EasybuyCollect;
import com.entity.EasybuyNews;
import com.entity.EasybuyProduct;
import com.entity.EasybuyUser;
import com.entity.EasybuyProductCategory;
import com.service.news.impl.NewsServiceImpl;
import com.service.order.Impl.CartServiceImpl;
import com.service.product.impl.EasybuyProductServiceImpl;
//import com.service.order.impl.CartServiceImpl;
import com.service.product.impl.ProductCategoryServiceImpl;
//import com.service.product.impl.ProductServiceImpl;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.ReturnResult;
import com.utils.StringUtils;
import com.web.AbstractServlet;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 商品信息控制层！
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	private static final String TMP_DIR_PATH = "D:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files";
	private File destinationDir;
	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {//如果目录不存在，则新建目录
			tmpDir.mkdirs();
		}
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		destinationDir.mkdirs();
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}
		
	}
	/**
	 * Default constructor.
	 */
	public ProductServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 商品的主页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSize = request.getParameter("pageSize");
		//页面条数
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 5 : Integer.parseInt(pageSize);
		//当前页
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总记录数！
		int total = new EasybuyProductServiceImpl().getTotalCount();
		int pageCount=(total % rowPerPage == 0)?(total / rowPerPage):(total / rowPerPage)+1;
		Pager pager = new Pager(total, rowPerPage, currentPage);
		if(pager.getPageCount()<pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage-1);
		}
		pager.setUrl("/ProductServlet?action=index");
		// 访问三层！
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductAll(pager);
		// 放置对象！
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 5);
		return "/backend/product/productList";
	}
	
	/**
	 * 修改商品信息！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateAndDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Pager pager = null;
		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSize = request.getParameter("pageSize");
		//页面条数
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 5 : Integer.parseInt(pageSize);
		//当前页
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总记录数！
		int total = new EasybuyProductServiceImpl().getTotalCount();
		// 分页操作！
		int pageCount=(total % rowPerPage == 0)?(total / rowPerPage):(total / rowPerPage)+1;
		String sid=request.getParameter("id");
		int id=EmptyUtils.isEmpty(sid)?0:Integer.parseInt(sid);
		if(id==0) {
			pager = new Pager(total, rowPerPage, pageCount);
		}else {
			pager= new Pager(total, rowPerPage, currentPage);
		}
		if(pager.getPageCount()<pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage-1);
			
		}
		pager.setUrl("/ProductServlet?action=index");
		// 访问三层！
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductAll(pager);
		// 放置对象！
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 5);
		return "/backend/product/productList";
	}
	
	/**
	 * @param request
	 * @return
	 */
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

	/**
	 * 获取一级分类！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryProductList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");// id
		String menu = request.getParameter("menu");// id
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = new EasybuyProductServiceImpl().getProductRowCount(Integer.parseInt(category));
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/ProductServlet?action=queryProductList&category=" + category);
		// 访问三层！
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductListByCategoryId(Integer.parseInt(category), pager);
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
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
	/**
	 * 区间查询
	 * */
	public String queryProductList6(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Float price1=Float.parseFloat(request.getParameter("price1"));
		Float price2=Float.parseFloat(request.getParameter("price2"));
		int total = new EasybuyProductServiceImpl().getProductRowCount4(price1, price2);
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductListByPrice(price1, price2);
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("total", total);
		request.setAttribute("productList", productList);
		return "/pre/product/queryProductList";
	}
	public String queryLikeProductList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("keyWord");// 名字
		
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = new EasybuyProductServiceImpl().getProductRowCountByName(category);
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/ProductServlet?action=queryLikeProductList&category=" + category);
		// 访问三层！
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductListByCategoryName(category, pager);
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
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
	

	/**
	 * 获取二级分类！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryProductList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");// id

		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = new EasybuyProductServiceImpl().getProductRowCount2(Integer.parseInt(category));
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/ProductServlet?action=queryProductList2&category=" + category);
		// 访问三层！
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductListByCategoryId2(Integer.parseInt(category), pager);
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
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

	/**
	 * 获取三级分类！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryProductList3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");// id

		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		int total = new EasybuyProductServiceImpl().getProductRowCount3(Integer.parseInt(category));
		Pager pager = new Pager(total, rowPerPage, currentPage);
		// 访问三层！
		pager.setUrl("/ProductServlet?action=queryProductList3&category=" + category);
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductListByCategoryId3(Integer.parseInt(category), pager);
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
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

	public String queryProductList4(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小
		String pageSizeStr = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSizeStr) ? 8 : Integer.parseInt(pageSizeStr);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		HttpSession session = request.getSession();
		EasybuyUser user1 = (EasybuyUser) session.getAttribute("easybuyUserLogin");
		List<EasybuyCollect> listCollect = new CartServiceImpl().selectByUserId(user1.getId());
		int total = listCollect.size();
		Pager pager = new Pager(total, rowPerPage, currentPage);
		pager.setUrl("/ProductServlet?action=queryProductList4");
	
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductListByUser(listCollect);
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		List<EasybuyCollect> listCollecta=getUserFromSession(request);
		
		request.setAttribute("listCollect", listCollecta);
		if((pager.getRowPerPage()*currentPage)>=total) {
			request.setAttribute("productList", productList.subList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), productList.size()));
		}else {
			request.setAttribute("productList", productList.subList((pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage()*currentPage));
		}
		
		request.setAttribute("pager", pager);
		request.setAttribute("total", total);
		return "/pre/product/queryProductList";
	}

	/**
	 *收藏列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String queryProductDeatil(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		EasybuyProduct product = new EasybuyProductServiceImpl().findById(Integer.parseInt(id));
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
		List<EasybuyCollect> listCollect=getUserFromSession(request);
		if(listCollect!=null) {
			for (EasybuyCollect easybuyCollect : listCollect) {
				if(product.getId()==easybuyCollect.getProductId()) {
					request.setAttribute("easybuyCollect", easybuyCollect);
				}
				
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("product", product);
		return "/pre/product/productDeatil";
	}

	/**
	 * 根据id删除商品！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 获取商品ID！
		String id = request.getParameter("id");
		// 调用三层！
		int del = new EasybuyProductServiceImpl().delEasybuyProductById(Integer.parseInt(id));
		// 判断是否删除成功！
		if (del > 0) {
			result.returnSuccess("操作成功！");
		} else {
			result.returnFail("该商品可能在其他商品中有关联数据~ 不能删除哦！");
		}
		return result;
	}

	/**
	 * 修改或者上架操作！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult tomodify(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		int toModify = new EasybuyProductServiceImpl().addEasybuyProduct(easybuyProduct);
		// 实例化向页面输出对象！
		ReturnResult result = new ReturnResult();
		// 判断是否删除成功！
		if (toModify > 0) {
			result.returnSuccess("操作成功！");
		} else {
			result.returnFail("操作失败！请联系管理员。。");
		}
		return result;
	}

	/**
	 * 商品上架！
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String toAddUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getCategoryList(request, response);
		EasybuyProduct easybuyProduct = new EasybuyProduct();
		// 放一个空的商品信息对象！
		request.setAttribute("easybuyProduct", easybuyProduct);
		request.setAttribute("menu", 6);
		return "/backend/product/toAddProduct";
	}

	/**
	 * 根据Id查询对应的商品信息传递到修改页面！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取ID！
		String id = request.getParameter("id");
		String currentPage = request.getParameter("currentPage");
		// 调用三层！
		EasybuyProduct easybuyProduct = new EasybuyProductServiceImpl().getEasybuyProductById(Integer.parseInt(id));
		// 调用三层！
		List<EasybuyProductCategory> listOne = new ProductCategoryServiceImpl().getProductCategoryListOne();
  		List<EasybuyProductCategory> listTwo = new ProductCategoryServiceImpl().getProductCategoryListTwo();
 		List<EasybuyProductCategory> listThree = new ProductCategoryServiceImpl().getProductCategoryListThree();
		// 放到内置对象！
		request.setAttribute("listOne", listOne);
 		request.setAttribute("listTwo", listTwo);
 		request.setAttribute("listThree", listThree);
 		request.setAttribute("currentPage", currentPage);
		// 存放在内置对象中！
		request.setAttribute("easybuyProduct", easybuyProduct);
		return "/backend/product/toAddProduct";
	}

	/**
	 * 商品三级分类！！
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public void getCategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用三层！
		List<EasybuyProductCategory> listOne = new ProductCategoryServiceImpl().getProductCategoryListOne();
		List<EasybuyProductCategory> listTwo = new ProductCategoryServiceImpl().getProductCategoryListTwo();
		List<EasybuyProductCategory> listThree = new ProductCategoryServiceImpl().getProductCategoryListThree();
		// 放到内置对象！
		request.setAttribute("listOne", listOne);
		request.setAttribute("listTwo", listTwo);
		request.setAttribute("listThree", listThree);
	}

/**
 * 上传
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ReturnResult getImgs(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
							
						// 通过Arrays类的asList()方法创建固定长度的集合
						List<String> filType = Arrays.asList("gif", "bmp", "jpg");
						String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
						if (!filType.contains(ext)) // 判断文件类型是否在允许范围内
							return result.returnFail("上传失败，文件类型只能是gif、bmp、jpg");
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
				return result.returnFail("上传失败，文件太大，单个文件的最大限制是：" + upload.getSizeMax() + "bytes!");
			} catch (Exception e) {			
				e.printStackTrace();
			}

		}
		// 访问三层！
		int count = new EasybuyProductServiceImpl().addEasybuyProduct(product);
		// 判断是否上传成功！
		if(count>0) {
			return result.returnSuccess("操作成功！");	
		}else {
			return result.returnFail("操作失败！请联系管理员。。");
		}
	}
	
	
	/**
	 * 获取所有商品信息！
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String category(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总页数！
		int count = new ProductCategoryServiceImpl().getTotalCount();
		Pager pager = new Pager(count, rowPerPage, currentPage);
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);

		}
		List<EasybuyProductCategory> listCategoryP = new ProductCategoryServiceImpl().getEasybuyProductCategoryAll(null);
		pager.setUrl("/ProductServlet?action=category");
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 4);
		// 调用三层！
		List<EasybuyProductCategory> listCategory = new ProductCategoryServiceImpl().getEasybuyProductCategoryAll(pager);
		request.setAttribute("listCategoryP", listCategoryP);
		request.setAttribute("listCategory", listCategory);
		return "backend/user/Member_Money";
	}
	
	/**
	 * 删除商品信息！
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public ReturnResult delCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String type=request.getParameter("type");
		
		// 执行删除的时候先进去判断！
		int find = new ProductCategoryServiceImpl().getdParentId(Integer.parseInt(id));
		int count = new ProductCategoryServiceImpl().getProductById(type, Integer.parseInt(id));
		int del = -1;
		// 实例化向页面输出对象！
		ReturnResult result = new ReturnResult();
		// 判断外键是否有数据！
		if(find == 0&&count==0) {
			// 调用三层！
			del = new ProductCategoryServiceImpl().deleteEasybuyProductCategoryById(Integer.parseInt(id));
			// 判断是否删除成功！
			if (del > 0) {
				result.returnSuccess("删除成功！");
			}else {
				// 删除失败！！
				result.returnFail("该分类下有商品，无法删除！");
			}
		}else {
			// 删除失败！！
			result.returnFail("该分类下有商品，无法删除！");
		}
		return result;
	}
	
	
	
	/**
	 * 添加分类！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String AddProductCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<EasybuyProductCategory> productCategoryList1 = new ProductCategoryServiceImpl().getProductCategoryListOne();
		request.setAttribute("productCategoryList1", productCategoryList1);
		return "backend/category/toAddProductCategory";
	}

	/**
	 * 新增
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult insertCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取ID!
		String sid = request.getParameter("id");
		// 分页！
		int id = EmptyUtils.isEmpty(sid) ? 0 : Integer.parseInt(sid);
		String name = request.getParameter("name");
		String parentId = request.getParameter("parentId");
		String type = request.getParameter("type");
		EasybuyProductCategory easybuyProductCategory = new EasybuyProductCategory(id, name, Integer.parseInt(parentId),Integer.parseInt(type));
		// 访问三层！
		int ins = new ProductCategoryServiceImpl().insertEasybuyProductCategory(easybuyProductCategory);
		// 实例化向页面输出对象！
		ReturnResult result = new ReturnResult();
		// 判断是否删除成功！
		if (ins > 0) {
			result.returnSuccess("操作成功！");
		} else {
			result.returnFail("操作失败！");
		}
		return result;
	}
	
	/**
	 * 删除/修改成功后返回商品列表！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String ac(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Pager pager = null;
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		// 获取总页数！
		int count = new ProductCategoryServiceImpl().getTotalCount();
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
		pager.setUrl("/ProductServlet?action=category");
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 4);
		// 调用三层！
		List<EasybuyProductCategory> listCategory = new ProductCategoryServiceImpl()
				.getEasybuyProductCategoryAll(pager);
		// 放置对象！
		request.setAttribute("listCategory", listCategory);
		return "backend/user/Member_Money";
	}
	
	/**
	 * 添加分类根据一级分类获取二级分类信息！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getProductCategoryTwo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取一级分类的商品ID！
		String parentId = request.getParameter("parentId");
		// 访问三层！
		List<EasybuyProductCategory> productCategoryList2 = new ProductCategoryServiceImpl().getProductCategoryListByparentId(2, Integer.parseInt(parentId));
		// 放置对象！
		request.setAttribute("listTwo", productCategoryList2);
		return "backend/product/toAddProduct";
	}

	/**
	 * 添加分类根据二级分类获取三级分类信息！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getProductCategoryThree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取二级分类的商品ID！
		String parentId = request.getParameter("parentId");
		// 访问三层！
		List<EasybuyProductCategory> productCategoryList3 = new ProductCategoryServiceImpl().getProductCategoryListByparentId(3, Integer.parseInt(parentId));
		request.setAttribute("listThree", productCategoryList3);
		return "backend/product/toAddProduct";
	}

	/**
	 * 添加二级分类！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addCategoryLevel2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取ID！
		String parentId = request.getParameter("parentId");
		// 访问三层！
		List<EasybuyProductCategory> productCategoryList2 = new ProductCategoryServiceImpl()
				.getProductCategoryListByparentId(2, Integer.parseInt(parentId));
		// 放置对象！
		request.setAttribute("productCategoryList2", productCategoryList2);
		return "backend/category/toAddProductCategory";
	}
	
	/**
	 * 修改商品分类！
	 * @param reuest
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String upProductCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		EasybuyProductCategory parentProductCategory = null;
		// 根据id查询商品分类信息
		EasybuyProductCategory productCategory = new ProductCategoryServiceImpl()
				.getProductCategoryById(Integer.parseInt(id));
		if (productCategory.getType() == 3) {// 如果是三级分类查询父分类
			parentProductCategory = new ProductCategoryServiceImpl()
					.getProductCategoryById(productCategory.getParentId());
		}
		// 一级分类列表
		List<EasybuyProductCategory> productCategoryList1 = new ProductCategoryServiceImpl()
				.getProductCategoryListOne();
		// 二级分类列表
		List<EasybuyProductCategory> productCategoryList2 = new ProductCategoryServiceImpl()
				.getProductCategoryListTwo();
		// 三级分类列表
		List<EasybuyProductCategory> productCategoryList3 = new ProductCategoryServiceImpl()
				.getProductCategoryListThree();
		request.setAttribute("productCategory", productCategory);
		request.setAttribute("parentProductCategory", parentProductCategory);
		request.setAttribute("productCategoryList1", productCategoryList1);
		request.setAttribute("productCategoryList2", productCategoryList2);
		request.setAttribute("productCategoryList3", productCategoryList3);
		return "backend/category/toAddProductCategory";

	}

	@Override
	public Class getServletClass() {
		return ProductServlet.class;
	}

}
