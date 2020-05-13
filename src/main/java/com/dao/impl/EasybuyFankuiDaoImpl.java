package com.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.EasybuyFankuiDao;
import com.entity.EasybuyFankui;
import com.utils.DataBaseUtil;
import com.utils.Pager;

public class EasybuyFankuiDaoImpl extends BaseDao implements EasybuyFankuiDao{

	public EasybuyFankuiDaoImpl(Connection conn) {
		super(conn);
	}
	//保存用户反馈
	@Override
	public int saveFankui(EasybuyFankui fankui) {
		int result=0;
		String sql="INSERT into easybuy_fankui (faxie,manyidu,name,ipone) VALUES(?,?,?,?)";
		Object[] parms= {
				fankui.getFaxie(),
				fankui.getManyidu(),
				fankui.getName(),
				fankui.getIpone()
		};
		result=super.excuteUpdate(sql, parms);
		return result;
	}
//查询全部用户反馈
	@Override
	public List<EasybuyFankui>FankuiList(int currentPage,int rowPerPage ) {
		List<EasybuyFankui>list=new ArrayList<EasybuyFankui>();
//		String sql="select faxie,manyidu,name,ipone from easybuy_fankui";
//		try {
//			if(pager!=null) {
//				sql = " select faxie,manyidu,name,ipone from easybuy_fankui LIMIT ?,?";
//				Object[] parms= {(pager.getCurrentPage() - 1) * pager.getRowPerPage(),pager.getRowPerPage()};
//				rs=super.excuteQuery(sql, parms);
//			}else {
//			rs=super.excuteQuery(sql, null);
//			}
//			while(rs.next()) {
//				String faxie=rs.getString("faxie");
//				int manyidu=rs.getInt("manyidu");
//				String name=rs.getString("name");
//				String ipone=rs.getString("ipone");
//				EasybuyFankui fankui=new EasybuyFankui(faxie,manyidu,name,ipone);
//				list.add(fankui);
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//		closeAll(conn, pstmt, rs);
//		}
		return list;
	}
	/**
	 * 获取用户反馈总记录数业务
	 */
	public int getTotalCount() {
		int result =-1;
		String sql="select count(1) from easybuy_fankui";
		rs=super.excuteQuery(sql, null);
		if(rs!=null) {
			try {
				while(rs.next()) {
					result=rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeAll(conn,pstmt,rs);
			}
		}
		return result;
	}
}
