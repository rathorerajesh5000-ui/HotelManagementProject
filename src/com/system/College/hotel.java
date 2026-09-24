package com.system.College;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

// -------------------- ROOM CLASS --------------------
class Room implements Serializable {

    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        this.available = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}

// -------------------- RESERVATION CLASS --------------------
class Reservation implements Serializable {

    private String reservationId;
    private String customerName;
    private int roomNumber;
    private String category;
    private double amountPaid;
    private String checkIn;
    private String checkOut;

    public Reservation(String reservationId, String customerName,
                       int roomNumber, String category,
                       double amountPaid, String checkIn, String checkOut) {

        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.amountPaid = amountPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getReservationId() {
        return reservationId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "\nReservation Details:\n" +
                "Reservation ID = " + reservationId +
                "\nCustomer = " + customerName +
                "\nRoom No = " + roomNumber +
                "\nCategory = " + category +
                "\nAmount = " + amountPaid +
                "\nCheck-In = " + checkIn +
                "\nCheck-Out = " + checkOut + "\n";
    }
}

// -------------------- HOTEL CLASS --------------------
class Hotel {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public Hotel() {
        loadData();
        if (rooms.isEmpty()) {
            setupDefaultRooms();
            saveData();
        }
    }

    private void setupDefaultRooms() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4500));
    }

    public void searchRooms() {
        System.out.println("\n--- AVAILABLE ROOMS ---");
        for (Room r : rooms) {
            if (r.isAvailable())
                System.out.println(r);
        }
    }

    public void bookRoom(String customerName, String category, String checkIn, String checkOut) {

        for (Room r : rooms) {   // <-- FIXED here
            if (r.isAvailable() && r.getCategory().equalsIgnoreCase(category)) {

                r.setAvailable(false);

                String id = "RES" + (reservations.size() + 1);
                Reservation res = new Reservation(id, customerName,
                        r.getRoomNumber(), category, r.getPrice(),
                        checkIn, checkOut);

                reservations.add(res);
                saveData();

                System.out.println("\nRoom booked successfully!");
                System.out.println(res);
                return;
            }
        }
        System.out.println("No available rooms in this category!");
    }

    public void cancelReservation(String reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId().equals(reservationId)) {

                for (Room r : rooms) {
                    if (r.getRoomNumber() == res.getRoomNumber()) {
                        r.setAvailable(true);
                        break;
                    }
                }

                reservations.remove(res);
                saveData();

                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }
        System.out.println("Reservation ID not found!");
    }

    public void viewReservation(String reservationId) {
        for (Reservation r : reservations) {
            if (r.getReservationId().equals(reservationId)) {
                System.out.println(r);
                return;
            }
        }
        System.out.println("No reservation found!");
    }

    private void saveData() {
        try {
            ObjectOutputStream o1 = new ObjectOutputStream(new FileOutputStream("rooms.dat"));
            o1.writeObject(rooms);
            o1.close();

            ObjectOutputStream o2 = new ObjectOutputStream(new FileOutputStream("reservations.dat"));
            o2.writeObject(reservations);
            o2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            ObjectInputStream i1 = new ObjectInputStream(new FileInputStream("rooms.dat"));
            rooms = (ArrayList<Room>) i1.readObject();
            i1.close();

            ObjectInputStream i2 = new ObjectInputStream(new FileInputStream("reservations.dat"));
            reservations = (ArrayList<Reservation>) i2.readObject();
            i2.close();

        } catch (Exception ignored) { }
    }
}



class Main {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    hotel.searchRooms();
                    break;

                case 2:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Room Category (Standard/Deluxe/Suite): ");
                    String cat = sc.nextLine();
                    System.out.print("Check-In date: ");
                    String in = sc.nextLine();
                    System.out.print("Check-Out date: ");
                    String out = sc.nextLine();

                    hotel.bookRoom(name, cat, in, out);
                    break;

                case 3:
                    System.out.print("Enter Reservation ID: ");
                    String rid = sc.nextLine();
                    hotel.cancelReservation(rid);
                    break;

                case 4:
                    System.out.print("Enter Reservation ID: ");
                    String id = sc.nextLine();
                    hotel.viewReservation(id);  // FIXED
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
