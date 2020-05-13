package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.utils.Pager;
/**
 * 商品信息数据访问层！
 * @author Administrator
 *
 */
public interface ProductDao {
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
	 * 获取一级分类所有商品信息！
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(@Param("categoryLevel1")int categoryId, Pager pager);
	/**
	 * 获取二级分类所有商品信息！
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
	 * 根据商品编号查询商品信息！
	 * @param id
	 * @return
	 */
	public EasybuyProduct getById(@Param("id")int id);
	/**
	 * 修改商品库存信息！
	 * @param id
	 * @param quantity
	 * 
	 */
	public void updateStock(@Param("id")Integer id,@Param("stock") Integer stock);
	/**
	 * 查询用户收藏列表！
	 * @param list
	 * @return
	 * 未写
	 */
	public EasybuyProduct getEasybuyProductListByUser(int id);
	/**
	 * 查询订单表！
	 * @param list
	 * @return
	 * 未写
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder(List<EasybuyOrderDetail> list);
	
	/**
	 * 商品管理！
	 * @param pager
	 * @return
	 */
	List<EasybuyProduct> findEasybuyProductAll(@Param("currentPage")int currentPage,@Param("rowPerPage")int rowPerPage);
	
	/**
	 * 获取总记录数！
	 * @return
	 */
	public int findTotalCount();
	
	/**
	 * 根据ID删除指定商品信息！
	 * @param id
	 * @return
	 */
	int deleteEasybuyProductById(@Param("id")int id);
	
	/**
	 * 商品上架！
	 * @param easybuyProduct
	 * @return
	 * 未写
	 */
	int insertEasybuyProduct(EasybuyProduct easybuyProduct);
	int update1(EasybuyProduct easybuyProduct);
	int update2(EasybuyProduct easybuyProduct);
	
	/**
	 * 根据Id查询对应的商品信息！
	 * @param id
	 * @return
	 */
	EasybuyProduct findEasybuyProductById(@Param("id")int id);
	
	/**
	 * 获取模糊查询继续数
	 * @param categoryName
	 * @return
	 */
	public int getProductRowCountByName(@Param("name")String categoryName);
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
	 * 区间查询总记录数
	 * */
	public int getProductRowCount4(@Param("price1")Float price1,@Param("price2")Float price2);
}
