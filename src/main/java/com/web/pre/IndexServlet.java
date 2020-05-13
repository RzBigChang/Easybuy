 package com.web.pre;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.EasybuyNews;
import com.entity.EasybuyProductCategory;
import com.entity.EasybuyProduct;
import com.service.news.impl.NewsServiceImpl;
import com.service.product.impl.ProductCategoryServiceImpl;
import com.service.product.EasybuyProductService;
import com.service.product.impl.EasybuyProductServiceImpl;

import com.web.AbstractServlet;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
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
	 * 主页面加载
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 访问三层！
		List<EasybuyProductCategory> list = new ProductCategoryServiceImpl().getProductCategoryList(1);
		List<EasybuyProductCategory> list2 = new ProductCategoryServiceImpl().getProductCategoryList(2);
		List<EasybuyProductCategory> list3 = new ProductCategoryServiceImpl().getProductCategoryList(3);
		List<EasybuyNews> newsList = new NewsServiceImpl().queryNewsList(null);
		// 访问三层！
		EasybuyProductService ser=	new EasybuyProductServiceImpl();
		List<EasybuyProduct> productList = new EasybuyProductServiceImpl().getEasybuyProductList();


		request.setAttribute("productList", productList);

		request.setAttribute("newsList", newsList);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		return "pre/Index";
	}

	@Override
	public Class getServletClass() {
		return IndexServlet.class;
	}

}
