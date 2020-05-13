package com.service.order.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EasybuyOrderDao;
import com.dao.MyBatisUtil;
import com.dao.OrderDao;
import com.dao.OrderDetailDao;
import com.dao.ProductDao;
import com.dao.impl.EasybuyOrderDaoImpl;
import com.dao.impl.EasybuyProductDaoImpl;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyUser;
import com.service.order.OrderService;
import com.utils.DataBaseUtil;
import com.utils.Pager;
import com.utils.ShoppingCart;
import com.utils.ShoppingCartItem;
import com.utils.StringUtils;
@Service
public class OderServiceImpl implements OrderService {
	@Autowired
	private OrderDao ordera;
	@Autowired
	private EasybuyOrderDao ordere;
	@Autowired
	private OrderDetailDao orderd;
	/**
	 * 根据用户ID得到对应信息业务！
	 * 
	 * @param userId
	 * @return
	 */
	
	@Override
	public List<EasybuyOrder> getEasybuyOrderAll(int userId) {
		List<EasybuyOrder>easybuyOrder=new ArrayList<EasybuyOrder>();
		try {
			if(userId==0) {
				easybuyOrder=ordere.findEasybuyOrderList0();
			}else {
				easybuyOrder=ordere.findEasybuyOrderList(userId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return easybuyOrder;
	}
	

	/**
	 * 获得总计数！
	 * 
	 * @return
	 */
	@Override
	public int getTotalCount() {
		int result=0;
		try {
			result=ordere.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * saveOrder方法
	 * */
	public void saveOrder(EasybuyOrder order) {
			int id=0;	
			id=ordera.saveOrderOne(order);
			
		if(id>0) {
			id=ordera.saveOrderTwo();
		}
		order.setId(id);
	}
	/**
	 * saveOrderDetail
	 * */
	public void saveOrderDetail(EasybuyOrderDetail detail,@Param("orderId")int orderId) {
		int id=0;
		id=orderd.saveOrderDetailOne(detail);
		if(id>0) {
			id=orderd.saveOrderDetailTwo();
		}
		detail.setId(id);
	}
	/**
	 * 购物！
	 *  不是订单
	 * @param cart
	 * @param user
	 * @param adress
	 * @return
	 */
	@Override
	public EasybuyOrder payShoppingCart(ShoppingCart cart, EasybuyUser user, String adress) {
		// 创建对象！
		EasybuyOrder order = new EasybuyOrder();
		int count=0;
		int counr=0;
		try {
			// 更新订单
			order.setUserId(user.getId() + "");
			order.setUserAddress(adress);
			order.setCreateTime(new Date());
			order.setCost(cart.getTotalCost());
			order.setSerialNumber(StringUtils.randomUUID());
			order.setLoginName(user.getLoginName());
			//veOrder(order);
			count=ordera.saveOrderOne(order);
		
			//new OrderDaoImpl(conn).saveOrder(order);
			for (ShoppingCartItem item : cart.getItems()) {
				// 更新订单详情
				EasybuyOrderDetail detail = new EasybuyOrderDetail();
				detail.setOrderId(order.getId());
				detail.setProductId(item.getProduct().getId());
				detail.setQuantity(item.getQuantity());
				detail.setCost(item.getCost());
				//new OrderDetailDaoImpl(conn).saveOrderDetail(detail, order.getId());
				//veOrderDetail(detail, order.getId());		
				counr=orderd.saveOrderDetailOne(detail);
				//orderId,productId,quantity,cost
				//System.out.println(detail.getId());
				//System.out.println(item.getProduct().getId());
				//new EasybuyProductDaoImpl(conn).updateStock(item.getProduct().getId(), item.getQuantity());
			}
		} catch (Exception e) {
			order = null;
			// 捕获异常！
			e.printStackTrace();
		}
		return order;
	}
	/**
	 * 获得购物信息！
	 * 
	 * @return
	 */
	@Override
	public List<DetailProduct> getEasybuyOrderDetail() {
		List<DetailProduct>list=new ArrayList<DetailProduct>();
		try {
			list=ordera.getEasybuyOrderDetail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获得下单购物订单信息！
	 *  不是订单
	 * @param orderId
	 * @return
	 */
	
	public List<EasybuyOrderDetail> getEasybuyOrderDetail(int orderId) {
		List<EasybuyOrderDetail>list=new ArrayList<EasybuyOrderDetail>();
		//list=orderDao.getEasybuyOrderDetail(orderId);
		return list;
	}
	/**
	 * 根据ID查询订单信息！
	 * OrderServlet未找到功能
	 * @param id
	 * @return
	 */
	@Override
	public int getUserByIdOrder(int id) {
		Integer result=0;
		try {
			result=ordere.findUserByIdOrder(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int cancel(EasybuyOrder eo) {
		int result=0;
		try {
			result=ordere.cancel(eo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//已删功能
	@Override
	public EasybuyOrder getByID(int id) {
		EasybuyOrder eo = new EasybuyOrder();
		eo=ordere.getByID(id);
		return eo;
	}
	
	
}
