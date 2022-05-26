package com.houdaoul.ecom.onlineshopping.controller;

import com.houdaoul.ecom.onlineshopping.config.JwtTokenProvider;
import com.houdaoul.ecom.onlineshopping.domain.User;
import com.houdaoul.ecom.onlineshopping.dto.UserLoginDto;
import com.houdaoul.ecom.onlineshopping.dto.UserRegisterDto;
import com.houdaoul.ecom.onlineshopping.service.UserService;
import com.houdaoul.ecom.onlineshopping.util.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  
  private final UserService userService;

  private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  private final JwtTokenProvider jwtTokenProvider;

  public AuthenticationController(UserService userService, JwtTokenProvider jwtTokenProvider) {
    this.userService = userService;
    this.jwtTokenProvider = jwtTokenProvider;
  }
  
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginRequest) {
    if (userLoginRequest.getEmail() == null || userLoginRequest.getPassword() == null) {
      var loginResponse = LoginResponse.builder()
              .message("Email and password required")
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
    }

    Optional<User> maybeUser = userService.getUserByEmail(userLoginRequest.getEmail());
    if (maybeUser.isEmpty()) {
      var loginResponse = LoginResponse.builder()
              .message("Email doesn't exist")
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
    }
    User curUser = maybeUser.get();
    if (!userService.checkPassword(curUser, userLoginRequest.getPassword())) {
      var loginResponse = LoginResponse.builder()
              .message("Incorrect password")
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
    }

    //String token = tokenizer.generateToken(curUser);
    String token = jwtTokenProvider.createToken(curUser);

    logger.info("User [" + curUser.getUsername() + "] logged in!");

    var loginResponse = LoginResponse.builder()
            .message("Logged in")
            .token(token)
            .build();

    return ResponseEntity.ok().body(loginResponse);
  }
  
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRegisterDto));
  }
}
