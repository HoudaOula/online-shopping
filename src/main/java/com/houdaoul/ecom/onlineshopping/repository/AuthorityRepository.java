package com.houdaoul.ecom.onlineshopping.repository;

import com.houdaoul.ecom.onlineshopping.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, String> {

    Optional<Authority> findAuthorityByName(String name);
}
