package com.service.fankui.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dao.EasybuyFankuiDao;
import com.dao.MyBatisUtil;
import com.dao.impl.EasybuyFankuiDaoImpl;
//import com.dao.impl.NewsDaoImpl;
import com.entity.EasybuyFankui;
import com.service.fankui.EasybuyFankuiService;
import com.utils.DataBaseUtil;
import com.utils.Pager;

public class EasybuyFankuiServiceImpl implements EasybuyFankuiService{
	//保存用户反馈信息
	@Override
	public int saveFankui(EasybuyFankui fankui) {
		int result=-1;
		SqlSession session=null;
		session=MyBatisUtil.createSqlSession();
		result=session.getMapper(EasybuyFankuiDao.class).saveFankui(fankui);
		session.commit();
		return result;
	}
	//查询用户反馈信息
	@Override
	public List<EasybuyFankui>FankuiList(Pager pager) {
		List<EasybuyFankui>list=null;
		SqlSession session = MyBatisUtil.createSqlSession();
		try {
			int currentPage = (pager.getCurrentPage() - 1) * pager.getRowPerPage();
			int rowPerPage = pager.getCurrentPage() * 8 - 1;
			list=session.getMapper(EasybuyFankuiDao.class).FankuiList(currentPage,rowPerPage);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(session);
		}
		return list;
	}
	public int getTotalCount() {
		SqlSession session=MyBatisUtil.createSqlSession();
		int result=-1;
		try {
			//获得连接对象
			result=session.getMapper(EasybuyFankuiDao.class).getTotalCount();
		} catch (Exception e) {
			//异常捕获
			e.printStackTrace();
		}finally {
		MyBatisUtil.closeSqlSession(session);
		}
		return result;
	}

}
