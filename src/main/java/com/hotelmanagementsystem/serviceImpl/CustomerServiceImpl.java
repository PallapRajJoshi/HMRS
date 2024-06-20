package com.hotelmanagementsystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.Customer;
import com.hotelmanagementsystem.repository.CustomerRepository;

import com.hotelmanagementsystem.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
private CustomerRepository customerRepo;
	@Override
	public void userSignup(Customer customer) {
		customerRepo.save(customer);
		
	}

	@Override
	public Customer userLogin(String cemail, String cpass) {
		
		return customerRepo.findByCemailAndCpassword(cemail,cpass);
	}

	@Override
	public Customer passwordForget(String cemail) {
	
		return customerRepo.findByCemail(cemail);
	}

	@Override
	public void updatePassword(Customer c) {
		customerRepo.save(c);
		
	}

}
