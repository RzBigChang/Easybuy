package com.service.order.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

import com.dao.EasybuyCollectDao;
import com.dao.MyBatisUtil;
import com.dao.ProductDao;
import com.dao.impl.EasybuyCollectDaoImpl;
import com.entity.EasybuyCollect;
import com.service.order.CartService;
import com.utils.ShoppingCart;
import com.utils.DataBaseUtil;
import com.utils.EmptyUtils;
import com.utils.ShoppingCartItem;
@Service("CartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private EasybuyCollectDao collect;
	@Override
	/**
	 * 获得购物车商品所有信息！
	 * @param productId
	 * @param quantityStr
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	public ShoppingCart modifyShoppingCart(String productId, String quantityStr, ShoppingCart cart) throws Exception {
		//创建quantity变量
		Integer quantity = 0;
		//if判断
    	if (!EmptyUtils.isEmpty(quantityStr))
            quantity = Integer.parseInt(quantityStr);
        //便利购物车寻找该商品 修改其数量
        for (ShoppingCartItem item : cart.getItems()) {
            if (item.getProduct().getId()==(Integer.parseInt(productId))) {
                if (quantity == 0 || quantity < 0) {
                    cart.getItems().remove(item);
                    break;
                } else {
                    item.setQuantity(quantity);
                }
            }
        }
        //重新计算金额
        calculate(cart);
        return cart;
	}

	@Override
	  /**
     * 核算购物车的金额！
     * @param cart
     * @return
     * @throws Exception
     */
	public ShoppingCart calculate(ShoppingCart cart) throws Exception {
		//创建sum
		  double sum = 0.0;
	        for (ShoppingCartItem item : cart.getItems()) {
	            sum = sum + item.getQuantity() * item.getProduct().getPrice();
	            item.setCost(item.getQuantity() * item.getProduct().getPrice());
	        }
	        cart.setSum(sum);
	        return cart;
	}

	@Override
	/**
	 * 查询收藏表！
	 * 
	 * @return
	 */
	public List<EasybuyCollect> select() {
		List<EasybuyCollect> list=null;
		try {
			list = collect.select();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	 /**
     * 添加收藏记录！
     * @param userId
     * @param productId
     * @param productNum
     * @param type
     * @return
     */
	public int addCollect(int userId, int productId, int productNum, int type) {
		int result =0;
		try {
			result=collect.addCollect(userId, productId, productNum, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	 /**
     * 根据ID查询购物车！
     * @param userId
     * @param productId
     * @return
     */
	public EasybuyCollect selectId(int userId, int productId) {
		EasybuyCollect list=null;
		try {
			list =collect.selectId(userId, productId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	 /**
     * 查询用户的收藏夹！
     * @param userId
     * @return
     */
	public List<EasybuyCollect> selectByUserId(int userId) {
		List<EasybuyCollect> list=null;
		try {
			list =collect.selectByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
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
		int result = -1;
		try {
			result =collect.delCollect(easybuyCollect);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

}
