package com.houdaoul.ecom.onlineshopping.util;

import com.houdaoul.ecom.onlineshopping.domain.Authority;
import com.houdaoul.ecom.onlineshopping.repository.AuthorityRepository;

public class AuthorityInitialiazr {

    public final AuthorityRepository authorityRepository;

    public AuthorityInitialiazr(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void init() {
        authorityRepository.save(Authority.builder().name("USER").build());
        authorityRepository.save(Authority.builder().name("ADMIN").build());
    }
}
