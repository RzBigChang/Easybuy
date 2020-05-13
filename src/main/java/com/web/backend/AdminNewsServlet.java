package com.web.backend;

import com.entity.EasybuyNews;
import com.service.news.impl.NewsServiceImpl;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.web.AbstractServlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminNewsServlet
 */
@WebServlet("/AdminNewsServlet")
public class AdminNewsServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminNewsServlet() {
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
	 * 获取资讯列表信息！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryNewsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// 获取当前页数！
				String currentPageStr = request.getParameter("currentPage");
				// 获取页大小！
				String pageSize = request.getParameter("pageSize");
				int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
				int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
				int count = new NewsServiceImpl().getTotalCount();
				Pager pager = new Pager(count, rowPerPage, currentPage);
				if(pager.getPageCount()<pager.getCurrentPage()) {
					pager.setCurrentPage(currentPage-1);		
				}
				pager.setUrl("/AdminNewsServlet?action=queryNewsList");
				// 访问三层！
				List<EasybuyNews> newsList = new NewsServiceImpl().queryNewsList(pager);
				// 放置内置对象！
				request.setAttribute("newsList", newsList);
				request.setAttribute("pager", pager);
				request.setAttribute("menu", 7);
				return "/backend/news/newsList";
	}
	/**
	 * 新闻详情！
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String newsDeatil(HttpServletRequest request,HttpServletResponse response)throws Exception{
		// 获取ID！
		String id = request.getParameter("id");
		// 访问三层！
		EasybuyNews news=new NewsServiceImpl().findNewsById(Integer.parseInt(id));
		// 放置内置对象！
		request.setAttribute("news",news);
		request.setAttribute("menu", 7);
		return "/backend/news/newsDetail";
	}
	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return AdminNewsServlet.class;
	}

}
