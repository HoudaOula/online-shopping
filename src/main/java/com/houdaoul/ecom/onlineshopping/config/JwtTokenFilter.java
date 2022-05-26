package com.houdaoul.ecom.onlineshopping.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
  
  private JwtTokenProvider jwtTokenProvider;

  private final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
  
  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(httpServletRequest);
    try {
      if (token != null && jwtTokenProvider.validateToken(token)) {
        Authentication auth = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        logger.info("Authentication provided");
      }
    } catch (Exception ex) {
      SecurityContextHolder.clearContext();
      httpServletResponse.sendError(403, ex.getMessage());
      ex.printStackTrace();
      logger.error("Authentication NOT provided !");
      return;
    }
    
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
  
}
