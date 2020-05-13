package com.service.fankui;

import java.util.List;

import com.entity.EasybuyFankui;
import com.utils.Pager;

public interface EasybuyFankuiService {
	//用户反馈存储
	int saveFankui(EasybuyFankui fankui);
	//查询用户反馈信息
	public List<EasybuyFankui>FankuiList(Pager pager);
	/**
	 * 获取用户反馈总记录数业务
	 */
	public int getTotalCount();
}
