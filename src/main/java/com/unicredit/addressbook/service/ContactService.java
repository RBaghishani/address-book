package com.unicredit.addressbook.service;

import com.unicredit.addressbook.entity.Contact;
import com.unicredit.addressbook.dto.ContactDTO;
import com.unicredit.addressbook.exception.ContactAlreadyExistsException;
import com.unicredit.addressbook.exception.ContactNotFoundException;
import com.unicredit.addressbook.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable("contacts")
    public Page<ContactDTO> getAllContacts(String surname, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Contact> contacts;
        if (surname != null) {
            contacts = contactRepository.findBySurname(surname, pageable);
        } else {
            contacts = contactRepository.findAll(pageable);
        }
        return contacts.map(this::convertToDTO);
    }

    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        return convertToDTO(contact);
    }

    @CacheEvict(value = "contacts", allEntries = true)
    public ContactDTO createContact(ContactDTO contactDTO) {
        String name = contactDTO.getName();
        String surname = contactDTO.getSurname();
        List<Contact> existingContacts = contactRepository.findByNameAndSurname(name, surname);
        if (!existingContacts.isEmpty()) {
            throw new ContactAlreadyExistsException(name, surname);
        }
        Contact contact = convertToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    @CacheEvict(value = "contacts", allEntries = true)
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        contact.setName(contactDTO.getName());
        contact.setSurname(contactDTO.getSurname());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        Contact updatedContact = contactRepository.save(contact);
        return convertToDTO(updatedContact);
    }

    @CacheEvict(value = "contacts", allEntries = true)
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        contactRepository.deleteById(id);
    }

    private ContactDTO convertToDTO(Contact contact) {
        return modelMapper.map(contact, ContactDTO.class);
    }

    private Contact convertToEntity(ContactDTO contactDTO) {
        return modelMapper.map(contactDTO, Contact.class);
    }
}