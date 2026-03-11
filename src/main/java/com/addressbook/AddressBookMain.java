package com.addressbook;

import java.util.Scanner;

/**
 * Main entry point for the Address Book System.
 * UC1: Displays welcome message and allows adding one contact via console.
 */
public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Welcome to Address Book Program      ");
        System.out.println("========================================");

        // UC1: Create a default address book and add one contact
        AddressBook addressBook = new AddressBook("MyAddressBook");

        System.out.println("\n--- Add New Contact ---");
        Contact contact = readContactFromConsole();
        addressBook.addContact(contact);

        System.out.println("\n--- All Contacts ---");
        addressBook.displayContacts();
    }

    /**
     * Reads contact details from the console and returns a Contact object.
     */
    public static Contact readContactFromConsole() {
        System.out.print("First Name   : ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last Name    : ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Address      : ");
        String address = scanner.nextLine().trim();

        System.out.print("City         : ");
        String city = scanner.nextLine().trim();

        System.out.print("State        : ");
        String state = scanner.nextLine().trim();

        System.out.print("Zip          : ");
        String zip = scanner.nextLine().trim();

        System.out.print("Phone Number : ");
        String phone = scanner.nextLine().trim();

        System.out.print("Email        : ");
        String email = scanner.nextLine().trim();

        return new Contact(firstName, lastName, address, city, state, zip, phone, email);
    }
}
