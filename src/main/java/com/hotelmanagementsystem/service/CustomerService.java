package com.hotelmanagementsystem.service;

import com.hotelmanagementsystem.model.Customer;


public interface CustomerService {
	void userSignup(Customer customer);
	Customer userLogin(String cemail,String cpass);
	Customer passwordForget(String cemail);
	void updatePassword(Customer c);
}
