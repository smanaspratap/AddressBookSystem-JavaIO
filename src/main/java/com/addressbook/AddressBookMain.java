package com.addressbook;

import java.util.Scanner;

/**
 * Main entry point for the Address Book System.
 * UC1: Displays welcome message and allows adding one contact via console.
 * UC2: Added menu with option to edit an existing contact by name.
 * UC3: Added menu option to delete a contact by name.
 * UC4: Added menu option to add multiple contacts via loop.
 */
public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Welcome to Address Book Program      ");
        System.out.println("========================================");

        AddressBook addressBook = new AddressBook("MyAddressBook");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("\n--- Add Single Contact ---");
                    Contact contact = readContactFromConsole();
                    addressBook.addContact(contact);
                    break;
                case "2":
                    System.out.println("\n--- Add Multiple Contacts ---");
                    addressBook.addMultipleContacts();
                    break;
                case "3":
                    System.out.println("\n--- Edit Contact ---");
                    System.out.print("Enter First Name of contact to edit: ");
                    String editFirst = scanner.nextLine().trim();
                    System.out.print("Enter Last Name  of contact to edit: ");
                    String editLast  = scanner.nextLine().trim();
                    addressBook.editContact(editFirst, editLast);
                    break;
                case "4":
                    System.out.println("\n--- Delete Contact ---");
                    System.out.print("Enter First Name of contact to delete: ");
                    String delFirst = scanner.nextLine().trim();
                    System.out.print("Enter Last Name  of contact to delete: ");
                    String delLast  = scanner.nextLine().trim();
                    addressBook.deleteContact(delFirst, delLast);
                    break;
                case "5":
                    addressBook.displayContacts();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========== Menu ==========");
        System.out.println("1. Add Single Contact");
        System.out.println("2. Add Multiple Contacts");
        System.out.println("3. Edit Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Display All Contacts");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
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
