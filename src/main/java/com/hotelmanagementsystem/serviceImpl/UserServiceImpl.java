package com.hotelmanagementsystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.User;
import com.hotelmanagementsystem.repository.UserRepository;
import com.hotelmanagementsystem.service.UserService;


@Service //here we have to write business logics of the system
public class UserServiceImpl implements UserService {
	@Autowired
private UserRepository userRepo;
	@Override
	public void userSignup(User user) {
		
		userRepo.save(user);
	}

	@Override
	public User userLogin(String email, String pass) {
		
	return userRepo.findByEmailAndPassword(email,pass);
		
	}

	@Override
	public User passwordForget(String email) {
		return userRepo.findByEmail(email);
		
	}

	@Override
	public void updatePassword(User u) {
		userRepo.save(u);
		
	}

}
