package com.system.College;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker{

    private static Scanner scanner=new Scanner(System.in);
    private static ArrayList<String> studentNames=new ArrayList<>();
    private static ArrayList<Double>studentGrades=new ArrayList<>();

    public static void main(String[] args) {
        while (true){
            System.out.println("\n=== STUDENT GRADE TRACKER ===");
            System.out.println("1. Add Student Grade");
            System.out.println("2. View All Grades");
            System.out.println("3. Show Summary Report");
            System.out.println("4. Exit");
            System.out.print("Choose option:");
            int choice = scanner.nextInt();
            scanner .nextLine();

            switch (choice){
                case 1:
                    addStudentGrade();
                    break;
                case 2:
                    viewAllGrades();
                    break;
                case 3:
                    summaryReport();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }

    private static void addStudentGrade(){

        System.out.println("Enter Student Name:");
        String name=scanner.nextLine();

        System.out.println("Enter Grade (0-100):");
        double grade=scanner.nextDouble();

        studentNames.add(name);
        studentGrades.add(grade);

        System.out.println("Student added successfully!");
    }
    private  static void viewAllGrades(){
        System.out.println("\n---All Student grades----");

        if(studentNames.isEmpty()){
            System.out.println("No student data available");
            return;
        }
        for (int i=0;i<studentNames.size();i++){
            System.out.println((i+1)+". "+studentNames.get(i)+" - "+studentGrades.get(i));
        }
    }
    private static void summaryReport(){
        if (studentGrades.isEmpty()){
            System.out.println("No data to calculate summary.");
            return;
        }
        double total =0;
        double highest=studentGrades.get(0);
        double lowest=studentGrades.get(0);

        for (double grade:studentGrades){
            total +=grade;
            if(grade>highest) highest=grade;
            if(grade<lowest)lowest=grade;
        }
        double average=total/studentGrades.size();

        average = total / studentGrades.size();

        System.out.println("\n--- SUMMARY REPORT ---");
        System.out.println("Total Students: " + studentGrades.size());
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);

    }
}



