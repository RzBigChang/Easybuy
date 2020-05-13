package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.EasybuyUserAddressDao;
import com.entity.EasybuyUserAddress;

public class EasybuyUserAddressDaoImpl extends BaseDao implements EasybuyUserAddressDao {

	public EasybuyUserAddressDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EasybuyUserAddress> findEasybuyUserAddressAll(int userId) {
		// SQL语句！
				String sql = "SELECT id,address,createTime,userId,isDefault,remark FROM easybuy_user_address WHERE userId = ?";
				// 创建数组为占位符赋值！
				Object[] parms = { userId };
				ResultSet set = null;
				EasybuyUserAddress easybuyUserAddress = null;
				List<EasybuyUserAddress> list = new ArrayList<EasybuyUserAddress>();
				try {
					// 调用方法！
					set = super.excuteQuery(sql, parms);
					if (set != null) {
						while (set.next()) {
							easybuyUserAddress = new EasybuyUserAddress();
							easybuyUserAddress.setId(set.getInt("id"));
							easybuyUserAddress.setAddress(set.getString("address"));
							easybuyUserAddress.setCreateTime(set.getString("createTime"));
							easybuyUserAddress.setUserId(set.getInt("userId"));
							easybuyUserAddress.setIsDefault(set.getInt("isDefault"));
							easybuyUserAddress.setRemark(set.getString("remark"));
							// 添加到集合！
							list.add(easybuyUserAddress);
						}
					}
				} catch (Exception e) {
					// 捕获异常！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(null, null, rs);
				}
				return list;
	}

	@Override
	public int updateEasybuyUserAddressById(EasybuyUserAddress easybuyUserAddress) {
		ResultSet rs = null;
		// SQL语句！
		String sql = "INSERT INTO easybuy_user_address(address,createTime,userId,isDefault,remark) VALUES(?,NOW(),?,0,?)";
		// 创建数组为占位符赋值！
		Object[] parms = { easybuyUserAddress.getAddress(), easybuyUserAddress.getUserId(),
				easybuyUserAddress.getRemark() };
		int temo = -1;
		try {
			temo = super.excuteUpdate(sql, parms);
			if (temo > 0) {
				String sql1 = "select max(id) from easybuy_user_address";
				rs = super.excuteQuery(sql1, null);
				if (rs.next()) {
					temo = rs.getInt(1);
				}
			}

		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			closeAll(null, null, null);
		}

		return temo;
	}

	@Override
	public EasybuyUserAddress getUserAddressById(int id) {
		// SQL语句！
				String sql = "SELECT id,address,createTime,userId,isDefault,remark FROM easybuy_user_address WHERE id = ?";
				// 创建数组为占位符赋值！
				Object[] parms = { id };
				ResultSet set = null;
				EasybuyUserAddress easybuyUserAddress = null;

				try {
					// 调用方法！
					set = super.excuteQuery(sql, parms);
					if (set != null) {
						while (set.next()) {
							easybuyUserAddress = new EasybuyUserAddress();
							easybuyUserAddress.setId(set.getInt("id"));
							easybuyUserAddress.setAddress(set.getString("address"));
							easybuyUserAddress.setCreateTime(set.getString("createTime"));
							easybuyUserAddress.setUserId(set.getInt("userId"));
							easybuyUserAddress.setIsDefault(set.getInt("isDefault"));
							easybuyUserAddress.setRemark(set.getString("remark"));

						}
					}
				} catch (Exception e) {
					// 捕获异常！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(null, null, set);
				}
				return easybuyUserAddress;
	}

	@Override
	public int findUserByIdAddress(int userId) {
		// 声明字符集！
				ResultSet rs = null;
				int result = -1;
				try {
					// SQL语句！
					String sql = "SELECT COUNT(1) FROM easybuy_user_address WHERE userId = ?";
					// 创建数组为占位符赋值！
					Object[] parms = {userId};
					// 调用方法！
					rs = super.excuteQuery(sql, parms);
					if (rs != null) {
						while (rs.next()) {
							result = rs.getInt(1);
						}
					}
				} catch (Exception e) {
					// 捕获异常！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(null, null, rs);
				}
				return result;
	}

}
