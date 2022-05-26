package com.houdaoul.ecom.onlineshopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserRegisterDto {

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
