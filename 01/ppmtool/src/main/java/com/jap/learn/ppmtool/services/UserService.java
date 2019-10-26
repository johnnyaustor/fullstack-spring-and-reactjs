package com.jap.learn.ppmtool.services;

import com.jap.learn.ppmtool.domain.User;
import com.jap.learn.ppmtool.exceptions.UsernameAlreadyExistsException;
import com.jap.learn.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword("");
            // Username has to be unique

            // password match

            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }
}
