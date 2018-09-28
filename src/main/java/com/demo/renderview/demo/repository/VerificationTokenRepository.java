package com.demo.renderview.demo.repository;

import com.demo.renderview.demo.repository.entity.User;
import com.demo.renderview.demo.repository.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);
}
