package com.EmailService.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmailService.micro.models.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{


}

