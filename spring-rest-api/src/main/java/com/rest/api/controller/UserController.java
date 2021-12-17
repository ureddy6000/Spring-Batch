package com.rest.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.entity.User;
import com.rest.api.exception.BusinessException;
import com.rest.api.service.impl.UserServiceInterface;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	private UserServiceInterface userService;
	
	@RequestMapping(path="/save",method=RequestMethod.POST
			,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addUser(@Valid @RequestBody User user){
		log.info("Inside the method addUser, ID: "+user.getId());
		try {
			User savedUser = userService.saveUser(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		}catch (BusinessException e) {
			BusinessException ce = new BusinessException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<BusinessException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			BusinessException ce = new BusinessException("611","Something went wrong in controller");
			return new ResponseEntity<BusinessException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path="/all",method=RequestMethod.GET
			,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> getAllUsers() throws BusinessException{
		log.info("Inside the method getAllUsers():");
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
	
	@RequestMapping(path="/{userid}",method=RequestMethod.GET
	,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getEmpById(@PathVariable("userid") Long userId){
		log.info("Inside the method getEmpById():"+userId);
		try {
			User retrievedUser = userService.getUserById(userId);
			return new ResponseEntity<User>(retrievedUser, HttpStatus.OK);
		}catch (Exception e) {
			BusinessException ce = new BusinessException("612","Something went wrong in controller");
			return new ResponseEntity<BusinessException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path="/delete/{userid}",method=RequestMethod.DELETE
	,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> deleteEmpById(@PathVariable("userid") Long userId) throws BusinessException, Exception{
		
		log.info("Inside the method deleteEmpById():"+userId);
		userService.deleteUserById(userId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path="/update",method=RequestMethod.PUT
	,produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> updateUser(@RequestBody User user) throws BusinessException{
		log.info("Inside the method updateUser, ID: "+user.getId());
		User savedUser = userService.saveUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	
	
}
