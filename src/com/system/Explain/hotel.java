package com.system.Explain;

import java.util.ArrayList;
import java.util.Scanner;

 class Hotel {

    //================ Customer Class ==================
    static class Customer {
        private String customerId;
        private String name;
        private String phone;
        private String address;
        private String roomType;

        public Customer(String customerId, String name, String phone, String address, String roomType) {
            this.customerId = customerId;
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.roomType = roomType;
        }

        void displayCustomer() {
            System.out.println("Customer ID : " + customerId);
            System.out.println("Name : " + name);
            System.out.println("Phone : " + phone);
            System.out.println("Address : " + address);
            System.out.println("Room Type : " + roomType);
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }
    }

    //================ Variables ==================
    private static Scanner INPUT_SCANNER = new Scanner(System.in);
    private static ArrayList<Customer> customers = new ArrayList<>();


    //================ Main Method ==================
    public static void main(String[] args) {

        int selectedOption;

        do {
            availableMenus();
            System.out.println("Choose your option:");
            selectedOption = INPUT_SCANNER.nextInt();

            switch (selectedOption) {
                case 1:
                    customerMenu();
                    break;

                case 5:
                    System.out.println("Thank you for using the Hotel System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }

        } while (selectedOption != 5);
    }


    //================ Main Menu ==================
    private static void availableMenus() {
        System.out.println("\n============= HOTEL MANAGEMENT SYSTEM ===========");
        System.out.println("1. Customer Management");
        System.out.println("2. Room Management");
        System.out.println("3. Booking Reservation");
        System.out.println("4. Billing");
        System.out.println("5. Exit");
    }


    //================ Customer Menu ==================
    private static void customerMenu() {

        int option;

        do {
            System.out.println("\n========= Customer Management ==========");
            System.out.println("1. Add New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Update Customer Information");
            System.out.println("5. Delete Customer");
            System.out.println("6. Back to Main Menu");

            System.out.println("Enter choice:");
            option = INPUT_SCANNER.nextInt();

            switch (option) {
                case 1:
                    addNewCustomer();
                    break;

                case 2:
                    viewAllCustomers();
                    break;

                case 3:
                    searchCustomerByID();
                    break;

                case 4:
                    updateCustomer();
                    break;

                case 5:
                    deleteCustomer();
                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (option != 6);
    }


    //================ Add Customer ==================
    private static void addNewCustomer() {

        INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer ID:");
        String id = INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer Name:");
        String name = INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer Phone:");
        String phone = INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer Address:");
        String address = INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer Room Type:");
        String roomType = INPUT_SCANNER.nextLine();

        Customer customer = new Customer(id, name, phone, address, roomType);
        customers.add(customer);

        System.out.println("Customer Added Successfully!");
    }


    //================ View Customers ==================
    private static void viewAllCustomers() {

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer c : customers) {
            c.displayCustomer();
            System.out.println("----------------------");
        }
    }


    //================ Search Customer ==================
    private static void searchCustomerByID() {

        INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer ID to search:");
        String searchId = INPUT_SCANNER.nextLine();

        boolean found = false;

        for (Customer customer : customers) {

            if (customer.getCustomerId().equalsIgnoreCase(searchId)) {
                System.out.println("\nCustomer Found:");
                customer.displayCustomer();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Customer with ID " + searchId + " not found.");
        }
    }


    //================ Update Customer ==================
    private static void updateCustomer() {

        INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer ID to update:");
        String id = INPUT_SCANNER.nextLine();

        boolean found = false;

        for (Customer customer : customers) {

            if (customer.getCustomerId().equalsIgnoreCase(id)) {

                System.out.println("Customer Found. Enter new details:");

                System.out.println("Enter New Name:");
                String name = INPUT_SCANNER.nextLine();

                System.out.println("Enter New Phone:");
                String phone = INPUT_SCANNER.nextLine();

                System.out.println("Enter New Address:");
                String address = INPUT_SCANNER.nextLine();

                System.out.println("Enter New Room Type:");
                String roomType = INPUT_SCANNER.nextLine();

                customer.setName(name);
                customer.setPhone(phone);
                customer.setAddress(address);
                customer.setRoomType(roomType);

                System.out.println("Customer Updated Successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }


    //================ Delete Customer ==================
    private static void deleteCustomer() {

        INPUT_SCANNER.nextLine();

        System.out.println("Enter Customer ID to delete:");
        String id = INPUT_SCANNER.nextLine();

        boolean found = false;

        for (int i = 0; i < customers.size(); i++) {

            if (customers.get(i).getCustomerId().equalsIgnoreCase(id)) {

                customers.remove(i);
                System.out.println("Customer Deleted Successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }
}