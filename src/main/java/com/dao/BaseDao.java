package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DataBaseUtil;


/**
 * 数据库管理！
 * 
 * @author Administrator
 *
 */
public class BaseDao {
	// 声明Connection对象属性！
	protected Connection conn = null;
	// 声明PreparedStatement对象属性！
	protected PreparedStatement pstmt=null;
	// 声明ResultSet对象属性！
	protected ResultSet rs=null;

	/**
	 * 带参构造方法！
	 * 
	 * @param conn
	 */
	public BaseDao(Connection conn) {
		// 通过构造方法的形式给Connection赋值！
		this.conn = conn;
	}

	/**
	 * 增！删！改！操作！！
	 * 
	 * @param sql
	 * @param parms
	 * @return
	 */
	protected int excuteUpdate(String sql, Object[] parms) {
		// 声明PreparedStatement对象！
		PreparedStatement pstms = null;
		// 声明变量接受受影响行数！
		int result = -1;
		try {
			// 执行SQL语句！
			pstms = conn.prepareStatement(sql);
			// 判断返回值结果是否为空！
			if (parms != null) {
				// 循环遍历Object数组！
				for (int i = 0; i < parms.length; i++) {
					// 循环为SQL语句赋值！
					pstms.setObject(i + 1, parms[i]);
				}
				// 调用增删改方法！
				result = pstms.executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源！
			DataBaseUtil.closeAll(null, pstms, null);
		}
		return result;
	}

	/**
	 * 查询操作！
	 * 
	 * @param sql
	 * @param parms
	 * @return
	 */
	protected ResultSet excuteQuery(String sql, Object[] params) {
		// 声明PreparedStatement对象！
		PreparedStatement pstms = null;
		// 声明ResultSet对象用于返回查询到的结果集！
		ResultSet result = null;
		try {
			// 执行SQL语句！
			pstms = conn.prepareStatement(sql);
			// 判断返回值结果是否为空！
			if (params != null) {
				// 循环遍历Object数组！
				for (int i = 0; i < params.length; i++) {
					// 循环为SQL语句赋值！
					pstms.setObject(i + 1, params[i]);
				}
			}
			// 调用查询方法！
			result = pstms.executeQuery();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	
	
	
	public Connection getConnection(){
		if(conn==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/easybuy","root","root");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * 实现增、删、改的方法
	 * */
	public int MYexecuteUpdate(String sql,Object[]params){
		conn=getConnection();
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1,params[i]);
				}
			}
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
	/**
	 * 实现查询方法
	 * */
	public ResultSet MYexecuteQuery(String sql,Object[]params){
		conn=getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1,params[i]);
				}
			}
			rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
