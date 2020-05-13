package com.web.backend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyUser;
import com.service.order.OrderService;
import com.service.order.Impl.OderServiceImpl;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.web.AbstractServlet;

/**
 * Servlet implementation class OrderServlet
 */
@SuppressWarnings("serial")
@WebServlet("/OrderServlet")
public class OrderServlet extends AbstractServlet {
	

	/**
	 * Default constructor.
	 */
	public OrderServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * 订单列表
	 */
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//张健修改start
		HttpSession session = request.getSession();
		EasybuyUser easybuyUserLogin = (EasybuyUser)session.getAttribute("easybuyUserLogin");
		int userId = easybuyUserLogin.getId();
		//张健修改end
		// 获取当前页数！
		String currentPageStr = request.getParameter("currentPage");
		// 获取页大小！
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 2 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		List<EasybuyOrder> orderList = new OderServiceImpl().getEasybuyOrderAll(userId);
		int count = orderList.size();
		Pager pager = new Pager(count, rowPerPage, currentPage);
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);

		}
		pager.setUrl("/OrderServlet?action=index&userId=" + userId);
		// 获取当前登录用户ID！
		// 调用三层！
		List<DetailProduct> listOrderDetail = new OderServiceImpl().getEasybuyOrderDetail();
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
	/*
	 * 查询订单明细
	 * */
	public String queryOrderDeatil(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取id
		String id=request.getParameter("id");
		OrderService orderservice=new OderServiceImpl();
		List<DetailProduct>listOrderDetail=orderservice.getEasybuyOrderDetail();
		//放置内置对象
		request.setAttribute("orderId",Integer.parseInt(id));
		request.setAttribute("listOrderDetail", listOrderDetail);
		return"/backend/order/orderDetailList";
	}
	/*
	 * 全部订单
	 * */
	public String queryAllOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取当前页数
		String currentPageStr=request.getParameter("currentPage");
		//获取页大小
		String pageSize=request.getParameter("pageSize");
		int rowPerPage=EmptyUtils.isEmpty(pageSize) ? 2 : Integer.parseInt(pageSize);
		int currentPage=EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		List<EasybuyOrder>orderList=new OderServiceImpl().getEasybuyOrderAll(0);
		int total=new OderServiceImpl().getTotalCount();
		Pager pager=new Pager(total,rowPerPage,currentPage);
		pager.setUrl("/OrderServlet?action=queryAllOrder");
		OrderService orderservice=new OderServiceImpl();
		List<DetailProduct>listOrderDetail=orderservice.getEasybuyOrderDetail();
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
	/**
	 * 根据id获取订单信息
	 * 
	 * */
	public String GetByID1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		OrderService orderservice=new OderServiceImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		EasybuyOrder temp = orderservice.getByID(id);
		request.setAttribute("temp", temp);
		return "/backend/order/cancel";	
	}
	
	public String GetByID2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		OrderService orderservice=new OderServiceImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		EasybuyOrder temp = orderservice.getByID(id);
		request.setAttribute("temp", temp);
		return "/backend/order/true";	
	}
	/**
	 * 取消订单
	 * */
	public String Update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int cancel = 1;
		String message = request.getParameter("txtMessage");
		int id = Integer.parseInt(request.getParameter("txtId"));
		
		
		EasybuyOrder eo = new EasybuyOrder();
		eo.setCancel(cancel);
		eo.setMessage(message);
		eo.setId(id);
		
		
		OrderService orderservice=new OderServiceImpl();		
		orderservice.cancel(eo);
		return "/backend/order/orderList";
	}
	@Override
	public Class getServletClass() {
		return OrderServlet.class;
	}

}
