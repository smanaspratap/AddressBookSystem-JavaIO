package com.addressbook;

import java.util.Scanner;

/**
 * Main entry point for the Address Book System.
 * UC1:  Welcome message and add contact.
 * UC2:  Edit contact.
 * UC3:  Delete contact.
 * UC4:  Add multiple contacts.
 * UC5:  Multiple address books.
 * UC6:  Duplicate check.
 * UC7:  Search by city/state across all books.
 * UC8:  View by city/state dictionary.
 * UC9:  Count by city and by state.
 * UC10: Sort all contacts by name.
 * UC11: Sort all contacts by city, state, or zip.
 */
public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static AddressBookSystem system = new AddressBookSystem();
    private static AddressBook currentBook  = null;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Welcome to Address Book Program      ");
        System.out.println("========================================");

        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Enter Address Book name to create: ");
                    system.addAddressBook(scanner.nextLine().trim());
                    break;
                case "2":
                    system.listAddressBooks();
                    System.out.print("Enter Address Book name to open: ");
                    currentBook = system.getAddressBook(scanner.nextLine().trim());
                    if (currentBook != null) {
                        System.out.println("Opened: " + currentBook.getName());
                        runAddressBookMenu();
                    }
                    break;
                case "3":
                    system.listAddressBooks();
                    break;
                // UC7
                case "4":
                    System.out.print("Enter city to search: ");
                    system.searchByCity(scanner.nextLine().trim());
                    break;
                case "5":
                    System.out.print("Enter state to search: ");
                    system.searchByState(scanner.nextLine().trim());
                    break;
                // UC8
                case "6":
                    system.viewByCity();
                    break;
                case "7":
                    system.viewByState();
                    break;
                // UC9
                case "8":
                    system.countByCity();
                    break;
                case "9":
                    system.countByState();
                    break;
                // UC10
                case "10":
                    system.displaySortedByName();
                    break;
                // UC11
                case "11":
                    system.displaySortedByCity();
                    break;
                case "12":
                    system.displaySortedByState();
                    break;
                case "13":
                    system.displaySortedByZip();
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

    private static void printMainMenu() {
        System.out.println("\n====== Address Book System Menu ======");
        System.out.println("1.  Create New Address Book");
        System.out.println("2.  Open Address Book");
        System.out.println("3.  List All Address Books");
        System.out.println("4.  Search by City   (UC7)");
        System.out.println("5.  Search by State  (UC7)");
        System.out.println("6.  View by City     (UC8)");
        System.out.println("7.  View by State    (UC8)");
        System.out.println("8.  Count by City    (UC9)");
        System.out.println("9.  Count by State   (UC9)");
        System.out.println("10. Sort by Name     (UC10)");
        System.out.println("11. Sort by City     (UC11)");
        System.out.println("12. Sort by State    (UC11)");
        System.out.println("13. Sort by Zip      (UC11)");
        System.out.println("0.  Exit");
        System.out.print("Choose: ");
    }

    private static void runAddressBookMenu() {
        boolean running = true;
        while (running) {
            printBookMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("\n--- Add Single Contact ---");
                    currentBook.addContact(readContactFromConsole());
                    break;
                case "2":
                    System.out.println("\n--- Add Multiple Contacts ---");
                    currentBook.addMultipleContacts();
                    break;
                case "3":
                    System.out.println("\n--- Edit Contact ---");
                    System.out.print("First Name: ");
                    String ef = scanner.nextLine().trim();
                    System.out.print("Last Name : ");
                    String el = scanner.nextLine().trim();
                    currentBook.editContact(ef, el);
                    break;
                case "4":
                    System.out.println("\n--- Delete Contact ---");
                    System.out.print("First Name: ");
                    String df = scanner.nextLine().trim();
                    System.out.print("Last Name : ");
                    String dl = scanner.nextLine().trim();
                    currentBook.deleteContact(df, dl);
                    break;
                case "5":
                    currentBook.displayContacts();
                    break;
                case "9":
                    running = false;
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printBookMenu() {
        System.out.println("\n=== [" + currentBook.getName() + "] ===");
        System.out.println("1. Add Single Contact");
        System.out.println("2. Add Multiple Contacts");
        System.out.println("3. Edit Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Display All Contacts");
        System.out.println("9. Back");
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
