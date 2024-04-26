package com.example.project.EcommerceApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.EcommerceApp.model.CustomUserDetail;
import com.example.project.EcommerceApp.model.User;
import com.example.project.EcommerceApp.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found !!"));
        return user.map(CustomUserDetail::new).get();
    }

}
