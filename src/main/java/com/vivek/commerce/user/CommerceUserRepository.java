package com.vivek.commerce.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommerceUserRepository extends JpaRepository<CommerceUser, UUID> {
}
