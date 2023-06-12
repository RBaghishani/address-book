package com.unicredit.addressbook.exception;

public class ContactAlreadyExistsException extends RuntimeException {

    public ContactAlreadyExistsException(String name, String surname) {
        super("Contact with name '" + name + "' and surname '" + surname + "' already exists");
    }
}