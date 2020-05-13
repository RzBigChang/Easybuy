package com.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.EasybuyOrderDao;
import com.entity.EasybuyOrder;
import com.entity.EasybuyOrderDetail;
/**
 * 订单信息数据访问层！
 * @author Administrator
 *
 */
public class EasybuyOrderDaoImpl extends BaseDao implements EasybuyOrderDao {

	public EasybuyOrderDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 根据用户信息查询对应订单信息！
	 * @param id
	 * @return
	 */
	@Override
	public List<EasybuyOrder> findEasybuyOrderList(int userId) {
		List<EasybuyOrder>list=new ArrayList<EasybuyOrder>();
		String sql=null;
		if(userId==0) {
			sql="Select * from easybuy_order ORDER BY createTime DESC";
		}else {
			sql="Select * from easybuy_order where userId=? ORDER BY createTime DESC";
		}
		Object[]parms= {userId};
		
		try {
			if(userId==0) {
				
				rs=super.excuteQuery(sql, null);
			}else {
				
				rs=super.excuteQuery(sql, parms);
			}
			if(rs!=null) {
				while(rs.next()) {
					EasybuyOrder easybuyOrder=new EasybuyOrder();
					easybuyOrder.setId(rs.getInt(1));
					easybuyOrder.setUserId(rs.getString(2));
					easybuyOrder.setLoginName(rs.getString(3));
					easybuyOrder.setUserAddress(rs.getString(4));
					easybuyOrder.setCreateTime(rs.getDate(5));
					easybuyOrder.setCost(rs.getFloat(6));
					easybuyOrder.setStatus(rs.getInt(7));
					easybuyOrder.setType(rs.getInt(8));
					easybuyOrder.setSerialNumber(rs.getString(9));
					easybuyOrder.setCancel(rs.getInt(10));
					easybuyOrder.setTime(rs.getString(11));
					easybuyOrder.setMessage(rs.getString(12));
					list.add(easybuyOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, null, rs);
		}
		return list;
	}
	/**
	 * 获得总记录数！
	 * @return
	 */
	@Override
	public int getTotalCount() {
		int result=0;
		try {
			String sql="select COUNT(1) from easybuy_order";
			rs=super.excuteQuery(sql,null);
			if(rs!=null) {
				while(rs.next()) {
					result=rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, null, rs);
		}
		return result;
	}
	//*********
		/**
		 * 根据ID查询订单信息！
		 * @param id
		 * @return
		 */
	@Override
	public int findUserByIdOrder(int id) {
		int result=0;
		try {
			String sql="select COUNT(1) from easybuy_order where userId=?";
			Object[]parms= {id};
			rs=super.excuteQuery(sql, parms);
			if(rs!=null) {
				while(rs.next()) {
					result=rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, null, rs);
		}
		return result;
	}
	@Override
	public int cancel(EasybuyOrder eo) {
		int result = 0;
		String sql = "update easybuy_order set cancel=?,message=? where id=?";
		Object[]params= {
				eo.getCancel(),
				eo.getMessage(),
				eo.getId()
		};
		result=super.MYexecuteUpdate(sql, params);
		return result;
	}
	@Override
	public EasybuyOrder getByID(int id) {
		EasybuyOrder eo = null;
		String sql = "SELECT * FROM easybuy_order where id=?";
		Object[]params= {id};
		try {
			rs=super.MYexecuteQuery(sql, params);
			while(rs.next()) {
				eo = new EasybuyOrder();
				eo.setId(rs.getInt(1));
				eo.setUserId(rs.getString(2));
				eo.setLoginName(rs.getString(3));
				eo.setUserAddress(rs.getString(4));
				eo.setCreateTime(rs.getDate(5));
				eo.setCost(rs.getFloat(6));
				eo.setStatus(rs.getInt(7));
				eo.setType(rs.getInt(8));
				eo.setSerialNumber(rs.getString(9));
				eo.setCancel(rs.getInt(10));
				eo.setTime(rs.getString(11));
				eo.setMessage(rs.getString(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return eo;
	}
	@Override
	public List<EasybuyOrder> findEasybuyOrderList0() {
		// TODO Auto-generated method stub
		return null;
	}
}
