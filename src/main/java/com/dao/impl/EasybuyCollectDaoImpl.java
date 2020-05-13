package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.EasybuyCollectDao;
import com.entity.EasybuyCollect;
import com.utils.DataBaseUtil;

public class EasybuyCollectDaoImpl extends BaseDao implements EasybuyCollectDao {

	public EasybuyCollectDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	/**
	 * 收藏商品！
	 * @param userId
	 * @param productId
	 * @param productNum
	 * @param type
	 * @return
	 */
	public int addCollect(int userId, int productId, int productNum, int type) {
		String sql="INSERT  into easybuy_collect(id,userId,productId,productNum,type) VALUES (DEFAULT,?,?,?,?)";
		Object[] params= {userId,productId,productNum,type};	
		return super.excuteUpdate(sql, params);	
	}

	@Override
	/**
	 * 获得收藏列表信息！
	 * @return
	 */
	public List<EasybuyCollect> select() {
		//查询Sql语句
		String sql="SELECT id,userId,productId,productNum,type FROM easybuy_collect";
		//创建List对象
		List<EasybuyCollect> list = new ArrayList<EasybuyCollect>();
		// 创建字符集
		ResultSet rs = null;
		try {
			rs = super.excuteQuery(sql, null);
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int userId = rs.getInt("userId");
					int productId = rs.getInt("productId");
					int productNum = rs.getInt("productNum");
					int type = rs.getInt("type");
					list.add(new EasybuyCollect(id, userId, productId, productNum, type));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, null, rs);
		}

		return list;
	}

	@Override
	/**
	 * 查询商品信息！
	 * @param userId
	 * @param productId
	 * @return
	 */
	public EasybuyCollect selectId(int userId, int productId) {
		//查询Sql语句
		String sql="SELECT * FROM  easybuy_collect WHERE userId=? AND productId=?";
		//创建对象
		EasybuyCollect easybuyCollect= null;
		Object[] parms= {userId,productId};
		// 创建字符集！
		ResultSet rs = null;
		try {
			rs = super.excuteQuery(sql, parms);
			if (rs != null) {		
				while (rs.next()) {
					int id = rs.getInt("id");
					int userId1 = rs.getInt("userId");
					int productId1 = rs.getInt("productId");
					int productNum = rs.getInt("productNum");
					int type = rs.getInt("type");
					easybuyCollect=new EasybuyCollect(id, userId1, productId1, productNum, type);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, null, null);
		}

		return easybuyCollect;
	}

	@Override
	/**
	 * 根据用户查询商品信息！
	 * @param userId
	 * @return
	 */
	public List<EasybuyCollect> selectByUserId(int userId) {
		//查询Sql语句
		String sql="SELECT id,userId,productId,productNum,`type` FROM easybuy_collect WHERE userId=?";
		List<EasybuyCollect> list = new ArrayList<EasybuyCollect>();
		Object[] parms= {userId};
		ResultSet rs = null;
		try {
			rs = super.excuteQuery(sql, parms);
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int userId1 = rs.getInt("userId");
					int productId = rs.getInt("productId");
					int productNum = rs.getInt("productNum");
					int type = rs.getInt("type");
					list.add(new EasybuyCollect(id, userId1, productId, productNum, type));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, null, null);
		}

		return list;
	}

	@Override
	/**
	 * 删除收藏
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int delCollect(EasybuyCollect easybuyCollect) {
		//删除Sql语句
		String sql="DELETE  FROM easybuy_collect WHERE id=?";
		Object[] parms= {easybuyCollect.getId()};
		int result=-1;
		try {
			result = super.excuteUpdate(sql, parms);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回result
		return result;
	}

}
