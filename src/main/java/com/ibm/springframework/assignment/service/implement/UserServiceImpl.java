package com.ibm.springframework.assignment.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.springframework.assignment.dao.UserRepository;
import com.ibm.springframework.assignment.entity.User;
import com.ibm.springframework.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	 
	@Override
	public User getUserInfo(User user) {
		
		return userRepository.findByUsername(user.getUsername());
	}

	@Override
	public void addUserInfo(User user) {
		// TODO Auto-generated method stub
		
		userRepository.save(user);
	}

	@Override
	public User getUserInfoById(String uid) {
		// TODO Auto-generated method stub
		
		return userRepository.getUserInfoById(uid);
	}

}
