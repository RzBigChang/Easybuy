package com.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyOrderDetail;

public interface OrderDetailDao {
	//订单
    public void saveOrderDetail(EasybuyOrderDetail detail,@Param("orderId")int orderId) throws SQLException ;
    
    //订单一号
    public int saveOrderDetailOne(EasybuyOrderDetail detail);
    
    //订单二号
    public int saveOrderDetailTwo();
}
