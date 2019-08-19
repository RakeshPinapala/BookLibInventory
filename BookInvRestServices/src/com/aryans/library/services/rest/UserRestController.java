package com.aryans.library.services.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryans.library.controller.UserFlowControler;
import com.aryans.library.exceptions.GenericLibraryException;
import com.aryans.library.exceptions.ResourseNotFoundException;
import com.aryans.library.model.UserModel;
import com.aryans.library.services.rest.JsonPOJO.ExceptionPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserFlowControler ufc;
	
	@GetMapping("/{userName}")
	public UserModel getUserByNameService(@PathVariable String userName) throws ClassNotFoundException, SQLException, ResourseNotFoundException, GenericLibraryException {
		return ufc.getUserByNameOrEmail(userName);
	}
	
	@GetMapping("/")
	public List<UserModel> getAllUsers() {
		return ufc.getAllUsers();
	}
	
	@PostMapping(path = "/register", consumes = "application/json")
	public String registerUserService(@RequestBody UserModel userModel) throws GenericLibraryException {
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
	
	@PutMapping(path = "/{userName}", consumes = "application/json")
	public String updateUserService(@RequestBody String json, @PathVariable String userName) throws JsonMappingException, JsonProcessingException, GenericLibraryException {
		ObjectMapper objMap = new ObjectMapper();
		Map<String, Object> values = objMap.readValue(json, new TypeReference<Map<String, Object>>() {}) ;
		return Boolean.toString(ufc.updateUser(values, userName));
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionPojo> handleGenericException(GenericLibraryException gLe) {
		ExceptionPojo exceptionPojo = new ExceptionPojo();
		exceptionPojo.setMessage(gLe.getMessage());
		exceptionPojo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exceptionPojo.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ExceptionPojo>(exceptionPojo,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionPojo> handleNotFoundException (ResourseNotFoundException rNf) {
		ExceptionPojo exceptionPojo = new ExceptionPojo();
		exceptionPojo.setMessage(rNf.getMessage());
		exceptionPojo.setStatusCode(HttpStatus.NOT_FOUND.value());
		exceptionPojo.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ExceptionPojo>(exceptionPojo,HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
