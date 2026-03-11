package com.addressbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Address Book that holds a list of Contacts.
 * UC1: Basic address book with addContact capability.
 */
public class AddressBook {

    private String name;
    private List<Contact> contacts;

    public AddressBook(String name) {
        this.name     = name;
        this.contacts = new ArrayList<>();
    }

    public String getName() { return name; }

    public List<Contact> getContacts() { return contacts; }

    /**
     * UC1: Add a contact to this address book.
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added successfully: " + contact.getFirstName()
                + " " + contact.getLastName());
    }

    /**
     * Display all contacts in this address book.
     */
    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found in Address Book: " + name);
            return;
        }
        System.out.println("\n=== Address Book: " + name + " ===");
        contacts.forEach(System.out::println);
        System.out.println("Total contacts: " + contacts.size());
    }
}
