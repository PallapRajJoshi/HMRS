package com.hotelmanagementsystem.service;

import java.util.List;

import com.hotelmanagementsystem.model.Contact;


public interface ContactService {
void sendFeedback(Contact contact);
List<Contact> getAllContact();

}
