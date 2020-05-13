package com.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	
	static {//�ھ�̬������£�factoryֻ�ᱻ����һ��
		System.out.println("static factory-----------");
		try {
			InputStream input=Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static SqlSession createSqlSession() {
		
		return factory.openSession();//Ϊtrue�Զ��ύ
	}
	public static void closeSqlSession(SqlSession sqlSession) {
		if(null !=sqlSession) {
			sqlSession.close();
		}
	}

}
