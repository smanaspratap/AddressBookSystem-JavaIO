package com.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages multiple Address Books identified by unique names.
 * UC5: Introduced AddressBookSystem to hold a HashMap of AddressBook by name.
 * UC7: Added searchByCity and searchByState across all address books using Java Streams.
 * UC8: Maintain cityMap and stateMap dictionaries for viewing persons by city or state.
 */
public class AddressBookSystem {

    // Dictionary of Address Book Name -> AddressBook
    private Map<String, AddressBook> addressBooks;

    // UC8: City -> List<Contact> dictionary
    private Map<String, List<Contact>> cityMap;

    // UC8: State -> List<Contact> dictionary
    private Map<String, List<Contact>> stateMap;

    public AddressBookSystem() {
        this.addressBooks = new HashMap<>();
        this.cityMap      = new HashMap<>();
        this.stateMap     = new HashMap<>();
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

    /**
     * UC8: Rebuild cityMap and stateMap dictionaries using Java Streams + Collectors.groupingBy.
     * Should be called after any add/delete/edit operation.
     */
    public void refreshDictionaries() {
        List<Contact> all = addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.toList());

        cityMap  = all.stream().collect(Collectors.groupingBy(
                c -> c.getCity().toLowerCase()));
        stateMap = all.stream().collect(Collectors.groupingBy(
                c -> c.getState().toLowerCase()));
    }

    /**
     * UC8: View all persons grouped by city.
     */
    public void viewByCity() {
        refreshDictionaries();
        if (cityMap.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("\n=== Persons by City ===");
        cityMap.forEach((city, contacts) -> {
            System.out.println("\nCity: " + city.toUpperCase());
            contacts.forEach(c -> System.out.println("  " + c));
        });
    }

    /**
     * UC8: View all persons grouped by state.
     */
    public void viewByState() {
        refreshDictionaries();
        if (stateMap.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("\n=== Persons by State ===");
        stateMap.forEach((state, contacts) -> {
            System.out.println("\nState: " + state.toUpperCase());
            contacts.forEach(c -> System.out.println("  " + c));
        });
    }

    public Map<String, AddressBook> getAllAddressBooks() {
        return addressBooks;
    }

    public Map<String, List<Contact>> getCityMap()  { return cityMap;  }
    public Map<String, List<Contact>> getStateMap() { return stateMap; }
}
