package com.service.address.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EasybuyUserAddressDao;
import com.dao.MyBatisUtil;
import com.entity.EasybuyUserAddress;
import com.service.address.EasybuyUserAddressService;
import com.utils.DataBaseUtil;
@Service
public class EasybuyUserAddressServiceImpl implements EasybuyUserAddressService {
	@Autowired
	private EasybuyUserAddressDao address;
	@Override
	public List<EasybuyUserAddress> getEasybuyUserAddressAll(int userId) {
		List<EasybuyUserAddress> list = null;
		try {
			list = address.findEasybuyUserAddressAll(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public int addUserAddress(EasybuyUserAddress easybuyUserAddress) {
		int reuslt = -1;
		try {
			reuslt = address.updateEasybuyUserAddressById(easybuyUserAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	@Override
	public EasybuyUserAddress getUserAddressById(int id) {
		EasybuyUserAddress easybuyUserAddress = null;
		try {
			easybuyUserAddress = address.getUserAddressById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return easybuyUserAddress;
	}

	@Override
	public int getUserByIdAddress(int userId) {
		int reuslt = -1;
		try {
			reuslt = address.findUserByIdAddress(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

}
