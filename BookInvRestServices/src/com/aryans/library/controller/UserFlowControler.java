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
import com.aryans.library.exceptions.GenericLibraryException;
import com.aryans.library.exceptions.ResourseNotFoundException;
import com.aryans.library.model.UserModel;
import com.aryans.library.model.Dao.UserModelDao;

@Controller
public class UserFlowControler {
	
	@Autowired
	private UserModelDao mUserModelDao;
	
	@Autowired
	private UserViewModelMapper mUserViewModelMapper;
	
	@Transactional
	public boolean registerUser(List userServiceValues) throws GenericLibraryException {
		boolean result = false;
		try {
			result = mUserModelDao.insertUser(mUserViewModelMapper.mapUserInsertion(userServiceValues));
		} catch (Exception ex) {
			throw new GenericLibraryException(ex.getMessage());
		}
		return result;
	}
	
	@Transactional
	public boolean updateUser(Map<String, Object> values, String userName) throws GenericLibraryException {
		boolean result = false;
		try {
			result = mUserModelDao.updateUserDetails(mUserViewModelMapper.mapUpdateUserDetails(values), userName);
		} catch (Exception ex) {
			throw new GenericLibraryException(ex.getMessage());
		}
		
		return result;
	}
	
	public boolean validateLogin(String uName, String passwd) {
		return BCrypt.checkpw(passwd, mUserModelDao.getUserPassByName(uName));
	}
	
	public UserModel getUserByNameOrEmail(String name) throws ClassNotFoundException, SQLException , ResourseNotFoundException, GenericLibraryException {
		UserModel um = null;
		try {
		um = mUserModelDao.getUserByName(name);
		} catch (Exception ex) {
			throw new GenericLibraryException(ex.getMessage());
		}
		
		if(um == null) {
			throw new ResourseNotFoundException("User name " + name + " not found. ");
		}
		
		return mUserModelDao.getUserByName(name);
	}
	
	public List<UserModel> getAllUsers() {
		return mUserModelDao.getAllUsers();
	}
	
}
