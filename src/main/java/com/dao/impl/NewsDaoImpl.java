package com.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.NewsDao;
import com.entity.EasybuyNews;
import com.utils.DataBaseUtil;
import com.utils.Pager;

//public class NewsDaoImpl extends BaseDao implements NewsDao{
//
//	public NewsDaoImpl(Connection conn) {
//		super(conn);
//		// TODO 自动生成的构造函数存根
//	}
//	/**
//	 * 查询所有资讯列表！
//	 * */
////	@Override
////	public List<EasybuyNews> queryNewsList(Pager pager) {
////		// 创建集合！
////				List<EasybuyNews> newsList = null;
////				// SQL语句！
////				String sql = " select id,title,content,createTime FROM easybuy_news";
////				ResultSet rs=null;
////				try {
////					if(pager!=null) {
////						sql = " SELECT id,title,content,createTime FROM easybuy_news LIMIT ?,?";
////						Object[] parms= {(pager.getCurrentPage() - 1) * pager.getRowPerPage(),pager.getRowPerPage()};
////						rs=super.excuteQuery(sql, parms);
////					}else {
////						rs=super.excuteQuery(sql, null);
////					}
////					
////					newsList=new ArrayList<EasybuyNews>();
////					while (rs.next()) {
////						int id=rs.getInt("id");
////						String title=rs.getString("title");
////						String content=rs.getString("content");
////						Date createTime=rs.getDate("createTime");
////						EasybuyNews news=new EasybuyNews(id, title, content, createTime);
////						newsList.add(news);
////					}
////				} catch (Exception e) {
////					e.printStackTrace();
////				}finally{			
////					DataBaseUtil.closeAll(rs, null, null);
////				}
////				return newsList;
////	}
//	/**
//	 * 获取资讯列表总记录数！
//	 * */
////	@Override
////	public int getTotalCount() {
////		int result =-1;
////		String sql="select count(1) from easybuy_news";
////		rs=super.excuteQuery(sql, null);
////		if(rs!=null) {
////			try {
////				while(rs.next()) {
////					result=rs.getInt(1);
////				}
////			} catch (Exception e) {
////				e.printStackTrace();
////			}finally {
////				closeAll(conn,pstmt,rs);
////			}
////		}
////		return result;
////	}
//	/**
//	 * 根据ID查询资讯列表详情！
//	 * */
//	@Override
//	public EasybuyNews getNewsById(int id) {
//		EasybuyNews easyBuyNews=null;
//		try {
//			String sql="select * from easybuy_news where id=?";
//			Object[]parms= {id};
//			rs=super.excuteQuery(sql, parms);
//			while(rs.next()) {
//				easyBuyNews=new EasybuyNews();
//				easyBuyNews.setId(rs.getInt(1));
//				easyBuyNews.setTitle(rs.getString(2));
//				easyBuyNews.setContent(rs.getString(3));
//				easyBuyNews.setCreateTime(rs.getDate(4));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			closeAll(conn,pstmt,rs);
//		}
//		return easyBuyNews;
//	}
//	@Override
//	public List<EasybuyNews> queryNewsList(int current, int rowPer) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
