package com.hotelmanagementsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.Contact;
import com.hotelmanagementsystem.repository.ContactRepository;
import com.hotelmanagementsystem.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	ContactRepository contactrepo;

	@Override
	public void sendFeedback(Contact contact) {
		contactrepo.save(contact);
	}

	@Override
	public List<Contact> getAllContact() {
		
		return contactrepo.findAll();
	}

}
