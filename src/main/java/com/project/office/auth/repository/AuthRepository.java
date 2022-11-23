package com.project.office.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.auth.entity.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {

}
