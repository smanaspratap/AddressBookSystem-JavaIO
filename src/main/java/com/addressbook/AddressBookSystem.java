package com.addressbook;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages multiple Address Books identified by unique names.
 * UC5:  HashMap of AddressBook by name.
 * UC7:  searchByCity / searchByState across all books using Java Streams.
 * UC8:  cityMap / stateMap dictionaries (Collectors.groupingBy).
 * UC9:  countByCity / countByState using Collectors.counting().
 * UC10: getSortedByName() - sort all contacts alphabetically by name.
 * UC11: getSortedByCity() / getSortedByState() / getSortedByZip().
 */
public class AddressBookSystem {

    private Map<String, AddressBook> addressBooks;
    private Map<String, List<Contact>> cityMap;
    private Map<String, List<Contact>> stateMap;

    public AddressBookSystem() {
        this.addressBooks = new HashMap<>();
        this.cityMap      = new HashMap<>();
        this.stateMap     = new HashMap<>();
    }

    // ── UC5 ─────────────────────────────────────────────────────────────────

    public void addAddressBook(String name) {
        if (addressBooks.containsKey(name)) {
            System.out.println("Address Book already exists: " + name);
        } else {
            addressBooks.put(name, new AddressBook(name));
            System.out.println("Address Book created: " + name);
        }
    }

    public AddressBook getAddressBook(String name) {
        AddressBook book = addressBooks.get(name);
        if (book == null) System.out.println("Address Book not found: " + name);
        return book;
    }

    public void listAddressBooks() {
        if (addressBooks.isEmpty()) { System.out.println("No Address Books found."); return; }
        System.out.println("\n=== Available Address Books ===");
        addressBooks.keySet().forEach(n ->
                System.out.println("  - " + n + " (" + addressBooks.get(n).getContacts().size() + " contacts)"));
    }

    // ── UC7 ─────────────────────────────────────────────────────────────────

    public void searchByCity(String city) {
        System.out.println("\n=== Search - City: " + city + " ===");
        List<Contact> results = allContacts().stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
        printResults(results, "city: " + city);
    }

    public void searchByState(String state) {
        System.out.println("\n=== Search - State: " + state + " ===");
        List<Contact> results = allContacts().stream()
                .filter(c -> c.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
        printResults(results, "state: " + state);
    }

    // ── UC8 ─────────────────────────────────────────────────────────────────

    public void refreshDictionaries() {
        List<Contact> all = allContacts();
        cityMap  = all.stream().collect(Collectors.groupingBy(c -> c.getCity().toLowerCase()));
        stateMap = all.stream().collect(Collectors.groupingBy(c -> c.getState().toLowerCase()));
    }

    public void viewByCity() {
        refreshDictionaries();
        if (cityMap.isEmpty()) { System.out.println("No contacts available."); return; }
        System.out.println("\n=== Persons by City ===");
        cityMap.forEach((city, contacts) -> {
            System.out.println("\nCity: " + city.toUpperCase());
            contacts.forEach(c -> System.out.println("  " + c));
        });
    }

    public void viewByState() {
        refreshDictionaries();
        if (stateMap.isEmpty()) { System.out.println("No contacts available."); return; }
        System.out.println("\n=== Persons by State ===");
        stateMap.forEach((state, contacts) -> {
            System.out.println("\nState: " + state.toUpperCase());
            contacts.forEach(c -> System.out.println("  " + c));
        });
    }

    // ── UC9 ─────────────────────────────────────────────────────────────────

    /**
     * UC9: Count contacts by City using Java Streams + Collectors.groupingBy + counting.
     */
    public void countByCity() {
        Map<String, Long> counts = allContacts().stream()
                .collect(Collectors.groupingBy(c -> c.getCity().toLowerCase(), Collectors.counting()));
        System.out.println("\n=== Contact Count by City ===");
        if (counts.isEmpty()) { System.out.println("No contacts available."); return; }
        counts.forEach((city, count) ->
                System.out.println("  " + city.toUpperCase() + " : " + count));
    }

    /**
     * UC9: Count contacts by State using Java Streams + Collectors.groupingBy + counting.
     */
    public void countByState() {
        Map<String, Long> counts = allContacts().stream()
                .collect(Collectors.groupingBy(c -> c.getState().toLowerCase(), Collectors.counting()));
        System.out.println("\n=== Contact Count by State ===");
        if (counts.isEmpty()) { System.out.println("No contacts available."); return; }
        counts.forEach((state, count) ->
                System.out.println("  " + state.toUpperCase() + " : " + count));
    }

    // ── UC10 ─────────────────────────────────────────────────────────────────

    /**
     * UC10: Get all contacts sorted alphabetically by first+last name using Java Streams.
     */
    public void displaySortedByName() {
        List<Contact> sorted = allContacts().stream()
                .sorted(Comparator.comparing(c -> (c.getFirstName() + " " + c.getLastName()).toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n=== All Contacts Sorted by Name ===");
        if (sorted.isEmpty()) { System.out.println("No contacts available."); return; }
        sorted.forEach(System.out::println);
    }

    // ── UC11 ─────────────────────────────────────────────────────────────────

    /**
     * UC11: Sort all contacts by City using Java Streams.
     */
    public void displaySortedByCity() {
        List<Contact> sorted = allContacts().stream()
                .sorted(Comparator.comparing(c -> c.getCity().toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n=== All Contacts Sorted by City ===");
        if (sorted.isEmpty()) { System.out.println("No contacts available."); return; }
        sorted.forEach(System.out::println);
    }

    /**
     * UC11: Sort all contacts by State using Java Streams.
     */
    public void displaySortedByState() {
        List<Contact> sorted = allContacts().stream()
                .sorted(Comparator.comparing(c -> c.getState().toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n=== All Contacts Sorted by State ===");
        if (sorted.isEmpty()) { System.out.println("No contacts available."); return; }
        sorted.forEach(System.out::println);
    }

    /**
     * UC11: Sort all contacts by Zip using Java Streams.
     */
    public void displaySortedByZip() {
        List<Contact> sorted = allContacts().stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
        System.out.println("\n=== All Contacts Sorted by Zip ===");
        if (sorted.isEmpty()) { System.out.println("No contacts available."); return; }
        sorted.forEach(System.out::println);
    }

    // ── Helpers ─────────────────────────────────────────────────────────────

    private List<Contact> allContacts() {
        return addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .collect(Collectors.toList());
    }

    private void printResults(List<Contact> results, String label) {
        if (results.isEmpty()) {
            System.out.println("No contacts found for " + label);
        } else {
            results.forEach(System.out::println);
            System.out.println("Total found: " + results.size());
        }
    }

    public Map<String, AddressBook> getAllAddressBooks() { return addressBooks; }
    public Map<String, List<Contact>> getCityMap()       { return cityMap;      }
    public Map<String, List<Contact>> getStateMap()      { return stateMap;     }
}
