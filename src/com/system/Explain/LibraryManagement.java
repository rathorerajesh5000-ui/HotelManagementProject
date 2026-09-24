package com.system.Explain;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagement {

    //================ Book Class =================
    static class Book {
        int id;
        String name;
        boolean isIssued;

        Book(int id, String name) {
            this.id = id;
            this.name = name;
            this.isIssued = false;
        }
    }

    //================ Member Class =================
    static class Member {
        int memberId;
        String memberName;

        Member(int memberId, String memberName) {
            this.memberId = memberId;
            this.memberName = memberName;
        }
    }

    //================ Variables =================
    private static Scanner INPUT_SCANNER = new Scanner(System.in);
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Member> members = new ArrayList<>();


    //================ Main Method =================
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Member");
            System.out.println("5. View Members");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = INPUT_SCANNER.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBook();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    addMember();
                    break;

                case 5:
                    viewMember();
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }


    //================ Add Book =================
    static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = INPUT_SCANNER.nextInt();
        INPUT_SCANNER.nextLine();

        System.out.print("Enter Book Name: ");
        String name = INPUT_SCANNER.nextLine();

        books.add(new Book(id, name));

        System.out.println("Book Added Successfully!");
    }


    //================ View Books =================
    static void viewBook() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : books) {
            System.out.println(b.id + " | " + b.name + " | Issued: " + b.isIssued);
        }
    }


    //================ Search Book =================
    static void searchBook() {

        System.out.print("Enter Book ID to search: ");
        int id = INPUT_SCANNER.nextInt();

        for (Book b : books) {

            if (b.id == id) {
                System.out.println("Book Found: " + b.name);
                return;
            }
        }

        System.out.println("Book not found!");
    }


    //================ Add Member =================
    static void addMember() {

        System.out.print("Enter Member ID: ");
        int id = INPUT_SCANNER.nextInt();
        INPUT_SCANNER.nextLine();

        System.out.print("Enter Member Name: ");
        String name = INPUT_SCANNER.nextLine();

        members.add(new Member(id, name));

        System.out.println("Member Added Successfully!");
    }


    //================ View Members =================
    static void viewMember() {

        if (members.isEmpty()) {
            System.out.println("No members available.");
            return;
        }

        for (Member m : members) {
            System.out.println(m.memberId + " | " + m.memberName);
        }
    }
}