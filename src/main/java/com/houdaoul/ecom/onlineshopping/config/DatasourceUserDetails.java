package com.houdaoul.ecom.onlineshopping.config;

import com.houdaoul.ecom.onlineshopping.domain.User;
import com.houdaoul.ecom.onlineshopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DatasourceUserDetails implements UserDetailsService {
  
  private final UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final Optional<User> user = userRepository.findById(email);
    return user.orElseThrow(
        () -> new UsernameNotFoundException("Could not find user with email" + email));
  }
}
