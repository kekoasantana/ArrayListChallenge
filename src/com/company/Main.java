package com.company;

import java.util.Scanner;
/**
 * program that implements simple mobile phone
 * able to store, modify, remove and query contacts
 * create a separate class for Contacts (name and phone number)
 * create a master class (MobilePhone) that holds the ArrayList of Contacts
 * the MobilePhone class has the functionality listed above
 * add a menu of options that are available
 * Options: Quit, print list of contacts, add new contact, update existing contact, remove contact, add search/find contact
 * when adding or updating be sure to check if the contact already exists (use name)
 * be sure not to expose the inner workings of the ArrayList to MobilePhone
 * e.g. no ints, no .get(i) etc
 * MobilePhone should do everything with Contact objects only
 */


public class Main {

    private static Scanner in = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("661 312 0112");

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();

        while (!quit) {
            System.out.println("Enter action: (6 to show available actions))");
            int action = in.nextInt();
            in.nextLine();

            switch (action) {
                case 0:
                    System.out.println("System shutting down...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }

    private static void addNewContact() {
        System.out.println("Enter new contact name: ");
        String name = in.nextLine();
        System.out.println("Enter new contact number: ");
        String phoneNumber = in.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);
        if (mobilePhone.addNewContact(newContact)) {
            System.out.println("New Contact Added:\nName: " + name + "\nNumber: " + phoneNumber + "\n");
        } else {
            System.out.println("Cannot add, " + name + "already on file.");
        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name: ");
        String name = in.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact record not found.");
            return;
        }

        System.out.print("Enter new contact name: ");
        String newName = in.nextLine();
        System.out.print("Enter new contact number: ");
        String newNumber = in.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if (mobilePhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Successfully updated contact.");
        } else {
            System.out.println("Error updating contact.");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String name = in.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact record not found.");
            return;
        }

        if (mobilePhone.removeContact(existingContactRecord)) {
            System.out.println("Successfully deleted contact.");
        } else {
            System.out.println("Error deleting contact.");
        }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String name = in.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact record not found.");
            return;
        }

        System.out.println("Name: " + existingContactRecord.getName() + "\nPhone Number: " + existingContactRecord.getPhoneNumber());
    }


    private static void startPhone() {
        System.out.println("Starting Phone...");
    }

    private static void printActions() {
        System.out.println("\nAvailable Options\nPress");
        System.out.println("0 - to shutdown\n" +
                           "1 - to print contacts\n" +
                           "2 - to add a new contact\n" +
                           "3 - to update an existing contaact\n" +
                           "4 - to remove a contact\n" +
                           "5 - to search for a contact\n" +
                           "6 - to print a list of available actions");
        System.out.println("Choose your action: ");
    }
}
