package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyFankui;
import com.utils.Pager;

public interface EasybuyFankuiDao{
	//保存用户反馈信息
	int saveFankui(EasybuyFankui fankui);
	//查询用户反馈信息
	public List<EasybuyFankui>FankuiList(@Param("currentPage")int currentPage,
										 @Param("rowPerPage")int rowPerPage);
	/**
	 * 获取用户反馈总记录数业务
	 */
	public int getTotalCount();
}
