package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.entity.ContactMessage;
import com.school.repository.ContactMessageRepository;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository repo;

    public ContactMessage save(ContactMessage msg) {
        return repo.save(msg);
    }
}