package com.hotelmanagementsystem.service;

import com.hotelmanagementsystem.model.User;

public interface UserService {
	void userSignup(User user);
	User userLogin(String email,String pass);
	User passwordForget(String email);
	void updatePassword(User u);
}
