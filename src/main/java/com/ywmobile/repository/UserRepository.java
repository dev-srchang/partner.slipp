package com.ywmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ywmobile.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
