package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.ProductCategoryDao;
import com.entity.EasybuyProductCategory;
import com.utils.DataBaseUtil;
import com.utils.Pager;

/**
 * 商品信息数访问数据层实现类
 * @author ASUS
 *
 */
public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao {

	/**
	 * 通过构造方法的形式给连接对象赋值
	 * @param conn
	 */
	public ProductCategoryDaoImpl(Connection conn) {
		super(conn);
		// TODO 自动生成的构造函数存根
	}

	/**
	 * 查询商品分类信息
	 */
	@Override
	public List<EasybuyProductCategory> getProductCategoryList(int typeId) {
		// SQL语句！
		String sql = "SELECT id,`name`,parentId,`type` FROM easybuy_product_category WHERE `type`=? ORDER BY `name` DESC";				
		// 创建集合！
		List<EasybuyProductCategory> list = null;
		// 创建字符集！
		ResultSet rs = null;
		// 创建数组为属性赋值！
		Object[] parms = { typeId };
		try {
			rs = super.excuteQuery(sql, parms);
			if (rs != null) {
				list = new ArrayList<EasybuyProductCategory>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int parentId = rs.getInt("parentId");
					int type = rs.getInt("type");
					list.add(new EasybuyProductCategory(id, name, parentId, type));
				}
			}
		} catch (Exception e) {
			// 异常捕获！
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeAll(rs, null, null);
		}
		return list;
	}

	/**
	 * 根据id查询商品信息
	 */
	@Override
	public EasybuyProductCategory getProductCategoryById(int id) {
		EasybuyProductCategory easybuyProductCategory = null;
		String sql = "SELECT `id`,`name`,`parentId`,`type` FROM easybuy_product_category WHERE id=?";
		Object[] parms = { id };
		try {
			rs = super.excuteQuery(sql, parms);
			if(rs !=null) {
				while (rs.next()) {
					easybuyProductCategory = new EasybuyProductCategory();
					easybuyProductCategory.setId(rs.getInt(1));
					easybuyProductCategory.setName(rs.getString(2));
					easybuyProductCategory.setParentId(rs.getInt(3));
					easybuyProductCategory.setType(rs.getInt(4));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt,rs);
		}
		return easybuyProductCategory;
	}

	/**
	 * 查询所有商品信息
	 */
	//@Override
	/**public List<EasybuyProductCategory> findEasybuyProductCategoryAll(Pager pager) {
		List<EasybuyProductCategory> list = new ArrayList<EasybuyProductCategory>();
		String sql = "select * from easybuy_product_category";
		//声明商品信息对象
		EasybuyProductCategory productCategory=null;
		try {
			if(pager!=null) {
				sql="select epc1.*,epc2.name AS parentName from easybuy_product_category epc1 LEFT JOIN easybuy_product_category epc2 ON epc1.parentId=epc2.id LIMIT ?,?";
				Object[] parms= {(pager.getCurrentPage() - 1) * pager.getRowPerPage(), pager.getRowPerPage() };
				rs=super.excuteQuery(sql, parms);
				while (rs.next()) {
					productCategory = new EasybuyProductCategory();
					productCategory.setId(rs.getInt(1));
					productCategory.setName(rs.getString(2));
					productCategory.setParentId(rs.getInt(3));
					productCategory.setType(rs.getInt(4));
					productCategory.setParentName(rs.getString(5));
					list.add(productCategory);
				}
			}else {
				rs=super.excuteQuery(sql, null);
				while(rs.next()) {
					productCategory = new EasybuyProductCategory();
					productCategory.setId(rs.getInt(1));
					productCategory.setName(rs.getString(2));
					productCategory.setParentId(rs.getInt(3));
					productCategory.setType(rs.getInt(4));
					list.add(productCategory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}*/

	/**
	 * 获取总记录数
	 */
	@Override
	public int getTotalCount() {
		int result = -1;
		String sql = "select count(1) from easybuy_product_category";
		rs = super.excuteQuery(sql, null);
		if (rs != null) {
			try {
				while (rs.next()) {
					result = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeAll(conn, pstmt, rs);
			}
		}
		return result;
	}

	/**
	 * 根据Id删除商品信息
	 */
	@Override
	public int delEasybuyProductCategoryById(int id) {
		//声明变量接受受影响的行数
		int result = -1;
		try {
			String sql = "DELETE FROM easybuy_product_category WHERE id = ?";
			//创建数组为属性赋值
			Object[] parms = { id };
			result = super.excuteUpdate(sql, parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 一级分类
	 */
	@Override
	public List<EasybuyProductCategory> findProductCategoryListOne() {
		String sql="SELECT `id`,`name`,`parentId`,`type` FROM easybuy_product_category WHERE `type`=1";
		List<EasybuyProductCategory>list=new ArrayList<EasybuyProductCategory>();
		ResultSet set=null;
		EasybuyProductCategory category;
		try {
			set=super.excuteQuery(sql,null);
			if (set !=null) {
				while(set.next()) {
					category=new EasybuyProductCategory();
					category.setId(set.getInt(1));
					category.setName(set.getString(2));
					category.setParentId(set.getInt(3));
					category.setType(set.getInt(4));
					list.add(category);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeAll(set, null, null);
		}
		return list;
	}
	/**
	 * 二级分类
	 */
	@Override
	public List<EasybuyProductCategory> findProductCategoryListTwo() {
		String sql="SELECT `id`,`name`,`parentId`,`type` FROM easybuy_product_category WHERE `type`=2";
		List<EasybuyProductCategory>list=new ArrayList<EasybuyProductCategory>();
		EasybuyProductCategory category=null;
		try {
			rs=super.excuteQuery(sql, null);
			if(rs!=null) {
				while (rs.next()) {
					category=new EasybuyProductCategory();
					category.setId(rs.getInt(1));
					category.setName(rs.getString(2));
					category.setParentId(rs.getInt(3));
					category.setType(rs.getInt(4));
					list.add(category);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeAll(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	/**
	 * 三级分类
	 */
	public List<EasybuyProductCategory> findProductCategoryListThree() {
		String sql="SELECT `id`,`name`,`parentId`,`type` FROM easybuy_product_category WHERE `type`=3";
		List<EasybuyProductCategory>list=new ArrayList<EasybuyProductCategory>();
		EasybuyProductCategory category=null;
		try {
			rs=super.excuteQuery(sql,null);
			if (rs!=null) {
				while (rs.next()) {
					category=new EasybuyProductCategory();
					category.setId(rs.getInt(1));
					category.setName(rs.getString(2));
					category.setParentId(rs.getInt(3));
					category.setType(rs.getInt(4));
					list.add(category);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeAll(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	/**
	 * 根据父分类查询商品分类信息
	 */
	public List<EasybuyProductCategory> getProductCategoryListByparentId(int typeId, int parentId) {
		String sql="SELECT id,`name`,parentId,`type` FROM easybuy_product_category  WHERE `type`=? AND parentId=? ";
		List<EasybuyProductCategory> list=new ArrayList<EasybuyProductCategory>();
		Object[] parms= {typeId,parentId};
		try {
			rs=super.excuteQuery(sql,parms);
			if (rs !=null) {	
				while(rs.next()) {
					int id=rs.getInt("id");
					String name=rs.getString("name");
					int parentId1=rs.getInt("parentId");
					int type=rs.getInt("type");
					list.add(new EasybuyProductCategory(id,name,parentId1,type));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeAll(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	/**
	 * 新增商品分类
	 */
	public int insertEasybuyProductCategory(EasybuyProductCategory easybuyProductCategory) {
		String sql=null;
		Object[]parms=null;
		if(easybuyProductCategory.getId()==0) {
			sql="INSERT  INTO `easybuy_product_category`(`name`,`parentId`,`type`) VALUES(?,?,?)";
			//创建数组为属性赋值
			parms=new Object[] {easybuyProductCategory.getName(),easybuyProductCategory.getParentId(),
					easybuyProductCategory.getType()};
		}else {
			sql="UPDATE easybuy_product_category SET `name` = ?,`parentId` = ?,`type` = ? WHERE id=?";
			//创建数组为属性赋值
			parms=new Object[] { easybuyProductCategory.getName(), easybuyProductCategory.getParentId(),
					easybuyProductCategory.getType(), easybuyProductCategory.getId() };
		}
		//声明变量接受受影响行数!
		int result=-1;
		
		try {
			result=super.excuteUpdate(sql, parms);
		} catch (Exception e) {
			//捕获异常
			e.printStackTrace();
		}
		return result;
	}

	@Override
	/**
	 * 根据删除的商品分类父ID编号，去查询一遍外键表中是否有数据
	 */
	public int findParentId(int parentId) {
		String sql="SELECT COUNT(1) FROM easybuy_product_category WHERE parentId = ?";
		//创建数组为属性赋值
		Object[] parms= {parentId};
		//声明变量接受受影响行数
		int find=-1;
		try {
			rs=super.excuteQuery(sql, parms);
			if(rs!=null) {
				while(rs.next()) {
					find=rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return find;
	}

	@Override
	/**
	 * 查询分类是否存在商品
	 */
	public int getProductById(String type, int id) {
		String sql = "SELECT COUNT(1) FROM easybuy_product WHERE 1=1 ";
		sql+=" AND categoryLevel"+type+"=?";
		//创建数组为属性赋值
		Object[] parms= {id};
		//声明变量接受受影响行数
		int find=-1;
		try {
			rs=super.excuteQuery(sql, parms);
			if (rs!=null) {
				while (rs.next()) {
					find=rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return find;
	}

	@Override
	public List<EasybuyProductCategory> findEasybuyProductCategoryAll(int currentPage, int rowPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(EasybuyProductCategory easybuyProductCategory) {
		// TODO Auto-generated method stub
		return 0;
	}

}
