package com.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an Address Book that holds a list of Contacts.
 * UC1: Basic address book with addContact capability.
 * UC2: Added editContact by first and last name.
 * UC3: Added deleteContact by first and last name.
 */
public class AddressBook {

    private String name;
    private List<Contact> contacts;
    private static final Scanner scanner = new Scanner(System.in);

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
     * UC2: Edit an existing contact by first and last name.
     */
    public void editContact(String firstName, String lastName) {
        Contact found = contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName)
                          && c.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);

        if (found == null) {
            System.out.println("Contact not found: " + firstName + " " + lastName);
            return;
        }

        System.out.println("Editing contact: " + found);
        System.out.println("(Press ENTER to keep current value)");

        System.out.print("New Address [" + found.getAddress() + "] : ");
        String address = scanner.nextLine().trim();
        if (!address.isEmpty()) found.setAddress(address);

        System.out.print("New City [" + found.getCity() + "] : ");
        String city = scanner.nextLine().trim();
        if (!city.isEmpty()) found.setCity(city);

        System.out.print("New State [" + found.getState() + "] : ");
        String state = scanner.nextLine().trim();
        if (!state.isEmpty()) found.setState(state);

        System.out.print("New Zip [" + found.getZip() + "] : ");
        String zip = scanner.nextLine().trim();
        if (!zip.isEmpty()) found.setZip(zip);

        System.out.print("New Phone [" + found.getPhoneNumber() + "] : ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) found.setPhoneNumber(phone);

        System.out.print("New Email [" + found.getEmail() + "] : ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) found.setEmail(email);

        System.out.println("Contact updated successfully: " + found);
    }

    /**
     * UC3: Delete a contact by first and last name.
     */
    public void deleteContact(String firstName, String lastName) {
        boolean removed = contacts.removeIf(c ->
                c.getFirstName().equalsIgnoreCase(firstName)
             && c.getLastName().equalsIgnoreCase(lastName));

        if (removed) {
            System.out.println("Contact deleted successfully: " + firstName + " " + lastName);
        } else {
            System.out.println("Contact not found: " + firstName + " " + lastName);
        }
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
