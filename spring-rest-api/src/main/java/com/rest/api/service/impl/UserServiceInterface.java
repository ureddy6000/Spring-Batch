package com.rest.api.service.impl;

import java.util.List;

import com.rest.api.entity.User;
import com.rest.api.exception.BusinessException;

public interface UserServiceInterface {

	public User saveUser(User user) throws BusinessException;

	public List<User> getAllUsers();

	public User getUserById(Long userId);

	public void deleteUserById(Long userId);

}
