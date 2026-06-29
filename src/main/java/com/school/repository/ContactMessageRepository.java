package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.entity.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
