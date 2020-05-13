package com.service.news.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MyBatisUtil;
import com.dao.NewsDao;
import com.dao.OrderDao;
import com.dao.ProductDao;
//import com.dao.impl.NewsDaoImpl;
import com.entity.EasybuyNews;
import com.service.news.NewsService;
import com.utils.Pager;

/**
 * 资讯表业务逻辑层实现类
 * @author ASUS
 *
 */
@Service
public class NewsServiceImpl implements NewsService{
	SqlSession session = MyBatisUtil.createSqlSession();
	@Autowired
	private NewsDao newsMapper;
	/**
	 * 使用Logger记录日志
	 */
	//public static Logger logger=Logger.getLogger(BaseDao.class.getName());

	@Override
	/**
	 * 获取资讯列表业务
	 */
	public List<EasybuyNews> queryNewsList(Pager pager) {
		List<EasybuyNews>list=null;
		int currentPage=0;
		int rowPerPage=0;
		try {
			if(pager!=null) {
				currentPage=(pager.getCurrentPage()-1)*pager.getRowPerPage();
				rowPerPage=pager.getRowPerPage();
				list=newsMapper.queryNewsList(currentPage, rowPerPage);
			}else {
				list=newsMapper.NewsList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	/**
	 * 获取资讯列表总记录数业务
	 */
	public int getTotalCount() {
		int result = -1;
		try {
			result= session.getMapper(NewsDao.class).getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(session);
		}
		return result;
	}

	@Override
	/**
	 * 根据ID获取资讯列表想法请业务
	 */
	public EasybuyNews findNewsById(int id) {
		try {
			EasybuyNews news=newsMapper.getNewsById(id);
			if(news!=null) {
				return news;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
