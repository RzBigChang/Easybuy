package com.service.news;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyNews;
import com.utils.Pager;

public interface NewsService  {
	/**
	 * 获取资讯列表业务
	 */
	public List<EasybuyNews>queryNewsList(Pager pager);
	/**
	 * 获取资讯列表总记录数业务
	 */
	public int getTotalCount();
	/**
	 * 根据ID获取资讯列表详情业务
	 */
	public EasybuyNews findNewsById(int id);
	
	
}
