package com.dinesh.sms;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {
 private static final Scanner in=new Scanner(System.in);
 private static final StudentDAO s=new StudentDAO();
 private static final AcademicDAO a=new AcademicDAO();
 public static void main(String[] args){
  try(Connection c=DBConnection.getConnection()){System.out.println("Database connected.");}
  catch(SQLException e){System.out.println("Connection failed: "+e.getMessage());return;}
  while(true){
   System.out.println("\n=== STUDENT MANAGEMENT ===");
   System.out.println("1 Register student  2 List students  3 Find student");
   System.out.println("4 Update student    5 Delete student 6 Add course");
   System.out.println("7 List courses      8 Enroll student 9 Mark attendance");
   System.out.println("10 Attendance report 11 Enter marks 12 Generate result 0 Exit");
   int choice=integer("Choice: ");
   try{
    switch(choice){
     case 1->s.add(text("Name: "),text("Email: "),text("Phone: "),integer("Age: "));
     case 2->s.list();
     case 3->s.find(integer("Student ID: "));
     case 4->s.update(integer("Student ID: "),text("New name: "),text("New email: "),text("New phone: "),integer("New age: "));
     case 5->s.delete(integer("Student ID: "));
     case 6->a.addCourse(text("Course name: "),integer("Duration months: "));
     case 7->a.courses();
     case 8->a.enroll(integer("Student ID: "),integer("Course ID: "));
     case 9->a.attendance(integer("Student ID: "),integer("Course ID: "),LocalDate.parse(text("Date YYYY-MM-DD: ")),text("PRESENT/ABSENT: "));
     case 10->a.attendanceReport(integer("Student ID: "));
     case 11->a.marks(integer("Student ID: "),integer("Course ID: "),text("Exam name: "),decimal("Marks obtained: "),decimal("Maximum marks: "));
     case 12->a.result(integer("Student ID: "));
     case 0->{System.out.println("Bye!");return;}
     default->System.out.println("Invalid option.");
    }
   }catch(Exception e){System.out.println("Operation failed: "+e.getMessage());}
  }
 }
 private static String text(String label){System.out.print(label);return in.nextLine().trim();}
 private static int integer(String label){while(true)try{return Integer.parseInt(text(label));}catch(NumberFormatException e){System.out.println("Enter a valid integer.");}}
 private static double decimal(String label){while(true)try{return Double.parseDouble(text(label));}catch(NumberFormatException e){System.out.println("Enter a valid number.");}}
}
