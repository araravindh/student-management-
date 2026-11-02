package com.dinesh.sms;
import java.sql.*;
import java.time.LocalDate;
public class AcademicDAO {
 public void addCourse(String name,int months) throws SQLException {
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO courses(course_name,duration_months) VALUES(?,?)")){
   p.setString(1,name);p.setInt(2,months);p.executeUpdate();System.out.println("Course added.");
  }
 }
 public void courses() throws SQLException {
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM courses");ResultSet r=p.executeQuery()){
   while(r.next())System.out.printf("%d | %s | %d months%n",r.getInt("course_id"),r.getString("course_name"),r.getInt("duration_months"));
  }
 }
 public void enroll(int sid,int cid) throws SQLException {
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO enrollments(student_id,course_id,enrollment_date) VALUES(?,?,?)")){
   p.setInt(1,sid);p.setInt(2,cid);p.setDate(3,Date.valueOf(LocalDate.now()));p.executeUpdate();System.out.println("Enrollment saved.");
  }
 }
 public void attendance(int sid,int cid,LocalDate date,String status) throws SQLException {
  if(!status.equalsIgnoreCase("PRESENT")&&!status.equalsIgnoreCase("ABSENT"))throw new IllegalArgumentException("Status must be PRESENT or ABSENT");
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO attendance(student_id,course_id,attendance_date,status) VALUES(?,?,?,?)")){
   p.setInt(1,sid);p.setInt(2,cid);p.setDate(3,Date.valueOf(date));p.setString(4,status.toUpperCase());p.executeUpdate();System.out.println("Attendance saved.");
  }
 }
 public void attendanceReport(int sid) throws SQLException {
  String q="SELECT c.course_name,a.attendance_date,a.status FROM attendance a JOIN courses c ON c.course_id=a.course_id WHERE a.student_id=? ORDER BY a.attendance_date DESC";
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,sid);try(ResultSet r=p.executeQuery()){while(r.next())System.out.printf("%s | %s | %s%n",r.getString(1),r.getDate(2),r.getString(3));}}
 }
 public void marks(int sid,int cid,String exam,double obtained,double max) throws SQLException {
  if(max<=0||obtained<0||obtained>max)throw new IllegalArgumentException("Marks must be between 0 and maximum marks.");
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO marks(student_id,course_id,exam_name,marks_obtained,max_marks) VALUES(?,?,?,?,?)")){
   p.setInt(1,sid);p.setInt(2,cid);p.setString(3,exam);p.setDouble(4,obtained);p.setDouble(5,max);p.executeUpdate();System.out.println("Marks saved.");
  }
 }
 public void result(int sid) throws SQLException {
  String q="SELECT s.student_name,c.course_name,m.exam_name,m.marks_obtained,m.max_marks,ROUND(m.marks_obtained/m.max_marks*100,2) percentage FROM marks m JOIN students s ON s.student_id=m.student_id JOIN courses c ON c.course_id=m.course_id WHERE s.student_id=?";
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,sid);try(ResultSet r=p.executeQuery()){boolean any=false;while(r.next()){any=true;System.out.printf("%s | %s | %s | %.2f/%.2f | %.2f%%%n",r.getString(1),r.getString(2),r.getString(3),r.getDouble(4),r.getDouble(5),r.getDouble(6));}if(!any)System.out.println("No marks found.");}}
 }
}
