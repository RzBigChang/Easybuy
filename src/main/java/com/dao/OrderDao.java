package com.dao;

import java.util.List;

import com.entity.DetailProduct;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
import com.utils.Page;
import com.utils.Pager;
/**
 * 订单信息数据访问层！
 * @author Administrator
 *
 */
public interface OrderDao {
	/**
	 * 获取订单信息
	 * @param order
	 * 
	 */
	public void saveOrder(EasybuyOrder order);
	
	/**
	 * order表添加
	 * 新加
	 * */
	public int saveOrderOne(EasybuyOrder order);
	
	/**
	 * order查询
	 * 新加
	 * */	
	
	public int saveOrderTwo();
	/**
	 * 获取订单信息！
	 * @return
	 */
	public List<DetailProduct> getEasybuyOrderDetail();
	/**
	 * 根据订单号获取订单详情信息！ 已删除功能
	 * @param orderId
	 * @return
	 */
	public List<EasybuyOrderDetail> getEasybuyOrderDetail(int orderId);
	
}
