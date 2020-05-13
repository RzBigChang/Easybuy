package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;

import org.apache.ibatis.session.SqlSession;

import com.dao.BaseDao;
import com.dao.MyBatisUtil;
import com.dao.ProductDao;
import com.entity.EasybuyCollect;
import com.entity.EasybuyOrderDetail;
import com.entity.EasybuyProduct;
import com.utils.Pager;

public class EasybuyProductDaoImpl extends BaseDao implements ProductDao {

	public EasybuyProductDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * 获取所有商品信息！
	 * 
	 */
	public List<EasybuyProduct> getEasybuyProductList() {
				// 创建集合！
				List<EasybuyProduct> list = new ArrayList<EasybuyProduct>();
				// 创建字符集！
				ResultSet rs = null;
				// 创建数组为占位符赋值！
				Object[] parms = null;
				try {
					// SQL语句！
					String sql = "SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE isdelete=0";
					rs = super.excuteQuery(sql, parms);
					if (rs != null) {
						//循环
						while (rs.next()) {
							int id = rs.getInt("id");
							String name = rs.getString("name");
							String description = rs.getString("description");
							float price = rs.getFloat("price");
							int stock = rs.getInt("stock");
							int categoryLevel1 = rs.getInt("categoryLevel1");
							int categoryLevel2 = rs.getInt("categoryLevel2");
							int categoryLevel3 = rs.getInt("categoryLevel3");
							String fileName = rs.getString("fileName");
							int isDelete = rs.getInt("isDelete");
							//存值
							list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
									categoryLevel3, fileName, isDelete));
						}
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(conn, pstmt, rs);
				}
				//返回字符串
				return list;
	}

	@Override
	/**
	 * 获取一级分类总记录数！
	 * 
	 */
	public int getProductRowCount(int categoryId) {
				// 创建字符集！
				ResultSet rs = null;
				//返回值
				int result = -1;
				try {
					//Sql语句
					String sql = "SELECT COUNT(1) FROM easybuy_product WHERE categoryLevel1=? AND isdelete=0";					
					Object[] parms = { categoryId };
					rs = super.excuteQuery(sql, parms);
					if (rs != null) {
						while (rs.next()) {
							result = rs.getInt(1);
						}
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					closeAll(conn, pstmt, rs);
				}
				//返回值
				return result;
	}

	@Override
	/**
	 * 获取二级分类总记录数！
	 *
	 */
	public int getProductRowCount2(int categoryId) {
				// 创建字符集！
				ResultSet rs = null;
				//返回值
				int result = -1;
				try {
					// SQL语句
					String sql = "SELECT COUNT(1) FROM easybuy_product WHERE categoryLevel2=? AND isdelete=0";
					// 创建数组为占位符赋值！
					Object[] parms = { categoryId };
					// 调用方法！
					rs = super.excuteQuery(sql, parms);
					if (rs != null) {
						while (rs.next()) {
							result = rs.getInt(1);
						}
					}
				} catch (Exception e) {
					// 异常捕获！
				e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(conn, pstmt, rs);
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
	public int getProductRowCount3(int categoryId) {
		// 创建字符集！
		ResultSet rs = null;
		//创建返回值
		int result = -1;
		try {
			// SQL语句！
			String sql = "SELECT COUNT(1) FROM easybuy_product WHERE categoryLevel3=?  AND isdelete=0";
			// 创建数组为属性赋值！
			Object[] parms = { categoryId };
			// 调用方法
			rs = super.excuteQuery(sql, parms);
			if (rs != null) {
				// 循环！
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			// 异常捕获！
			e.printStackTrace();
		} finally {
			// 释放资源！
			closeAll(conn, pstmt, rs);
		}
		return result;
	}

	@Override
	/**
	 * 获取一级分类所有商品信息！
	 * 
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId(int categoryId, Pager pager) {
				// 创建实例化集合
				List<EasybuyProduct> list = new ArrayList<EasybuyProduct>();
				// 创建字符集！
				ResultSet rs = null;
				try {
					// SQL语句！
					String sql = " SELECT `id`,`name`,`description`,`price`,`stock`,`categoryLevel1`,`categoryLevel2`,`categoryLevel3`,`fileName`,`isDelete` FROM easybuy_product WHERE categoryLevel1=?  AND isdelete=0 LIMIT ?,?";
					// 创建数组！
					Object[] parms = { categoryId, (pager.getCurrentPage() - 1) * pager.getRowPerPage(),
							pager.getRowPerPage() };
					//调用方法
					rs = super.excuteQuery(sql, parms);
					// 实例化集合！
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String description = rs.getString("description");
						float price = rs.getFloat("price");
						int stock = rs.getInt("stock");
						int categoryLevel1 = rs.getInt("categoryLevel1");
						int categoryLevel2 = rs.getInt("categoryLevel2");
						int categoryLevel3 = rs.getInt("categoryLevel3");
						String fileName = rs.getString("fileName");
						int isDelete = rs.getInt("isDelete");
						//存值
						list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
								categoryLevel3, fileName, isDelete));
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(conn, pstmt, rs);
				}
				//返回List集合
				return list;
	}

	@Override
	/**
	 * 获取二级分类所有商品信息！
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId2(int categoryId, Pager pager) {
				// 创建实例化集合
				List<EasybuyProduct> list = new ArrayList<EasybuyProduct>();
				// 创建字符集！
				ResultSet rs = null;
				try {
					// SQL语句1
					String sql = " SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE categoryLevel2=?  AND isdelete=0 LIMIT ?,?";
					// 创建数组为占位符赋值！
					Object[] parms = { categoryId, (pager.getCurrentPage() - 1) * pager.getRowPerPage(),
							pager.getRowPerPage() };
					rs = super.excuteQuery(sql, parms);
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String description = rs.getString("description");
						float price = rs.getFloat("price");
						int stock = rs.getInt("stock");
						int categoryLevel1 = rs.getInt("categoryLevel1");
						int categoryLevel2 = rs.getInt("categoryLevel2");
						int categoryLevel3 = rs.getInt("categoryLevel3");
						String fileName = rs.getString("fileName");
						int isDelete = rs.getInt("isDelete");
						list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
								categoryLevel3, fileName, isDelete));

					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(conn, pstmt, rs);
				}
				//返回list集合
				return list;
	}

	@Override
	/**
	 * 获取三级分类所有商品信息！
	 * 
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryId3(int categoryId, Pager pager) {
				// 创建 实例化集合！
				List<EasybuyProduct> 	list = new ArrayList<EasybuyProduct>();
				// SQL语句！
				String sql = " SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE categoryLevel3=? AND isdelete=0 LIMIT ?,?";
				// 创建字符集！
				ResultSet rs = null;
				try {
					// 创建数组为占位符赋值！
					Object[] parms = { categoryId, (pager.getCurrentPage() - 1) * pager.getRowPerPage(),
							pager.getRowPerPage() };
					//调用方法
					rs = super.excuteQuery(sql, parms);
					//循环
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String description = rs.getString("description");
						float price = rs.getFloat("price");
						int stock = rs.getInt("stock");
						int categoryLevel1 = rs.getInt("categoryLevel1");
						int categoryLevel2 = rs.getInt("categoryLevel2");
						int categoryLevel3 = rs.getInt("categoryLevel3");
						String fileName = rs.getString("fileName");
						int isDelete = rs.getInt("isDelete");
						//存值
						list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
								categoryLevel3, fileName, isDelete));
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
				closeAll(conn, pstmt, rs);
				}
				return list;
	}
	/**
	 * 区间查询
	 * */
	public List<EasybuyProduct> getEasybuyProductListByPrice(Float price1, Float price2) {
		List<EasybuyProduct>list = new ArrayList<EasybuyProduct>();
		//sql语句
		String sql;
		ResultSet rs=null;
		try {
			// 创建数组为占位符赋值
			if((int)(price1*10)<=(int)(price2*10)){
				sql="SELECT * FROM easybuy_product WHERE price>=? and price<=?" ;
				Object[] parms = {price1,price2 };
				rs = super.excuteQuery(sql,parms);
			}else if((int)(price1*10)>=(int)(price2*10)){
				sql="SELECT * FROM easybuy_product WHERE price<=? and price>=?" ;
				Object[] parms = {price1,price2 };
				rs = super.excuteQuery(sql,parms);
			};
			//循环
			while (rs.next()) {
				int id= rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int stock = rs.getInt("stock");
				int categoryLevel1 = rs.getInt("categoryLevel1");
				int categoryLevel2 = rs.getInt("categoryLevel2");
				int categoryLevel3 = rs.getInt("categoryLevel3");
				String fileName = rs.getString("fileName");
				int isDelete = rs.getInt("isDelete");
				//存值
				list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
						categoryLevel3, fileName, isDelete));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	@Override
	/**
	 * 根据商品编号查询商品信息！
	 * 
	 */
	public EasybuyProduct getById(int tid) {
				// 创建对象！
				EasybuyProduct easybuyProduct = null;
				// SQL语句！
				String sql = "SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE id=?  AND isdelete=0";
				// 创建字符集！
				ResultSet rs = null;
				try {
					Object[] parms = { tid };
					rs = super.excuteQuery(sql, parms);
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String description = rs.getString("description");
						float price = rs.getFloat("price");
						int stock = rs.getInt("stock");
						int categoryLevel1 = rs.getInt("categoryLevel1");
						int categoryLevel2 = rs.getInt("categoryLevel2");
						int categoryLevel3 = rs.getInt("categoryLevel3");
						String fileName = rs.getString("fileName");
						int isDelete = rs.getInt("isDelete");
						//存值
						easybuyProduct = new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
								categoryLevel3, fileName, isDelete);
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(null, null, rs);
				}
				//返回对象
				return easybuyProduct;
	}

	@Override
	/**
	 * 修改商品库存信息！
	 * @param id
	 * @param quantity
	 */
	public void updateStock(Integer id, Integer quantity) {
		try {
			// 创建数组为占位符赋值！
			Object[] params = new Object[] { quantity, id };
			// SQL语句！
			String sql = " update easybuy_product set stock=stock-? where id=?  AND isdelete=0";
			// 调用方法！
			super.excuteUpdate(sql, params);
		} catch (Exception e) {
			// 异常捕获！
			e.printStackTrace();
		} finally {
			// 释放资源！
			closeAll(null, null, null);
		}
	}

	@Override
	/**
	 * 查询用户收藏列表！
	 * @param list
	 * @return
	 */
	public EasybuyProduct getEasybuyProductListByUser(int id) {
		// 创建集合！
		SqlSession sqlSession=null;
		sqlSession=MyBatisUtil.createSqlSession();
				List<EasybuyProduct> listProduct = new ArrayList<EasybuyProduct>();
				// SQL语句！
				String sql = " SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE id=?  AND isdelete=0";
				// 创建数组为属性赋值！
				Object[] parms = null;
				// 创建字符集！
				ResultSet rs = null;
				listProduct=null;
//				try {
//					for (EasybuyCollect easybuyCollect : list) {
//						easybuyCollect.getProductId();
//						EasybuyProduct cv=sqlSession.getMapper(ProductDao.class).getEasybuyProductListByUser(easybuyCollect.getProductId());
//								
//						parms = new Object[] { easybuyCollect.getProductId() };
//						rs = super.excuteQuery(sql, parms);
//						if (rs != null) {
//							while (rs.next()) {
//								int id = rs.getInt("id");
//								String name = rs.getString("name");
//								String description = rs.getString("description");
//								float price = rs.getFloat("price");
//								int stock = rs.getInt("stock");
//								int categoryLevel1 = rs.getInt("categoryLevel1");
//								int categoryLevel2 = rs.getInt("categoryLevel2");
//								int categoryLevel3 = rs.getInt("categoryLevel3");
//								String fileName = rs.getString("fileName");
//								int isDelete = rs.getInt("isDelete");
//								listProduct.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1,
//										categoryLevel2, categoryLevel3, fileName, isDelete));

					
//					
//					}
//				} catch (Exception e) {
//					// 异常捕获！
//					e.printStackTrace();
//				} finally {
//					// 释放资源！
//				closeAll(null, null, rs);
//				}
				return null;
	}

	@Override
	/**
	 * 查询订单表！
	 * @param list
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByOrder(List<EasybuyOrderDetail> list) {
		// 创建集合！
				List<EasybuyProduct> listProduct = new ArrayList<EasybuyProduct>();
				// SQL语句！
				String sql = " SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE id=?  AND isdelete=0";
				// 创建数组为属性赋值！
				Object[] parms = null;
				// 创建字符集！
				ResultSet rs = null;
				try {
					for (EasybuyOrderDetail easybuyOrderDetail : list) {
						parms = new Object[] { easybuyOrderDetail.getProductId() };
						rs = super.excuteQuery(sql, parms);
						if (rs != null) {
							while (rs.next()) {
								int id = rs.getInt("id");
								String name = rs.getString("name");
								String description = rs.getString("description");
								float price = rs.getFloat("price");
								int stock = rs.getInt("stock");
								int categoryLevel1 = rs.getInt("categoryLevel1");
								int categoryLevel2 = rs.getInt("categoryLevel2");
								int categoryLevel3 = rs.getInt("categoryLevel3");
								String fileName = rs.getString("fileName");
								int isDelete = rs.getInt("isDelete");
								listProduct.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1,
										categoryLevel2, categoryLevel3, fileName, isDelete));

							}
						}
					}

				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(null, null, rs);
				}
				return listProduct;
	}

	//@Override
	/**
	 * 商品管理！
	 * @param pager
	 * @return
	 */
	/**
	 * public List<EasybuyProduct> findEasybuyProductAll(Pager pager) {
		// Sql语句！
				String sql = "SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product where isDelete=0 LIMIT ?,?";
				ResultSet rs = null;
				// 创建集合！
				List<EasybuyProduct> list = new ArrayList<EasybuyProduct>();
				try {
					// 创建数组为占位符赋值！
					Object[] parms = { (pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage() };
					rs = super.excuteQuery(sql, parms);
					// 实例化集合！
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String description = rs.getString("description");
						float price = rs.getFloat("price");
						int stock = rs.getInt("stock");
						int categoryLevel1 = rs.getInt("categoryLevel1");
						int categoryLevel2 = rs.getInt("categoryLevel2");
						int categoryLevel3 = rs.getInt("categoryLevel3");
						String fileName = rs.getString("fileName");
						int isDelete = rs.getInt("isDelete");
						list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
								categoryLevel3, fileName, isDelete));

					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(null, null, rs);
				}
				return list;
	}
	 * */

	@Override
	/**
	 * 获取总记录数！
	 * @return
	 */
	public int findTotalCount() {
		int count = 0;
		String sql = "SELECT count(1) FROM easybuy_product WHERE isDelete=0";
		rs=super.MYexecuteQuery(sql, null);
		if(rs!=null) {
			try {
				while (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeAll(conn, pstmt, rs);
			}
		}
		return count;
	}

	@Override
	/**
	 * 根据ID删除指定商品信息！
	 * @param id
	 * @return
	 */
	public int deleteEasybuyProductById(int id) {
		// SQL语句！
				String sql = "UPDATE easybuy_product SET `isDelete` = 1 WHERE id = ?";
				// 创建数组为占位符赋值！
				Object[] parms = { id };
				// 声明变量接受受影响行数！
				int temp = -1;
				try {
					// 调用方法！
					temp = super.excuteUpdate(sql, parms);
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				}
				return temp;
	}

	@Override
	/**
	 * 商品上架！
	 * @param easybuyProduct
	 * @return
	 */
	public int insertEasybuyProduct(EasybuyProduct easybuyProduct) {
		// SQL语句！
				String sql = null;
				int temp = -1;
				Object[] parms = null;
				try {
					if (easybuyProduct.getId() != 0) {
						// 修改商品！
						if (easybuyProduct.getFileName() == null) {
							// 创建数组为占位符赋值！
							sql = "UPDATE easybuy_product SET `categoryLevel1` = ?,`categoryLevel2` = ?,`categoryLevel3` = ?,`name` = ?,`price`=?,`stock` =? WHERE id=?";
							parms = new Object[] { easybuyProduct.getCategoryLevel1(), easybuyProduct.getCategoryLevel2(),
									easybuyProduct.getCategoryLevel3(), easybuyProduct.getName(), easybuyProduct.getPrice(),
									easybuyProduct.getStock(), easybuyProduct.getId() };
						} else {
							// 创建数组为占位符赋值！
							sql = "UPDATE easybuy_product SET `categoryLevel1` = ?,`categoryLevel2` = ?,`categoryLevel3` = ?,`name` = ?,`fileName`=?,`price`=?,`stock` =? WHERE id=?";
							parms = new Object[] { easybuyProduct.getCategoryLevel1(), easybuyProduct.getCategoryLevel2(),
									easybuyProduct.getCategoryLevel3(), easybuyProduct.getName(), easybuyProduct.getFileName(),
									easybuyProduct.getPrice(), easybuyProduct.getStock(), easybuyProduct.getId() };
						}

					} else {
						// 新增商品！
						sql = "INSERT INTO `easybuy_product`(`categoryLevel1`,`categoryLevel2`,`categoryLevel3`,`name`,`fileName`,`price`,`stock`,`description`,`isDelete`)VALUES (?,?,?,?,?,?,?,?,0)";
						parms = new Object[] { easybuyProduct.getCategoryLevel1(), easybuyProduct.getCategoryLevel2(),
								easybuyProduct.getCategoryLevel3(), easybuyProduct.getName(), easybuyProduct.getFileName(),
								easybuyProduct.getPrice(), easybuyProduct.getStock(), easybuyProduct.getDescription() };
					}
					// 调用三层！
					temp = super.excuteUpdate(sql, parms);
				} catch (Exception e) {
					// 捕获异常！
					System.err.println(e.getMessage());
				}
				return temp;
	}

	@Override
	/**
	 * 根据Id查询对应的商品信息！
	 * @param id
	 * @return
	 */
	public EasybuyProduct findEasybuyProductById(int id) {
		// SQL语句！
				String sql = "SELECT `id`,`name`,`description`,`price`,`stock`,`categoryLevel1`,`categoryLevel2`,`categoryLevel3`,`fileName`,`isDelete` FROM easybuy_product WHERE id = ?";
				// 创建数组为占位符赋值！
				Object[] parms = { id };
				// 声明字符集对象！
				ResultSet set = null;
				// 创建对象！
				EasybuyProduct easybuyProduct = null;
				try {
					set = super.excuteQuery(sql, parms);
					if (set != null) {
						// 循环遍历！
						while (set.next()) {
							// 实例化！
							easybuyProduct = new EasybuyProduct();
							easybuyProduct.setId(set.getInt("id"));
							easybuyProduct.setName(set.getString("name"));
							easybuyProduct.setDescription(set.getString("description"));
							easybuyProduct.setPrice(set.getFloat("price"));
							easybuyProduct.setStock(set.getInt("stock"));
							easybuyProduct.setCategoryLevel1(set.getInt("categoryLevel1"));
							easybuyProduct.setCategoryLevel2(set.getInt("categoryLevel2"));
							easybuyProduct.setCategoryLevel3(set.getInt("categoryLevel3"));
							easybuyProduct.setFileName(set.getString("fileName"));
							easybuyProduct.setIsDelete(set.getInt("isDelete"));
						}
					}
				} catch (Exception e) {
					// 捕获异常！
					System.err.println(e.getMessage());
				} finally {
					// 释放资源！
					closeAll(null, null, set);
				}
				return easybuyProduct;
	}

	@Override
	/**
	 * 获取模糊查询继续数
	 * @param categoryName
	 * @return
	 */
	public int getProductRowCountByName(String categoryName) {
		// 创建字符集！
				ResultSet rs = null;
				int result = -1;
				try {
					String sql = "SELECT COUNT(1) FROM easybuy_product WHERE `name` LIKE ? AND isdelete=0";
					Object[] parms = { "%"+categoryName+"%" };
					rs = super.excuteQuery(sql, parms);
					if (rs != null) {
						while (rs.next()) {
							result = rs.getInt(1);
						}
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					closeAll(null, null, rs);
				}
				return result;
	}

	@Override
	/**
	 * 模糊查询
	 * @param categoryId
	 * @param pager
	 * @return
	 */
	public List<EasybuyProduct> getEasybuyProductListByCategoryName(String categoryName, Pager pager) {
		// 创建集合！
		List<EasybuyProduct> list =new ArrayList<EasybuyProduct>();
		// SQL语句！
		String sql = " SELECT id,name,description,price,stock,categoryLevel1,categoryLevel2,categoryLevel3,fileName,isDelete FROM easybuy_product WHERE `name` LIKE ?  AND isdelete=0 LIMIT ?,?";
		// 创建字符集！
		ResultSet rs = null;
		try {
			// 创建数组！
			Object[] parms = { "%"+categoryName+"%", (pager.getCurrentPage() - 1) * pager.getRowPerPage(),
					pager.getRowPerPage() };
			rs = super.excuteQuery(sql, parms);
			// 实例化集合！
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int stock = rs.getInt("stock");
				int categoryLevel1 = rs.getInt("categoryLevel1");
				int categoryLevel2 = rs.getInt("categoryLevel2");
				int categoryLevel3 = rs.getInt("categoryLevel3");
				String fileName = rs.getString("fileName");
				int isDelete = rs.getInt("isDelete");
				list.add(new EasybuyProduct(id, name, description, price, stock, categoryLevel1, categoryLevel2,
						categoryLevel3, fileName, isDelete));
			}
		} catch (Exception e) {
			// 异常捕获！
			e.printStackTrace();
		} finally {
			// 释放资源！
			closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	/**
	 * 修改商品信息
	 * */
	public int updateByProductId(EasybuyProduct ep) {
		int rst = 0;
		String sql = "UPDATE easybuy_product SET name=?,description=?,price=?,stock=?,categoryLevel1=?,categoryLevel2=?,categoryLevel3=?,fileName=? WHERE id=?";
		Object[]params = {
				ep.getName(),
				ep.getDescription(),
				ep.getPrice(),
				ep.getStock(),
				ep.getCategoryLevel1(),
				ep.getCategoryLevel2(),
				ep.getCategoryLevel3(),
				ep.getFileName(),
				ep.getId()
		};
		rst = super.MYexecuteUpdate(sql, params);
		return rst;
	}

	@Override
	/**
	 * 
	 * 区间查询
	 * */
	public int getProductRowCount4(Float price1,Float price2) {
		// 创建字符集！
				ResultSet rs = null;
				//创建返回值
				int result = -1;
				String sql;
				try {
					// SQL语句！
					if((int)(price1*10)<=(int)(price2*10)) {
					sql = "SELECT COUNT(1) FROM easybuy_product WHERE price>=? and price<=?";
					// 创建数组为属性赋值！
					Object[] parms = { price1,price2 };
					// 调用方法
					rs = super.excuteQuery(sql, parms);
					}else if((int)(price1*10)>=(int)(price2*10)) {
						sql = "SELECT COUNT(1) FROM easybuy_product WHERE price<=? and price>=?";
						// 创建数组为属性赋值！
						Object[] parms = { price1,price2 };
						// 调用方法
						rs = super.excuteQuery(sql, parms);
					}else if((int)(price1*10)==0&&(int)(price2*10)!=0){
						sql = "SELECT COUNT(1) FROM easybuy_product WHERE price<=?";
						// 创建数组为属性赋值！
						Object[] parms = { price1,price2 };
						// 调
						rs = super.excuteQuery(sql, parms);
					}else if((int)(price1*10)==0&&(int)(price2*10)!=0){
						sql = "SELECT COUNT(1) FROM easybuy_product WHERE price<=?";
						// 创建数组为属性赋值！
						Object[] parms = { price1,price2 };
						// 调用方法
						rs = super.excuteQuery(sql, parms);
					};
					if (rs != null) {
						// 循环！
						while (rs.next()) {
							result = rs.getInt(1);
						}
					}
				} catch (Exception e) {
					// 异常捕获！
					e.printStackTrace();
				} finally {
					// 释放资源！
					closeAll(conn, pstmt, rs);
				}
				return result;
	}

	@Override
	public List<EasybuyProduct> findEasybuyProductAll(int currentPage, int rowPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update1(EasybuyProduct easybuyProduct) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update2(EasybuyProduct easybuyProduct) {
		// TODO Auto-generated method stub
		return 0;
	}

}
