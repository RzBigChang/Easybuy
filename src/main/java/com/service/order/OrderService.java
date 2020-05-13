package com.service.order;

import java.util.List;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyUser;
import com.utils.Pager;
import com.utils.ShoppingCart;

public interface OrderService {
	/**
	 * 根据用户ID得到对应信息业务！
	 * 
	 * @param userId
	 * @return
	 */
	List<EasybuyOrder> getEasybuyOrderAll(int userId);
	/**
	 * 获得总计数！
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 购物！
	 * 
	 * @param cart
	 * @param user
	 * @param adress
	 * @return
	 */
	EasybuyOrder payShoppingCart(ShoppingCart cart, EasybuyUser user, String adress);

	/**
	 * 获得购物信息！
	 * 
	 * @return
	 */
	public List<DetailProduct> getEasybuyOrderDetail();

	/**
	 * 获得下单购物订单信息！
	 * 
	 * @param orderId
	 * @return
	 */
	public List<EasybuyOrderDetail> getEasybuyOrderDetail(int orderId);

	// *********
	/**
	 * 根据ID查询订单信息！
	 *  未找到方法
	 * @param id
	 * @return
	 */
	int getUserByIdOrder(int id);
	// *********
	/**
	 * 取消订单
	 * */
	public int cancel(EasybuyOrder eo);
	/**
	 * 根据id查询订单信息
	 * */
	public EasybuyOrder getByID(int id);
}
