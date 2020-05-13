package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;

/**
 * 订单信息数据访问层！
 * @author Administrator
 *
 */
public interface EasybuyOrderDao {
	
	/**
	 * 使用Logger记录日志！
	 */
	
	/**
	 * 根据用户信息查询对应订单信息！
	 * @param id
	 * @return
	 */
	List<EasybuyOrder> findEasybuyOrderList0();
	
	
	List<EasybuyOrder> findEasybuyOrderList(@Param("userId")int userId);
	
	/**
	 * 获得总记录数！
	 * @return
	 */
	public int getTotalCount();
	
	//*********
	/**
	 * 根据ID查询订单信息！
	 * @param id
	 * @return
	 */
	int findUserByIdOrder(@Param("id") int id);
	/**
	 * 取消订单
	 * */
	public int cancel(EasybuyOrder eo);
	/**
	 * 根据id查询订单信息 已删功能
	 * */
	public EasybuyOrder getByID(int id);
}
