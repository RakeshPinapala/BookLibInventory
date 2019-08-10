package com.aryans.library.services.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryans.library.controller.UserFlowControler;
import com.aryans.library.model.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserFlowControler ufc;
	
	@GetMapping("/{userName}")
	public UserModel getUserByNameService(@PathVariable String userName) throws ClassNotFoundException, SQLException {
		return ufc.getUserByNameOrEmail(userName);
	}
	
	@GetMapping("/")
	public List<UserModel> getAllUsers() {
		return ufc.getAllUsers();
	}
	
	@PostMapping(path = "/register", consumes = "application/json")
	public String registerUserService(@RequestBody UserModel userModel) {
		List values = new ArrayList();
		values.add(userModel.getUserType());
		values.add(userModel.getUserName());
		values.add(userModel.getPassword());
		values.add(userModel.getParentUserId());

		return Boolean.toString(ufc.registerUser(values));
	}
	
	@PostMapping(path = "/validate", consumes = "application/json")
	public String validateUserLogin(@RequestBody String json) throws IOException, JsonMappingException, JsonProcessingException {
		ObjectMapper objMap = new ObjectMapper();
		JsonNode jsonNode = objMap.readTree(json); //might throw IOException
		return Boolean.toString(ufc.validateLogin(jsonNode.get("UserName").asText(), jsonNode.get("Password").asText()));
	}
	
	
}
