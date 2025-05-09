package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
    List<Contact> findAllContacts();   // これでDBから全件を返すと定義した
    
    /**
     * ID で問い合わせ１件を取得する
     */
    Optional<Contact> findContactById(Long id);

    /**
     * 指定 ID の問い合わせを削除する
     */
    void deleteContact(Long id);

}
