package com.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
