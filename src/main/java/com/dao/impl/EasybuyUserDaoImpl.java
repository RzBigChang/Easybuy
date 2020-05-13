package com.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.EasybuyUserDao;
import com.entity.EasybuyUser;
import com.utils.Pager;
import com.utils.SecurityUtils;

public class EasybuyUserDaoImpl extends BaseDao implements EasybuyUserDao {
	public EasybuyUserDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 根据用户名密码 查询信息
	 */
	@Override
	public EasybuyUser findEasybuyUserInfo(String loginName, String password) {
		EasybuyUser easybuyUserLogin = null;
		String sql = "select * from easybuy_user where loginName=? and password=?";
		Object[] params = { loginName, password };
		try {
			rs = super.MYexecuteQuery(sql, params);
			if (rs != null) {

				while (rs.next()) {
					easybuyUserLogin = new EasybuyUser();
					easybuyUserLogin.setId(rs.getInt("id"));
					easybuyUserLogin.setLoginName(rs.getString("loginName"));
					easybuyUserLogin.setUserName(rs.getString("userName"));
					easybuyUserLogin.setPassword(rs.getString("password"));
					easybuyUserLogin.setSex(rs.getInt("sex"));
					easybuyUserLogin.setIdentityCode(rs.getString("identityCode"));
					easybuyUserLogin.setEmail(rs.getString("email"));
					easybuyUserLogin.setMobile(rs.getString("mobile"));
					easybuyUserLogin.setType(rs.getInt("type"));
					easybuyUserLogin.setCodeUrl(rs.getString("codeUrl"));
					easybuyUserLogin.setActivated(rs.getInt("activated"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return easybuyUserLogin;
	}

	/*
	 * 根据用户信息注册
	 */
	@Override
	public int addRegisterInfo(EasybuyUser easybuyuser) {
		int result = 0;
		try {
			String sql = "insert into easybuy_user(userName,loginName,password,sex,identityCode,email,mobile,type,codeUrl,activated)values(?,?,?,?,?,?,?,?,?,?)";
			Object[] params = {
					easybuyuser.getUserName(), 
					easybuyuser.getLoginName(),
					easybuyuser.getPassword(),
					easybuyuser.getSex(), 
					easybuyuser.getIdentityCode(),
					easybuyuser.getEmail(),
					easybuyuser.getMobile(),
					easybuyuser.getType(),
					easybuyuser.getCodeUrl(),
					easybuyuser.getActivated()
					};
			result = super.MYexecuteUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/*
	 * 根据用户ID删除信息！
	 */
	@Override
	public int delEasybuyUserById(int id) {
		int result = 0;
		try {
			String sql = "delete from easybuy_user where id=?";
			Object[] parms = { id };
			result = super.MYexecuteUpdate(sql, parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询用户信息总记录数！
	 * 
	 * @return
	 */
	@Override
	public int getTotalCount() {
		int result =0;
		String sql = "select COUNT(1) from easybuy_user";
		rs = super.MYexecuteQuery(sql, null);
		if (rs != null) {
			try {
				while (rs.next()) {
					result = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeAll(null, null, rs);
			}
		}
		return result;
	}

	/**
	 * 根据用户Id查询对应信息！
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public EasybuyUser findEasybuyUserById(int id) {
		EasybuyUser easuBuyUser = null;
		try {
			String sql = "select * from easybuy_user where id=?";
			Object[] parms = {id};
			rs = super.MYexecuteQuery(sql, parms);
			while (rs.next()) {
				easuBuyUser = new EasybuyUser();
				easuBuyUser.setId(rs.getInt(1));
				easuBuyUser.setUserName(rs.getString(2));
				easuBuyUser.setLoginName(rs.getString(3));
				easuBuyUser.setPassword(rs.getString(4));
				easuBuyUser.setSex(rs.getInt(5));
				easuBuyUser.setIdentityCode(rs.getString(6));
				easuBuyUser.setEmail(rs.getString(7));
				easuBuyUser.setMobile(rs.getString(8));
				easuBuyUser.setType(rs.getInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, null, rs);
		}
		return easuBuyUser;
	}

	/**
	 * 根据用户ID更新用户信息！
	 * 
	 * @return
	 */
	@Override
	public int modifyEasybuyUserById(EasybuyUser easybuyUser) {
		int result=0;
		String sql=null;
		Object[]parms=null;
		try {
			if(easybuyUser.getId()!=0) {
				sql="UPDATE easybuy_user SET userName=?,loginName=?,sex=?,identityCode=?,email=?,mobile=?,type=? where id=?";
				parms=new Object[] {
						easybuyUser.getUserName(),
						easybuyUser.getLoginName(),
						easybuyUser.getSex(),
						easybuyUser.getIdentityCode(),
						easybuyUser.getEmail(),
						easybuyUser.getMobile(),
						easybuyUser.getType(),
						easybuyUser.getId()
				};
			}else {
				sql="INSERT INTO easybuy_user(userName,loginName,password,sex,identityCode,email,mobile,type,codeUrl,activated)values(?,?,?,?,?,?,?,?,?,?)";
				parms=new Object[] {
						easybuyUser.getUserName(),
						easybuyUser.getLoginName(),
						easybuyUser.getPassword(),
						easybuyUser.getSex(),
						easybuyUser.getIdentityCode(),
						easybuyUser.getEmail(),
						easybuyUser.getMobile(),
						easybuyUser.getCodeUrl(),
						easybuyUser.getActivated(),
						easybuyUser.getType()
				};
			}
			result=super.MYexecuteUpdate(sql, parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询是否存在相同的用户名！
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public int findLoginNameByName(String name) {
		int count = -1;
		try {
			String sql = "SELECT * FROM easybuy_user WHERE loginName = ?";
			Object[] params = { name };
			rs = super.MYexecuteQuery(sql, params);
			if (rs != null) {
				while (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, null, rs);
		}
		return count;
	}

	// ************
	/**
	 * 修改密码操作验证！
	 * 
	 * @param name
	 * @param emailMobile
	 * @param value
	 * @return
	 */
	@Override
	public int findUserPasswordBy(String name, String emailMobile, String value) {
		int result=0;
		try {
			String sql="Select COUNT(1) from easybuy_user where loginName=?";
			Object[]parms= {name};
			rs=super.excuteQuery(sql, parms);
			if(rs!=null) {
				while(rs.next()) {
					result=rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, null, rs);
		}
		return result;
	}

	/**
	 * 根据用户名修改该用户密码！
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public int updateUserPasswordBy(String name, String password) {
		int result=0;
		try {
			String sql="UPDATE easybuy_user SET password=? where loginName=?";
			Object[]parms= {SecurityUtils.md5Hex(password),name};
			result=super.excuteUpdate(sql, parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//按随机码查询
	@Override
	public EasybuyUser SelectId(String codeUrl) {
		EasybuyUser easuBuyUser=null;
		String sql="select * from easybuy_user where codeUrl=?";
		try {
			Object[]params= {codeUrl};
			rs=super.MYexecuteQuery(sql, params);
			while(rs.next()) {
				easuBuyUser=new EasybuyUser();
				easuBuyUser.setId(rs.getInt(1));
				easuBuyUser.setUserName(rs.getString(2));
				easuBuyUser.setLoginName(rs.getString(3));
				easuBuyUser.setPassword(rs.getString(4));
				easuBuyUser.setSex(rs.getInt(5));
				easuBuyUser.setIdentityCode(rs.getString(6));
				easuBuyUser.setEmail(rs.getString(7));
				easuBuyUser.setMobile(rs.getString(8));
				easuBuyUser.setType(rs.getInt(9));
				easuBuyUser.setCodeUrl(rs.getString(10));
				easuBuyUser.setActivated(rs.getInt(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败");
		}finally {
			closeAll(null, null, rs);
		}
		return easuBuyUser;
	}
	//账户状态
	@Override
	public int XiuGaiDome(EasybuyUser user) {
		int result=0;
		String sql="update easybuy_user set activated=? where id=?";
		try {
			Object[]params= {
					user.getActivated(),
					user.getId()
			};
			result=super.excuteUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("更新失败");
		}
		return result;
	}
	//根据账户状态查询
	@Override
	public EasybuyUser SelectActivated(int activated) {
		EasybuyUser easybuyuser=null;
		String sql="select * from easybuy_user where activated=?";
		try {
			Object[]params= {activated};
			rs=super.MYexecuteQuery(sql, params);
			while(rs.next()) {
				easybuyuser=new EasybuyUser();
				easybuyuser.setId(rs.getInt(1));
				easybuyuser.setUserName(rs.getString(2));
				easybuyuser.setLoginName(rs.getString(3));
				easybuyuser.setPassword(rs.getString(4));
				easybuyuser.setSex(rs.getInt(5));
				easybuyuser.setIdentityCode(rs.getString(6));
				easybuyuser.setEmail(rs.getString(7));
				easybuyuser.setMobile(rs.getString(8));
				easybuyuser.setType(rs.getInt(9));
				easybuyuser.setCodeUrl(rs.getString(10));
				easybuyuser.setActivated(rs.getInt(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, null, rs);
		}
		return easybuyuser;
	}
	//按照用户名and密码查询账户状态
	@Override
	public EasybuyUser SelectNameandPwd(String name, String pwd) {
		EasybuyUser easybuyUser=null;
		String sql="select * from easybuy_user where loginName=? and password=?";
		try {
			Object[]params= {
					name,
					pwd
			};
			rs=super.excuteQuery(sql, params);
			while(rs.next()) {
				easybuyUser=new EasybuyUser();
				easybuyUser.setId(rs.getInt(1));
				easybuyUser.setUserName(rs.getString(2));
				easybuyUser.setLoginName(rs.getString(3));
				easybuyUser.setPassword(rs.getString(4));
				easybuyUser.setSex(rs.getInt(5));
				easybuyUser.setIdentityCode(rs.getString(6));
				easybuyUser.setEmail(rs.getString(7));
				easybuyUser.setMobile(rs.getString(8));
				easybuyUser.setType(rs.getInt(9));
				easybuyUser.setCodeUrl(rs.getString(10));
				easybuyUser.setActivated(rs.getInt(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, null, rs);
		}
		return easybuyUser;
	}


	@Override
	public List<EasybuyUser> findEasybuyUserAll(int currentPage, int rowPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
