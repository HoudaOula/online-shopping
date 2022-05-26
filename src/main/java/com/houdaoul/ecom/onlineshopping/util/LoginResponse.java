package com.houdaoul.ecom.onlineshopping.util;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
  
  private String token;
  
  private String message;
  
  public String getToken() {
    return token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
}
