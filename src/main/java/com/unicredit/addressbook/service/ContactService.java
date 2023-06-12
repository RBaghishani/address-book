package com.unicredit.addressbook.service;

import com.unicredit.addressbook.entity.Contact;
import com.unicredit.addressbook.dto.ContactDTO;
import com.unicredit.addressbook.exception.ContactNotFoundException;
import com.unicredit.addressbook.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ContactDTO> getAllContacts(String surname) {
        List<Contact> contacts;
        if (surname != null) {
            contacts = contactRepository.findBySurname(surname);
        } else {
            contacts = contactRepository.findAll();
        }
        return contacts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        return convertToDTO(contact);
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = convertToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        contact.setName(contactDTO.getName());
        contact.setSurname(contactDTO.getSurname());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        Contact updatedContact = contactRepository.save(contact);
        return convertToDTO(updatedContact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    private ContactDTO convertToDTO(Contact contact) {
        return modelMapper.map(contact, ContactDTO.class);
    }

    private Contact convertToEntity(ContactDTO contactDTO) {
        return modelMapper.map(contactDTO, Contact.class);
    }
}