package com.springboot.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springsecurity.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
}
