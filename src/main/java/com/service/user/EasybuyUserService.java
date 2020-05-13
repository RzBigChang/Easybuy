package com.service.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.EasybuyUser;
import com.utils.Pager;

public interface EasybuyUserService {
	/**
	 * 根据用户名和密码查询对应信息！
	 * @param easybuyUser
	 * @return
	 */
	EasybuyUser findEasybuyUserInfo(String loginName,String password);
	
	/**
	 * 根据用户信息注册
	 * @return
	 */
	int addRegisterInfo(EasybuyUser easybuyUser);
	
	/**
	 * 查询所有用户信息！
	 * @return
	 */
	List<EasybuyUser> findEasybuyUserAll(Pager pager);
	
	/**
	 * 根据用户ID删除信息！
	 * @param id
	 * @return
	 */
	int delEasybuyUserById(int id);
	
	/**
	 * 查询用户信息总记录数！
	 * @return
	 */
	int getTotalCount();
	
	/**
	 * 根据用户Id查询对应信息！
	 * @param id
	 * @return
	 */
	EasybuyUser findEasybuyUserById(int id);
	
	/**
	 * 根据用户ID更新用户信息！
	 * @return
	 */
	int modifyEasybuyUserById(EasybuyUser easybuyUser);
	
	/**
	 * 查询是否存在相同的用户名！
	 * @param name
	 * @return
	 */
	int findLoginNameByName(String name);
	
	//************
	/**
	 * 修改密码操作验证！
	 * @param name
	 * @param emailMobile
	 * @param value
	 * @return
	 */
	int findUserPasswordBy(String name,String emailMobile,String value);
	
	/**
	 * 根据用户名修改该用户密码！
	 * @param name
	 * @param password
	 * @return
	 */
	//查找激活码 返回用户名
	EasybuyUser SelectId(String codeUrl);
	//查找到激活码后，更新数据库中的表
	void XiuGaiDome(EasybuyUser user);
	//按账户状态查询
	EasybuyUser SelectActivated(int activated);
	int updateUserPasswordBy(String name, String password);
	//根据用户名and密码查询账户状态
	EasybuyUser SelectNameandPwd(String name,String pwd);
}
