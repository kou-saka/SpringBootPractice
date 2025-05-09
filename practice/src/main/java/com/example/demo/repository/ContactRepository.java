package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Contact;

//この一行でCRUD系のメソッドは使えるようになっている
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
}