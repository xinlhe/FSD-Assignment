package com.ibm.springframework.assignment.service;

import com.ibm.springframework.assignment.entity.User;


public interface UserService {

	public User getUserInfo(User user);
	
	public void addUserInfo(User user);
	
	public User getUserInfoById(String uid);
	
}
