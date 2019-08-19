package com.aryans.library.model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aryans.library.model.Dao.BooksModelDao;
import com.aryans.library.model.Dao.OrdersModelDao;
import com.aryans.library.model.Dao.UserModelDao;

public class TestDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/aryans/library/springConfigs/ModelDaoConfig.xml");
		
		UserModelDao umDao = (UserModelDao) appContext.getBean("userModelDao");
		//System.out.println(umDao.insertUser(new Object[] {"USER", "Sitama", "onePunchMan", null}));
		//System.out.println(umDao.getUserById(2L).getUserName());
		Map<String,Object> valuesMap = new HashMap<String,Object>();
		valuesMap.put("USER_TYPE", "Admin");
		valuesMap.put("USER_NAME", "Sitama gundu");
		
		System.out.println(umDao.updateUserDetails(valuesMap, "Sitama"));
		
		//BooksModelDao booksDao = (BooksModelDao) appContext.getBean("booksModelDao");
		//System.out.println(booksDao.getBookByID(1L).getImages());
		//System.out.println(booksDao.insertBook(new Object[] {10, "Horror", 580.00, "Dummy_path1", "This is a horror book and harry potter","horror,Basic Instinct,harry potter", "Horror Book1"}));
		
//		OrdersModelDao ordersDao = (OrdersModelDao) appContext.getBean("ordersModelDao");
//		System.out.println(ordersDao.insertQuery(new Object[] {2L,2L,"Sitama",new Timestamp(2019, 07,21, 17, 25, 00, 00), new Timestamp(2019, 06,21, 17, 25, 00, 00)}));
		
		
	}
}

