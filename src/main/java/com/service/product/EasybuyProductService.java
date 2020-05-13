package com.service.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.utils.Pager;
/**
 * 商品信息业务逻辑层！
 * @author Administrator
 *
 */
public interface EasybuyProductService {
	/**
	 * 获取所有商品信息！
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductList();
	/**
	 * 获取一级分类总记录数！
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount(@Param("categoryLevel1")int categoryId);
	
	/**
	 * 获取模糊查询继续数
	 * @param categoryName
	 * @return
	 */
	public int getProductRowCountByName(@Param("name")String categoryName);
	/**
	 * 获取二级分类总记录数！
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount2(@Param("categoryLevel2")int categoryId);
	/**
	 * 获取三级分类总记录数！
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount3(@Param("categoryLevel3")int categoryId);
	/**
	 *获取一级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(@Param("categoryLevel1")int categoryId, Pager pager);
	/**
	 * 获得商品二级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId2(@Param("categoryLevel2")int categoryId, Pager pager);
	/**
	 * 获取三级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId3(@Param("categoryLevel3")int categoryId, Pager pager);
	/**
	 * 根据ID获取信息！
	 * @param tid
	 * @return
	 */
	public EasybuyProduct findById(@Param("id")int tid);
	/**
	 * 查询用户收藏夹！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByUser(List<EasybuyCollect> list);
	/**
	 * 订单表！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder(List<EasybuyOrderDetail> list);
	
	/**
	 * 商品管理！
	 * @param pager
	 * @return
	 */
	List<EasybuyProduct> getEasybuyProductAll(Pager pager);
	
	/**
	 * 获取总记录数！
	 * @return
	 */
	public int getTotalCount();
	
	/**
	 * 根据ID删除指定商品信息！
	 * @param id
	 * @return
	 */
	int delEasybuyProductById(int id);
	
	/**
	 * 商品上架/修改商品业务！
	 *上架不归我管！
	 * @param easybuyProduct
	 * @return
	 */
	public int addEasybuyProduct(EasybuyProduct easybuyProduct);
	
	/**
	 * 根据Id查询对应的商品信息！
	 * @param id
	 * @return
	 */
	EasybuyProduct getEasybuyProductById(@Param("id")int id);
	/**
	 * 模糊查询
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryName(@Param("name")String categoryName, Pager pager);
	/**
	 * 修改商品信息
	 * */
	public int updateByProductId(EasybuyProduct ep);
	/**
	 * 区间查询
	 * */
	public List<EasybuyProduct> getEasybuyProductListByPrice(@Param("price1")Float price1,@Param("price2")Float price2);
	/**
	 * 区间查询中记录数
	 * */
	public int getProductRowCount4(@Param("price1")Float price1,@Param("price2")Float price2);
	
}
