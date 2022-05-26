package com.houdaoul.ecom.onlineshopping.controller;

import com.houdaoul.ecom.onlineshopping.domain.User;
import com.houdaoul.ecom.onlineshopping.exception.UserDoesNotExistException;
import com.houdaoul.ecom.onlineshopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/users")
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

     */

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email).orElseThrow(UserDoesNotExistException::new);
        return ResponseEntity.ok().body(user);
    }
}
