package com.aryans.library.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aryans.library.services.config.SpringRestConfig;

public class TestController {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ApplicationContext appContext = new ClassPathXmlApplicationContext("com/aryans/library/springConfigs/ModelDaoConfig.xml");
		//ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringRestConfig.class);
		
		UserFlowControler ufc = (UserFlowControler) appContext.getBean("userFlowControler");
//		List userServiceValues = new ArrayList();
//		userServiceValues.add("Admin");
//		//userServiceValues.add("N");
//		userServiceValues.add("Goku");
//		userServiceValues.add("Chichi");
//		userServiceValues.add(0L);
		
		//ufc.registerUser(userServiceValues);
		Map<String,Object> valuesMap = new HashMap<String,Object>();
		valuesMap.put("PASSWORD", "onePunchMan");
		valuesMap.put("USER_NAME", "Sitama");
		
		System.out.println(ufc.updateUser(valuesMap, "Sitama gundu"));
	}

}
