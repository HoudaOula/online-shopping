package com.houdaoul.ecom.onlineshopping.service;

import com.houdaoul.ecom.onlineshopping.domain.Authority;
import com.houdaoul.ecom.onlineshopping.domain.User;
import com.houdaoul.ecom.onlineshopping.dto.UserRegisterDto;
import com.houdaoul.ecom.onlineshopping.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> saveUser(UserRegisterDto userDto) throws Exception {
        if (userRepository.findById(userDto.getEmail()).isPresent()) {
            logger.error("User " + userDto.getEmail() + " already exists!");
            throw new Exception();
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(encryptPassword(userDto.getPassword()))
                .authorities(getUserRoles())
                .build();

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> saveAdmin(UserRegisterDto userDto) throws Exception {
        if (getUserByEmail(userDto.getEmail()).isPresent()) {
            logger.error("User " + userDto.getEmail() + " already exists!");
            throw new Exception();
        }

        User admin = User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(encryptPassword(userDto.getPassword()))
                .authorities(getAdminRoles())
                .build();

        return Optional.of(userRepository.save(admin));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findById(email);
    }

    /*
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
     */

    public List<Authority> getUserRoles() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("USER"));
        return authorities;
    }

    public List<Authority> getAdminRoles() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ADMIN"));
        authorities.add(new Authority("USER"));
        return authorities;
    }

    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
