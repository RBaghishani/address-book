package com.unicredit.addressbook.repository;

import com.unicredit.addressbook.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findBySurname(String surname, Pageable pageable);

    Page<Contact> findAll(Pageable pageable);

    List<Contact> findByNameAndSurname(String name, String surname);

}