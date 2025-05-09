package com.example.demo.service;

import java.util.List;	// List を使うためのインポート
import java.util.Optional;	//Optionalをつかうためのインポート

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;                  // Contact エンティティをつかうためのインポート
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void saveContact(ContactForm contactForm) {
        Contact contact = new Contact();
        contact.setLastName(contactForm.getLastName());
        contact.setFirstName(contactForm.getFirstName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhone(contactForm.getPhone());
        contact.setZipCode(contactForm.getZipCode());
        contact.setAddress(contactForm.getAddress());
        contact.setBuildingName(contactForm.getBuildingName());
        contact.setContactType(contactForm.getContactType());
        contact.setBody(contactForm.getBody());
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAllContacts() {               
        // JpaRepository が提供する findAll() を呼び出して、
        // DB の contacts テーブルの全レコードを取得し、
        // List<Contact> として返します。
        return contactRepository.findAll();
    }
    
    @Override
    public Optional<Contact> findContactById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

}
