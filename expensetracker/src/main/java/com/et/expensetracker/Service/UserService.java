package com.et.expensetracker.Service;

import com.et.expensetracker.Model.User;
import com.et.expensetracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(User user) throws Exception{
//        if(repo.findByUsername(user.getUsername()).isPresent()){
//            throw new Exception("Username is already in use!");
//        }
//
//        if(repo.findByEmail(user.getEmail()).isPresent()){
//            throw new Exception("Email is already in use with another account!");
//        }
//        User user = new User();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }
}
