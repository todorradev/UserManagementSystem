package com.toshko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toshko.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
