package com.aryans.library.model.Dao;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aryans.library.model.UserModel;
import com.aryans.library.model.DataBase.UserDataBase;
import com.aryans.library.model.Mapper.UserRowMapper;

@Repository
public class UserModelDao {
	
	@Autowired
	private JdbcTemplate mJdbcTemplate;
	
	@Autowired
	private UserDataBase mUserDataBase;
	
	/**
	 * Get the user row by USER_ID value
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserModel getUserById(long id) throws ClassNotFoundException, SQLException {
		return mJdbcTemplate.queryForObject(mUserDataBase.getUserIdQuery(), new Object[] {id}, new UserRowMapper());
	}
	
	/**
	 * Get the user row by USER_NAME value
	 * 
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserModel getUserByName(String name) throws ClassNotFoundException, SQLException {
		return mJdbcTemplate.queryForObject(mUserDataBase.getUserByNameQuery(), new Object[] {name}, new UserRowMapper());
	}
	
	/**
	 * Method to insert User row
	 * Assuming that order of values are maintained as per the query.
	 * 
	 * @param values - list of column values
	 * @return true if insertion is successful else false.
	 */
	public boolean insertUser(Object[] values) {
		return (mJdbcTemplate.update(mUserDataBase.getInsertUserQuery(), values) == 0) ? false : true;
	}
	
	/**
	 * Method to get the user's password by user name.
	 * 
	 * @param uName
	 * @return
	 */
	public String getUserPassByName(String uName) {
		return mJdbcTemplate.queryForObject(mUserDataBase.getUserPassByName(),new Object[] {uName},String.class);
	}
	
	/**
	 * Method to return list of all the users in the DB 
	 * 
	 * @return list of user model objects.
	 */
	public List<UserModel> getAllUsers() {
		return mJdbcTemplate.query(mUserDataBase.getAllUsers(), new UserRowMapper());
	}
	
	/**
	 * Method to update the user details
	 * 
	 * @return
	 */
	public boolean updateUserDetails(Map<String, Object> valuesMap, String currentUserName) {
		String sql = DaoUtility.PrepareUpdateQuery(mUserDataBase.getUpdateUserQuery(), valuesMap.keySet(), new UserModel());
		List list = new ArrayList();
		valuesMap.values().forEach( (Object obj) -> list.add(obj));
		list.add(currentUserName);
		Object[] args = list.toArray();
		
		return mJdbcTemplate.update(sql, args) > 0 ? true : false;
	}
}
