package com.unicredit.addressbook.repository;

import com.unicredit.addressbook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findBySurname(String surname);

    List<Contact> findByNameAndSurname(String name, String surname);

}