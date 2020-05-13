package com.service.product.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MyBatisUtil;
import com.dao.ProductCategoryDao;
import com.dao.impl.ProductCategoryDaoImpl;
import com.entity.EasybuyProductCategory;
import com.service.product.ProductCategoryService;
import com.utils.DataBaseUtil;
import com.utils.Pager;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao product;
	@Override
	/**
	 * 查询商品
	 */
	public List<EasybuyProductCategory> getProductCategoryList(int typeId) {
		List<EasybuyProductCategory> list = new ArrayList<EasybuyProductCategory>();
		try {
			list = product.getProductCategoryList(typeId);
		} catch (Exception e) {
			// 捕获异常！
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 获得所有商品分类信息
	 */
	public List<EasybuyProductCategory> getEasybuyProductCategoryAll(Pager pager) {
		int currentPage=0;
		int rowPerPage=0;
		try {
			if(pager!=null) {
				currentPage=(pager.getCurrentPage()-1)*pager.getRowPerPage();
				rowPerPage=pager.getRowPerPage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return product.findEasybuyProductCategoryAll(currentPage, rowPerPage);
	}

	@Override
	/**
	 * 获取总记录数
	 */
	public int getTotalCount() {
		int result=-1;
		try {
			result=product.getTotalCount();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	/**
	 * 根据商品ID删除指定商品信息业务
	 */
	public int deleteEasybuyProductCategoryById(int id) {
		int result=-1;
		try {
			result=product.delEasybuyProductCategoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	/**
	 * 根据id查询商品信息
	 */
	public EasybuyProductCategory getProductCategoryById(int id) {
		EasybuyProductCategory easybuyProductCategory=null;
		try {
			easybuyProductCategory=product.getProductCategoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return easybuyProductCategory;
	}

	@Override
	/**
	 * 一级分类
	 */
	public List<EasybuyProductCategory> getProductCategoryListOne() {
		List<EasybuyProductCategory> list=null;
		try {
			list=product.findProductCategoryListOne();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 二级分类
	 */
	public List<EasybuyProductCategory> getProductCategoryListTwo() {
		List<EasybuyProductCategory> list=null;
		try {
			list=product.findProductCategoryListTwo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 三级分类
	 */
	public List<EasybuyProductCategory> getProductCategoryListThree() {
		List<EasybuyProductCategory> list=null;
		try {
			list=product.findProductCategoryListThree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 根据父分类查询商品分类信息
	 */
	public List<EasybuyProductCategory> getProductCategoryListByparentId(int typeId, int parentId) {
		List<EasybuyProductCategory> list=null;
		try {
			list=product.getProductCategoryListByparentId(typeId, parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 新增商品分类
	 */
	public int insertEasybuyProductCategory(EasybuyProductCategory easybuyProductCategory) {
		int result=0;
		try {
			if(easybuyProductCategory.getId()==0) {
				result=product.insertEasybuyProductCategory(easybuyProductCategory);
			}else {
				result=product.update(easybuyProductCategory);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	/**
	 * 根据删除的商品分类父ID编号,去查询一遍外键表中是否有数据业务
	 */
	public int getdParentId(int parentId) {
		Connection conn=null;
		int result=-1;
		try {
			result=product.findParentId(parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	/**
	 * 查询分类是否存在商品
	 */
	public int getProductById(String type, int id) {
		Connection conn=null;
		int result=0;
		try {
			result=product.getProductById(type, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delEasybuyProductCategoryById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}



}
