package com.addressbook;

import java.util.Scanner;

/**
 * Main entry point for the Address Book System.
 * UC1: Displays welcome message and allows adding one contact via console.
 * UC2: Added menu with option to edit an existing contact by name.
 * UC3: Added menu option to delete a contact by name.
 * UC4: Added menu option to add multiple contacts via loop.
 * UC5: Refactored to use AddressBookSystem with multiple named address books.
 * UC6: Duplicate entry is rejected in addContact.
 * UC7: Added global search by city and state across all address books.
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
                    String newBook = scanner.nextLine().trim();
                    system.addAddressBook(newBook);
                    break;
                case "2":
                    system.listAddressBooks();
                    System.out.print("Enter Address Book name to open: ");
                    String bookName = scanner.nextLine().trim();
                    currentBook = system.getAddressBook(bookName);
                    if (currentBook != null) {
                        System.out.println("Opened: " + currentBook.getName());
                        runAddressBookMenu();
                    }
                    break;
                case "3":
                    system.listAddressBooks();
                    break;
                // UC7: Global Search
                case "4":
                    System.out.print("Search by city - Enter city name: ");
                    String city = scanner.nextLine().trim();
                    system.searchByCity(city);
                    break;
                case "5":
                    System.out.print("Search by state - Enter state name: ");
                    String state = scanner.nextLine().trim();
                    system.searchByState(state);
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
        System.out.println("1. Create New Address Book");
        System.out.println("2. Open Address Book");
        System.out.println("3. List All Address Books");
        System.out.println("4. Search by City  (across all books)");
        System.out.println("5. Search by State (across all books)");
        System.out.println("0. Exit");
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
                    Contact contact = readContactFromConsole();
                    currentBook.addContact(contact);
                    break;
                case "2":
                    System.out.println("\n--- Add Multiple Contacts ---");
                    currentBook.addMultipleContacts();
                    break;
                case "3":
                    System.out.println("\n--- Edit Contact ---");
                    System.out.print("Enter First Name: ");
                    String editFirst = scanner.nextLine().trim();
                    System.out.print("Enter Last Name : ");
                    String editLast  = scanner.nextLine().trim();
                    currentBook.editContact(editFirst, editLast);
                    break;
                case "4":
                    System.out.println("\n--- Delete Contact ---");
                    System.out.print("Enter First Name: ");
                    String delFirst = scanner.nextLine().trim();
                    System.out.print("Enter Last Name : ");
                    String delLast  = scanner.nextLine().trim();
                    currentBook.deleteContact(delFirst, delLast);
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
        System.out.println("9. Back to Main Menu");
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
