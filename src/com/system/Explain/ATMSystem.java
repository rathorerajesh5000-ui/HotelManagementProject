package com.system.Explain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Customer{
    private int customerID;
    private String userID;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String accountNumber;
    private double balanceAmount;
    private String password;


    public Customer(int customerID, String userID, String firstName, String lastName,
                    String mobileNo, String accountNumber, double balanceAmount, String password) {
        this.customerID = customerID;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.accountNumber = accountNumber;
        this.balanceAmount = balanceAmount;
        this.password = password;
    }
    public double getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(double balanceAmount) { this.balanceAmount = balanceAmount; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

public class ATMSystem {

    private static final Scanner INPUT_SCANNER=new Scanner(System.in);
    private static final Random rand=new Random();
    private static final  double MINIMUM_AMOUNT=100.0d;

    private final static Map<String,Customer> customers=new LinkedHashMap<>();

    static {
        final var put = customers.put("Ajay_12345", new Customer(generateUserId(), "Ajay_12345", "Ajay", "Rathore",
                generateMobileNo(true), "Acc_Ajay_12345", generateBalance(), "123456"));

        customers.put("Rajesh_12345", new Customer(generateUserId(), "Rajesh_12345", "Rajesh", "Rathore",
                generateMobileNo(true), "Acc_Rajesh_12345", generateBalance(), "234567"));
    }


    public static void main(String[] args) {
        while (true){
            availableMenus();
            System.out.println("Choose your option:");
            int selectedOption=INPUT_SCANNER.nextInt();

            if (selectedOption==6){
                System.out.println("Thank you for using the ATM,Goodbye!");
                break;
            }
            PerformOperations(selectedOption);
            System.out.println();
        }
    }
    private static void PerformOperations(int selectedOption){
        switch (selectedOption) {
            case 1 -> checkBalance();
            case 2 -> depositMoney();
            case 3 -> withdrawMoney();
            case 4 -> changePIN();
            case 5 -> addAccount();
            default -> System.out.println("Invalid Option!");
        }
    }
    private static Customer validateUser(String userId){
        if (!customers.containsKey(userId)) {
            System.out.println("User not found: " + userId);
            return null;
        }
        Customer customer = customers.get(userId);

        System.out.print("Enter your PIN: ");
        String pin = INPUT_SCANNER.next();

        if (!customer.getPassword().equals(pin)) {
            System.out.println("Incorrect PIN!");
            return null;
        }
        return customer;
    }

    private static void checkBalance(){
        System.out.println("Enter your User ID:");
        Customer customer = validateUser(INPUT_SCANNER.next());
        if (customer == null) return;

        System.out.println("Your Balance: ₹" + customer.getBalanceAmount());
    }
    private static void depositMoney(){
        System.out.print("Enter your User ID: ");
        Customer customer = validateUser(INPUT_SCANNER.next());
        if (customer == null) return;

        System.out.print("Enter amount to deposit: ");
        double amount = INPUT_SCANNER.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }
        customer.setBalanceAmount(customer.getBalanceAmount() + amount);

        System.out.println("Deposit Successful!");
        System.out.println("Updated Balance: ₹" + customer.getBalanceAmount());
    }

    private static void withdrawMoney(){
        System.out.print("Enter your User ID: ");
        Customer customer = validateUser(INPUT_SCANNER.next());
        if (customer == null) return;

        System.out.print("Enter amount to withdraw: ");
        double amount = INPUT_SCANNER.nextDouble();

        if (amount < MINIMUM_AMOUNT) {
            System.out.println("Minimum withdrawal amount: ₹" + MINIMUM_AMOUNT);
            return;
        }

        if (amount > customer.getBalanceAmount()) {
            System.out.println("Insufficient balance!");
            return;
        }
        customer.setBalanceAmount(customer.getBalanceAmount() - amount);

        System.out.println("Withdrawal Successful!");
        System.out.println("Remaining Balance: ₹" + customer.getBalanceAmount());
    }

    private static void changePIN(){
        System.out.print("Enter your User ID: ");
        Customer customer = validateUser(INPUT_SCANNER.next());
        if (customer == null) return;

        System.out.print("Enter new 6-digit PIN: ");
        String newPin = INPUT_SCANNER.next();

        if (!newPin.matches("\\d{6}")) {
            System.out.println("PIN must be 6 digits!");
            return;
        }

        customer.setPassword(newPin);
        System.out.println("PIN Updated Successfully!");
    }
    private static void addAccount(){
        System.out.println("Enter First Name: ");
        String firstName = INPUT_SCANNER.next();

        System.out.println("Enter Last Name: ");
        String lastName = INPUT_SCANNER.next();

        System.out.println("Enter Mobile No: ");
        String mobileNo = INPUT_SCANNER.next();

        String userId = firstName + "_12345";
        String password = "123456";

        customers.putIfAbsent(userId,
                new Customer(generateUserId(), userId, firstName, lastName,
                        mobileNo, "Acc_" + firstName + "_12345", 1000.0, password));

        System.out.println("Account Created Successfully!");
        System.out.println("User ID: " + userId);
        System.out.println("PIN: " + password);
    }
    private static int generateUserId(){
        return 100+rand.nextInt(900);
    }
    private static int generateBalance(){
        return 5000+rand.nextInt(20000);
    }
    private static String generateMobileNo(boolean indian){
        StringBuilder sb = new StringBuilder();
        if (indian) sb.append(6 + rand.nextInt(4));
        while (sb.length() < 10) sb.append(rand.nextInt(10));
        return sb.toString();
    }

    private static void availableMenus(){
        System.out.println("===== ATM OPTIONS =====");
        System.out.println("1: Check Balance");
        System.out.println("2: Deposit Money");
        System.out.println("3: Withdraw Money");
        System.out.println("4: Change PIN");
        System.out.println("5: Add Account");
        System.out.println("6: Exit");
    }
}







