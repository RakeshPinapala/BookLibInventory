package com.aryans.library.model.DataBase;

public interface UserDataBase {
	public String getUserIdQuery();
	public String getInsertUserQuery();
	public String getUserByNameQuery();
	public String getUserPassByName();
	public String getAllUsers();
	public String getUpdateUserQuery();
}
