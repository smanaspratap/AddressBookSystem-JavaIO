package com.addressbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents an Address Book that holds a list of Contacts.
 * UC1:  addContact.
 * UC2:  editContact.
 * UC3:  deleteContact.
 * UC4:  addMultipleContacts loop.
 * UC5:  Has a unique name, used in AddressBookSystem.
 * UC6:  Duplicate check using Stream + equals().
 * UC10: getSortedByName() - sort contacts alphabetically by name using Java Streams.
 * UC11: getSortedByCity/State/Zip.
 * UC13: writeToFile / readFromFile using Java File IO (BufferedWriter/BufferedReader).
 */
public class AddressBook {

    private String name;
    private List<Contact> contacts;
    private static final Scanner scanner = new Scanner(System.in);

    public AddressBook(String name) {
        this.name     = name;
        this.contacts = new ArrayList<>();
    }

    public String getName()            { return name;     }
    public List<Contact> getContacts() { return contacts; }

    /**
     * UC1 + UC6: Add a contact with duplicate check via Java Streams + equals().
     */
    public void addContact(Contact contact) {
        boolean isDuplicate = contacts.stream().anyMatch(c -> c.equals(contact));
        if (isDuplicate) {
            System.out.println("Duplicate entry! Contact already exists: "
                    + contact.getFirstName() + " " + contact.getLastName());
            return;
        }
        contacts.add(contact);
        System.out.println("Contact added: " + contact.getFirstName() + " " + contact.getLastName());
    }

    /**
     * UC2: Edit contact by name.
     */
    public void editContact(String firstName, String lastName) {
        Contact found = contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName)
                          && c.getLastName().equalsIgnoreCase(lastName))
                .findFirst().orElse(null);
        if (found == null) { System.out.println("Contact not found: " + firstName + " " + lastName); return; }
        System.out.println("Editing: " + found + "\n(Press ENTER to keep current value)");
        System.out.print("Address [" + found.getAddress() + "]: ");
        String v; if (!(v = scanner.nextLine().trim()).isEmpty()) found.setAddress(v);
        System.out.print("City    [" + found.getCity()    + "]: ");
        if (!(v = scanner.nextLine().trim()).isEmpty()) found.setCity(v);
        System.out.print("State   [" + found.getState()   + "]: ");
        if (!(v = scanner.nextLine().trim()).isEmpty()) found.setState(v);
        System.out.print("Zip     [" + found.getZip()     + "]: ");
        if (!(v = scanner.nextLine().trim()).isEmpty()) found.setZip(v);
        System.out.print("Phone   [" + found.getPhoneNumber() + "]: ");
        if (!(v = scanner.nextLine().trim()).isEmpty()) found.setPhoneNumber(v);
        System.out.print("Email   [" + found.getEmail()   + "]: ");
        if (!(v = scanner.nextLine().trim()).isEmpty()) found.setEmail(v);
        System.out.println("Updated: " + found);
    }

    /**
     * UC3: Delete contact by name.
     */
    public void deleteContact(String firstName, String lastName) {
        boolean removed = contacts.removeIf(c ->
                c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName));
        System.out.println(removed
                ? "Deleted: " + firstName + " " + lastName
                : "Not found: " + firstName + " " + lastName);
    }

    /**
     * UC4: Loop to add multiple contacts.
     */
    public void addMultipleContacts() {
        String more = "y";
        while (more.equalsIgnoreCase("y")) {
            addContact(AddressBookMain.readContactFromConsole());
            System.out.print("Add another? (y/n): ");
            more = scanner.nextLine().trim();
        }
        System.out.println("Done adding contacts.");
    }

    /**
     * UC10: Sort contacts alphabetically by name using Java Streams and Comparator.
     */
    public List<Contact> getSortedByName() {
        return contacts.stream()
                .sorted(Comparator.comparing(c ->
                        (c.getFirstName() + " " + c.getLastName()).toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Display all contacts (UC1 / UC4).
     */
    public void displayContacts() {
        if (contacts.isEmpty()) { System.out.println("No contacts in: " + name); return; }
        System.out.println("\n=== Address Book: " + name + " ===");
        contacts.forEach(System.out::println);
        System.out.println("Total: " + contacts.size());
    }

    /**
     * UC10: Display contacts sorted alphabetically by name.
     */
    public void displaySortedByName() {
        List<Contact> sorted = getSortedByName();
        System.out.println("\n=== [" + name + "] Sorted by Name ===");
        if (sorted.isEmpty()) { System.out.println("No contacts."); return; }
        sorted.forEach(System.out::println);
    }

    // ── UC11 ─────────────────────────────────────────────────────────────────

    /**
     * UC11: Sort contacts by City using Java Streams and Comparator.
     */
    public List<Contact> getSortedByCity() {
        return contacts.stream()
                .sorted(Comparator.comparing(c -> c.getCity().toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * UC11: Sort contacts by State using Java Streams and Comparator.
     */
    public List<Contact> getSortedByState() {
        return contacts.stream()
                .sorted(Comparator.comparing(c -> c.getState().toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * UC11: Sort contacts by Zip using Java Streams and Comparator.
     */
    public List<Contact> getSortedByZip() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
    }

    /**
     * UC11: Display contacts sorted by city.
     */
    public void displaySortedByCity() {
        List<Contact> sorted = getSortedByCity();
        System.out.println("\n=== [" + name + "] Sorted by City ===");
        if (sorted.isEmpty()) { System.out.println("No contacts."); return; }
        sorted.forEach(System.out::println);
    }

    /**
     * UC11: Display contacts sorted by state.
     */
    public void displaySortedByState() {
        List<Contact> sorted = getSortedByState();
        System.out.println("\n=== [" + name + "] Sorted by State ===");
        if (sorted.isEmpty()) { System.out.println("No contacts."); return; }
        sorted.forEach(System.out::println);
    }

    /**
     * UC11: Display contacts sorted by zip.
     */
    public void displaySortedByZip() {
        List<Contact> sorted = getSortedByZip();
        System.out.println("\n=== [" + name + "] Sorted by Zip ===");
        if (sorted.isEmpty()) { System.out.println("No contacts."); return; }
        sorted.forEach(System.out::println);
    }

    // ── UC13: File IO ─────────────────────────────────────────────────────────

    /**
     * UC13: Write all contacts to a file using Java File IO (BufferedWriter).
     * Each contact is written as a CSV line:
     * firstName,lastName,address,city,state,zip,phone,email
     *
     * @param fileName the name of the file to write to
     */
    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("# Address Book: " + name);
            writer.newLine();
            for (Contact c : contacts) {
                writer.write(String.join(",",
                        c.getFirstName(),
                        c.getLastName(),
                        c.getAddress(),
                        c.getCity(),
                        c.getState(),
                        c.getZip(),
                        c.getPhoneNumber(),
                        c.getEmail()));
                writer.newLine();
            }
            System.out.println("Address Book saved to file: " + fileName
                    + " (" + contacts.size() + " contacts)");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * UC13: Read contacts from a file using Java File IO (BufferedReader).
     * Expected format per line: firstName,lastName,address,city,state,zip,phone,email
     * Lines starting with '#' are treated as comments.
     *
     * @param fileName the name of the file to read from
     */
    public void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                String[] parts = line.split(",", 8);
                if (parts.length < 8) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                Contact contact = new Contact(
                        parts[0].trim(), parts[1].trim(),
                        parts[2].trim(), parts[3].trim(),
                        parts[4].trim(), parts[5].trim(),
                        parts[6].trim(), parts[7].trim());
                addContact(contact);
                count++;
            }
            System.out.println("Loaded " + count + " contacts from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
