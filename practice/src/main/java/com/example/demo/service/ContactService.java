package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {

    /**
     * フォームの内容を DB に保存する
     */
    void saveContact(ContactForm contactForm);

    /**
     * DB に保存された全てのお問い合わせを取得する
     * @return Contact エンティティのリスト
     */
    List<Contact> findAllContacts();
}
