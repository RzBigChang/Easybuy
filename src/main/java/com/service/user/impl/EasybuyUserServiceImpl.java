package com.service.user.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EasybuyUserDao;
import com.dao.MyBatisUtil;
import com.dao.impl.EasybuyUserDaoImpl;
import com.entity.EasybuyUser;
import com.service.user.EasybuyUserService;
import com.utils.DataBaseUtil;
import com.utils.MailUtil;
import com.utils.Pager;
@Service
public class EasybuyUserServiceImpl implements EasybuyUserService{

	@Autowired
	private EasybuyUserDao usermapper;
	/**
	 * 根据用户名和密码查询对应信息！
	 * @param easybuyUser
	 * @return
	 */
	@Override
	public EasybuyUser findEasybuyUserInfo(String loginName,String password) {
		try {
			return usermapper.findEasybuyUserInfo(loginName, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	/**
	 * 根据用户信息注册
	 * @return
	 */
	@Override
	public int addRegisterInfo(EasybuyUser easybuyUser) {
		int result=0;
		try {
			result=usermapper.addRegisterInfo(easybuyUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * 查询所有用户信息！
	 * @return
	 */
	@Override
	public List<EasybuyUser> findEasybuyUserAll(Pager pager) {
		List<EasybuyUser>list=new ArrayList<EasybuyUser>();	
		int currentPage=(pager.getCurrentPage()-1)*pager.getRowPerPage();
		int rowPerPage=pager.getRowPerPage();
		try {
			list=usermapper.findEasybuyUserAll(currentPage,rowPerPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据用户ID删除信息！
	 * @param id
	 * @return
	 */
	@Override
	public int delEasybuyUserById(int id) {
		int result=0;
		try {
			result=usermapper.delEasybuyUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询用户信息总记录数！
	 * @return
	 */
	@Override
	public int getTotalCount() {
		int result=0;
		try {
			result=usermapper.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据用户Id查询对应信息！
	 * @param id
	 * @return
	 */
	@Override
	public EasybuyUser findEasybuyUserById(int id) {
		EasybuyUser easybuyuser=null;
		try {
			easybuyuser=usermapper.findEasybuyUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return easybuyuser;
	}
	/**
	 * 根据用户ID更新用户信息！
	 * @return
	 */
	@Override
	public int modifyEasybuyUserById(EasybuyUser easybuyUser) {
		int result=0;
		try {
			result=usermapper.modifyEasybuyUserById(easybuyUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查询是否存在相同的用户名！
	 * @param name
	 * @return
	 */
	@Override
	public int findLoginNameByName(String name) {
		int result=0;
		try {
			result=usermapper.findLoginNameByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//************
		/**  已删功能
		 * 修改密码操作验证！
		 * @param name
		 * @param emailMobile
		 * @param value
		 * @return
		 */
	@Override
	public int findUserPasswordBy(String name, String emailMobile, String value) {
		int result=0;
	//	result=easybuyUserdao.findUserPasswordBy(name, emailMobile, value);
		return result;
	}
	/**
	 * 	已删功能
	 * 根据用户名修改该用户密码！
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public int updateUserPasswordBy(String name, String password) {
		int result=0;
		//result=easybuyUserdao.updateUserPasswordBy(name, password);
		return result;
	}
	//根据随机码查询
	@Override
	public EasybuyUser SelectId(String codeUrl) {
		EasybuyUser user=null;
		try {
			user=usermapper.SelectId(codeUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return user;
	}
	//修改账户状态
	@Override
	public void XiuGaiDome(EasybuyUser user) {
		try {
			int result=usermapper.XiuGaiDome(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//根据账户状态查询
	@Override
	public EasybuyUser SelectActivated(int activated) {
		EasybuyUser rasybuyUser=null;
		try {
			rasybuyUser=usermapper.SelectActivated(activated);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rasybuyUser;
	}
	//根据用户名and密码查询账户状态
	@Override
	public EasybuyUser SelectNameandPwd(String name, String pwd) {
		EasybuyUser easybuyUser=null;
		try {
			easybuyUser=usermapper.SelectNameandPwd(name, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return easybuyUser;
	}
	
}
