package com.aryans.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.aryans.library.controller.ViewModelMapper.UserViewModelMapper;
import com.aryans.library.model.UserModel;
import com.aryans.library.model.Dao.UserModelDao;

@Controller
public class UserFlowControler {
	
	@Autowired
	private UserModelDao mUserModelDao;
	
	@Autowired
	private UserViewModelMapper mUserViewModelMapper;
	
	@Transactional
	public boolean registerUser(List userServiceValues) {
		return mUserModelDao.insertUser(mUserViewModelMapper.mapUserInsertion(userServiceValues));
	}
	
	@Transactional
	public boolean updateUser(Map<String, Object> values, String userName) {
		return mUserModelDao.updateUserDetails(mUserViewModelMapper.mapUpdateUserDetails(values), userName);
	}
	
	public boolean validateLogin(String uName, String passwd) {
		return BCrypt.checkpw(passwd, mUserModelDao.getUserPassByName(uName));
	}
	
	public UserModel getUserByNameOrEmail(String name) throws ClassNotFoundException, SQLException {
		return mUserModelDao.getUserByName(name);
	}
	
	public List<UserModel> getAllUsers() {
		return mUserModelDao.getAllUsers();
	}
	
}
