package com.service.product.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MyBatisUtil;
import com.dao.ProductDao;
import com.dao.impl.EasybuyProductDaoImpl;
import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.service.product.EasybuyProductService;
import com.utils.DataBaseUtil;
import com.utils.Pager;
@Service
public class EasybuyProductServiceImpl implements EasybuyProductService {
	@Autowired
	private ProductDao productMapper;
	public List<EasybuyProduct> getEasybuyProductList() {
		// 获得连接！
		//java.sql.Connection conn = null;
		List<EasybuyProduct> list = null;
		try {
			// 获得连接对象！
			list = productMapper.getEasybuyProductList();
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	/**
	 * 获取一级分类总记录数！
	 * 
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount(@Param("categoryLevel1")int categoryId) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
		
			result = productMapper.getProductRowCount(categoryId);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	/**
	 * 获取模糊查询继续数
	 * @param categoryName
	 * @return
	 */
	public int getProductRowCountByName(String categoryName) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
		
			//result接受方法返回值
			result = productMapper.getProductRowCountByName(categoryName);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	/**
	 * 获取二级分类总记录数！
	 * 
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount2(@Param("categoryLevel2")int categoryId) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
			// 获得连接！
		
			//result接受方法返回值
			result = productMapper.getProductRowCount2(categoryId);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	/**
	 * 获取三级分类总记录数！
	 * 
	 * @param categoryId
	 * @return
	 */
	public int getProductRowCount3(@Param("categoryLevel3")int categoryId) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
			
			//result接受方法返回值
			result = productMapper.getProductRowCount3(categoryId);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	/**
	 * 获取一级分类所有商品信息！
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(int categoryId, Pager pager) {
		// 获得连接！
		java.sql.Connection conn = null;
		List<EasybuyProduct> list = null;
		try {
			// 获得连接对象！
		
			//result接受方法返回值
			list =productMapper.getEasybuyProductListByCategoryId(categoryId, pager);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
			
		}
//					List<EasybuyProduct>list=dao.getEasybuyProductList();
		return list;
	}

	@Override
	/**
	 * 获得商品二级分类所有商品信息！
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId2(int categoryId, Pager pager) {
		// 获得连接！
		java.sql.Connection conn = null;
		List<EasybuyProduct> list = null;
		try {
			// 获得连接对象！
			
			//result接受方法返回值
			list = productMapper.getEasybuyProductListByCategoryId2(categoryId, pager);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
			
		}
//					List<EasybuyProduct>list=dao.getEasybuyProductList();
		return list;
	}

	@Override
	/**
	 * 获得商品三级分类！
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId3(int categoryId, Pager pager) {
		// 获得连接！
		java.sql.Connection conn = null;
		List<EasybuyProduct> list = null;
		try {
			// 获得连接对象！
		
			//result接受方法返回值
			list = productMapper.getEasybuyProductListByCategoryId3(categoryId, pager);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
			
		}
//					List<EasybuyProduct>list=dao.getEasybuyProductList();
		return list;
	}

	/**
	 * 
	 * 根据id获取商品信息
	 * */
	@Override
	public EasybuyProduct findById(int tid) {
		EasybuyProduct product = null;
		try {
			product = productMapper.findEasybuyProductById(tid);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return product;
	}

	@Override
	/**
	 * 查询用户收藏夹！
	 * 
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByUser(List<EasybuyCollect> list) {
		List<EasybuyProduct>listEasybuyProduct=new ArrayList<EasybuyProduct>();
		try {
			for (EasybuyCollect easybuyCollect : list) {
				EasybuyProduct cv=productMapper.getEasybuyProductListByUser(easybuyCollect.getProductId());
				listEasybuyProduct.add(cv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listEasybuyProduct;
	}

	@Override
	/**
	 * 订单表！
	 * 
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder(List<EasybuyOrderDetail> list) {
		java.sql.Connection conn = null;
		List<EasybuyProduct> listProduct = null;
		try {
			// 获得连接！
			conn = DataBaseUtil.getConnection();
		
			listProduct =productMapper.getEasybuyProductListByOrder(list);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
		
		}
		return listProduct;
	}

	@Override
	/**
	 * 商品管理！
	 * 
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductAll(Pager pager) {
		java.sql.Connection conn = null;
		List<EasybuyProduct> list = null;
		int currentPage=(pager.getCurrentPage()-1)*pager.getRowPerPage();
		int rowPerPage=pager.getRowPerPage();
		try {
			// 获得连接！
			
			//result接受方法返回值
			list = productMapper.findEasybuyProductAll(currentPage, rowPerPage);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
		}
		return list;
	}

	@Override
	/**
	 * 获取总记录数！
	 */
	public int getTotalCount() {
		java.sql.Connection conn = null;
		int result = -1;
		try {
			// 获得连接！
			//result接受方法返回值
			result = productMapper.findTotalCount();
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
		}
		return result;
	}

	@Override
	/**
	 * 根据ID删除指定商品信息！
	 */
	public int delEasybuyProductById(int id) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
			// 获得连接！
	
			//result接受方法返回值
			result = productMapper.deleteEasybuyProductById(id);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
		}
		return result;
	}

	@Override
	/**
	 * 商品上架/修改商品业务！
	 * 
	 * @param easybuyProduct
	 * @return
	 */
	public int addEasybuyProduct(EasybuyProduct easybuyProduct) {
		// 获得连接对象！
				int result = -1;
				try {
					if (easybuyProduct.getId() != 0) {
						// 修改商品！
						if (easybuyProduct.getFileName() == null) {
							// 创建数组为占位符赋值！
							result =productMapper.update1(easybuyProduct);
						} else {
							// 创建数组为占位符赋值！
							result = productMapper.update2(easybuyProduct);
						}
					} else {
						// 新增商品！
						result = productMapper.insertEasybuyProduct(easybuyProduct);
					}
				} catch (Exception e) {
					// 捕获异常！
					e.printStackTrace();
				} 
				return result;
	}

	@Override
	/**
	 * 根据Id查询对应的商品信息！
	 * 
	 * @param id
	 * @return
	 */
	public EasybuyProduct getEasybuyProductById(int id) {
		// 连接对象！
		java.sql.Connection conn = null;
				// 创建对象！
				EasybuyProduct easybuyProductAll = null;
				try {
					// 获得连接！
					//result接受方法返回值
					easybuyProductAll = productMapper.findEasybuyProductById(id);
				} catch (Exception e) {
					// 捕获异常！
					e.printStackTrace();
				} finally {
					// 释放资源！
					DataBaseUtil.closeAll(null, null, conn);
				}
				return easybuyProductAll;
	}

	@Override
	/**
	 * 模糊查询
	 * */
	public List<EasybuyProduct> getEasybuyProductListByCategoryName(String categoryName, Pager pager) {
		java.sql.Connection conn = null;
		List<EasybuyProduct> list = null;
		try {
			// 获得连接对象！
		
			//result接受方法返回值
			list = productMapper.getEasybuyProductListByCategoryName(categoryName, pager);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
		}
		return list;
	}

	@Override
	/**
	 * 修改
	 * */
	public int updateByProductId(EasybuyProduct ep) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
			//result接受方法返回值
			result = productMapper.updateByProductId(ep);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	/**
	 * 区间查询
	 * */
	public List<EasybuyProduct> getEasybuyProductListByPrice(Float price1,Float price2) {
		// 获得连接！
				java.sql.Connection conn = null;
				List<EasybuyProduct> list = null;				
				
				try {
					// 获得连接对象！
				
					//result接受方法返回值
					list = productMapper.getEasybuyProductListByPrice(price1, price2);
				} catch (Exception e) {
					// 捕获异常！
					e.printStackTrace();
				} finally {
					// 释放资源！
					DataBaseUtil.closeAll(null, null, conn);
				}
				return list;
	}

	@Override
	/**
	 * 区间查询继续数
	 * 
	 * */
	public int getProductRowCount4(Float price1, Float price2) {
		java.sql.Connection conn = null;
		int result = -1;
		try {
			// 获得连接！
	
			//result接受方法返回值
			result = productMapper.getProductRowCount4(price1, price2);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		} finally {
			// 释放资源！
			DataBaseUtil.closeAll(null, null, conn);
		}
		return result;
	}
}
