package com.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages multiple Address Books identified by unique names.
 * UC5: Introduced AddressBookSystem to hold a HashMap of AddressBook by name.
 * UC7: Added searchByCity and searchByState across all address books using Java Streams.
 */
public class AddressBookSystem {

    // Dictionary of Address Book Name -> AddressBook
    private Map<String, AddressBook> addressBooks;

    public AddressBookSystem() {
        this.addressBooks = new HashMap<>();
    }

    /**
     * UC5: Create and add a new Address Book with the given name.
     */
    public void addAddressBook(String name) {
        if (addressBooks.containsKey(name)) {
            System.out.println("Address Book already exists: " + name);
        } else {
            addressBooks.put(name, new AddressBook(name));
            System.out.println("Address Book created: " + name);
        }
    }

    /**
     * UC5: Get an Address Book by name.
     */
    public AddressBook getAddressBook(String name) {
        AddressBook book = addressBooks.get(name);
        if (book == null) {
            System.out.println("Address Book not found: " + name);
        }
        return book;
    }

    /**
     * UC5: List all available Address Book names.
     */
    public void listAddressBooks() {
        if (addressBooks.isEmpty()) {
            System.out.println("No Address Books found.");
            return;
        }
        System.out.println("\n=== Available Address Books ===");
        addressBooks.keySet().forEach(name ->
                System.out.println("  - " + name + " ("
                        + addressBooks.get(name).getContacts().size() + " contacts)"));
    }

    /**
     * UC7: Search for contacts by city across ALL address books using Java Streams.
     * Returns multiple results.
     */
    public void searchByCity(String city) {
        System.out.println("\n=== Search Results - City: " + city + " ===");
        List<Contact> results = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No contacts found in city: " + city);
        } else {
            results.forEach(System.out::println);
            System.out.println("Total found: " + results.size());
        }
    }

    /**
     * UC7: Search for contacts by state across ALL address books using Java Streams.
     * Returns multiple results.
     */
    public void searchByState(String state) {
        System.out.println("\n=== Search Results - State: " + state + " ===");
        List<Contact> results = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No contacts found in state: " + state);
        } else {
            results.forEach(System.out::println);
            System.out.println("Total found: " + results.size());
        }
    }

    public Map<String, AddressBook> getAllAddressBooks() {
        return addressBooks;
    }
}
