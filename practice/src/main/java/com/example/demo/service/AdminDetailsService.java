package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository repo;

    public AdminDetailsService(AdminRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = repo.findByEmail(email)
                          .orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

        // ROLE_ADMIN を付与
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        return new User(
            admin.getEmail(),
            admin.getPassword(),
            List.of(authority)
        );
    }
}
