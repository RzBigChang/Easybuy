package com.web.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.EasybuyFankui;
import com.entity.EasybuyNews;
import com.service.fankui.EasybuyFankuiService;
import com.service.fankui.impl.EasybuyFankuiServiceImpl;
import com.service.news.impl.NewsServiceImpl;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.web.AbstractServlet;

@WebServlet("/EasybuyFankuiServlet")
public class EasybuyFankuiServlet extends AbstractServlet{
	private static final long serialVersionUID = 1L;
	  /**
     * Default constructor. 
     */
    public EasybuyFankuiServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	//添加用户反馈
	public String SaveFankui(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String pl=request.getParameter("txtpl");
		int manyidu=Integer.parseInt(request.getParameter("f"));
		String name=request.getParameter("8879");
		String sj=request.getParameter("8878");
		EasybuyFankui fankui=new EasybuyFankui();
		fankui.setFaxie(pl);
		fankui.setManyidu(manyidu);
		fankui.setName(name);
		fankui.setIpone(sj);
		EasybuyFankuiService fankuiservice=new EasybuyFankuiServiceImpl();
		fankuiservice.saveFankui(fankui);
		return "/pre/Tiaozhuan";
		}
	public String FankuiList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取当前页数！
		// 获取当前页数！
				String currentPageStr = request.getParameter("currentPage");
				// 获取页大小！
				String pageSize = request.getParameter("pageSize");
				int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
				int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
				int count = new EasybuyFankuiServiceImpl().getTotalCount();
				Pager pager = new Pager(count, rowPerPage, currentPage);
				if(pager.getPageCount()<pager.getCurrentPage()) {
					pager.setCurrentPage(currentPage-1);		
				}
				pager.setUrl("/EasybuyFankuiServlet?action=FankuiList");
		// 访问三层！
		List<EasybuyFankui> FankuiList = new EasybuyFankuiServiceImpl().FankuiList(pager);
		// 放置内置对象！
		request.setAttribute("FankuiList", FankuiList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 8);
		return "/backend/fankui/fankuiList";  
	}
	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return EasybuyFankuiServlet.class;
	}

}
