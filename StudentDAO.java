package com.dinesh.sms;
import java.sql.*;
public class StudentDAO {
 public void add(String name,String email,String phone,int age) throws SQLException {
  String q="INSERT INTO students(student_name,email,phone,age) VALUES(?,?,?,?)";
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q)){
   p.setString(1,name); p.setString(2,email); p.setString(3,phone); p.setInt(4,age); p.executeUpdate();
   System.out.println("Student registered.");
  }
 }
 public void list() throws SQLException {
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT * FROM students"); ResultSet r=p.executeQuery()){
   System.out.println("ID | Name | Email | Phone | Age");
   while(r.next()) System.out.printf("%d | %s | %s | %s | %d%n",r.getInt("student_id"),r.getString("student_name"),r.getString("email"),r.getString("phone"),r.getInt("age"));
  }
 }
 public void find(int id) throws SQLException {
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT * FROM students WHERE student_id=?")){
   p.setInt(1,id); try(ResultSet r=p.executeQuery()){if(r.next()) System.out.printf("%d | %s | %s | %s | %d%n",r.getInt("student_id"),r.getString("student_name"),r.getString("email"),r.getString("phone"),r.getInt("age")); else System.out.println("Student not found.");}
  }
 }
 public void update(int id,String name,String email,String phone,int age) throws SQLException {
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("UPDATE students SET student_name=?,email=?,phone=?,age=? WHERE student_id=?")){
   p.setString(1,name);p.setString(2,email);p.setString(3,phone);p.setInt(4,age);p.setInt(5,id);
   System.out.println(p.executeUpdate()>0?"Student updated.":"Student not found.");
  }
 }
 public void delete(int id) throws SQLException {
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("DELETE FROM students WHERE student_id=?")){
   p.setInt(1,id);System.out.println(p.executeUpdate()>0?"Student deleted.":"Student not found.");
  }
 }
}
