package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository repo,
                            PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(AdminForm form) {
        Admin a = new Admin();
        a.setLastName(form.getLastName());
        a.setFirstName(form.getFirstName());
        a.setEmail(form.getEmail());
        //  ここでハッシュ化してからセット！
        a.setPassword(passwordEncoder.encode(form.getPassword()));
        repo.save(a);
    }
}
