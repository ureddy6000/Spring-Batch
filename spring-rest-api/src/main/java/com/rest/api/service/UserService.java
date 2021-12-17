package com.rest.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.entity.User;
import com.rest.api.exception.BusinessException;
import com.rest.api.repository.UserRepo;
import com.rest.api.service.impl.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface{
	
	@Autowired
	private UserRepo crudRepo;

	@Override
	public User saveUser(User user) throws BusinessException {

		if(user.getName().isEmpty() || user.getName().length() == 0 ) {
			throw new BusinessException("601","Name cannot be empty");
		}
		try {
			User savedUser = crudRepo.save(user);
			return savedUser;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602","User is null!" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong in Service layer while saving the user" + e.getMessage());
		}


	}

	@Override
	public List<User> getAllUsers() throws BusinessException {
		List<User> userList = null;
		try {
			userList = crudRepo.findAll();
		}
		catch (Exception e) {
			throw new BusinessException("605","Something went wrong in Service layer while fetching all users" + e.getMessage());
		}
		if(userList.isEmpty())
			throw new BusinessException("604", "There are no users in the database");
		return userList;
	}
		
	

	@Override
	public User getUserById(Long userId) throws BusinessException  {
		try {
			return crudRepo.findById(userId).get();
			
		}catch (IllegalArgumentException e) {
			throw new BusinessException("606","User ID cannot be null" + e.getMessage());
		}
		catch (NoSuchElementException e) {
			throw new BusinessException("607","given user id doesnot exist in the database" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("609","Something went wrong in Service layer while fetching all users" + e.getMessage());
		}
		
	}

	@Override
	public void deleteUserById(Long userId) throws BusinessException,Exception {
		try {
			crudRepo.deleteById(userId);
		}catch (IllegalArgumentException e) {
			throw new BusinessException("608","The User id cannot be null" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("610","Something went wrong in Service layer while fetching all users" + e.getMessage());
		}
		
	}

}
