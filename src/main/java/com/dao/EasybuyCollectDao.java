package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;

/**
 * 收藏列表数据访问层！！
 * 
 * @author Administrator
 *
 */
public interface EasybuyCollectDao {
	/**
	 * 收藏商品！
	 * @param userId
	 * @param productId
	 * @param productNum
	 * @param type
	 * @return
	 */
	int addCollect(@Param("userId")int userId, @Param("productId")int productId, @Param("productNum")int productNum, @Param("type")int type);
	/**
	 * 获得收藏列表信息！
	 * @return
	 */
	List<EasybuyCollect> select();
	/**
	 * 查询商品信息！
	 * @param userId
	 * @param productId
	 * @return
	 */
	EasybuyCollect selectId(@Param("userId")int userId, @Param("productId")int productId);
	/**
	 * 根据用户查询商品信息！
	 * @param userId
	 * @return
	 */
	List<EasybuyCollect> selectByUserId(@Param("userId")int userId);
	/**
	 * 删除收藏
	 * @param userId
	 * @param productId
	 * @return
	 */
	int delCollect(EasybuyCollect easybuyCollect);
	
}
