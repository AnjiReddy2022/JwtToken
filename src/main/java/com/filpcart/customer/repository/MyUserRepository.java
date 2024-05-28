package com.filpcart.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filpcart.customer.Model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

	Optional<MyUser> findByUsername(String username);

}
