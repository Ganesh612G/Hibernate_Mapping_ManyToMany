package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.Courses;
import com.entity.Student;

public class mainClass {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Student.class);
        cfg.addAnnotatedClass(Courses.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Please Enter Courses Data And Students");
        System.out.println("1. for Add Courses");
        System.out.println("2. for Add Students");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
               
                Courses course1 = new Courses();
                System.out.println("Enter The Course Id:");
                int id = sc.nextInt();
                course1.setC_id(id);
                System.out.println("Enter Course Name:");
                String courseName = sc.next();
                course1.setC_name(courseName);

                session.persist(course1);
                System.out.println("Course added successfully!");
                break;

            case 2:
               
                System.out.println("Enter the Student Id:");
                int sid = sc.nextInt();
                System.out.println("Enter Student Name:");
                String sname = sc.next();

                Student student = new Student();
                student.setS_id(sid);
                student.setS_name(sname);

               
                System.out.println("Enter the Course Id to associate with this student:");
                int courseId = sc.nextInt();
                Courses course = session.get(Courses.class, courseId);

                List<Courses> courses = new ArrayList<>();
                courses.add(course);

                student.setCourses(courses);
                session.persist(student);
                System.out.println("Student added successfully and associated with the course!");
                break;

            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
        }

        transaction.commit();
        session.close();
        sf.close();
        sc.close();

        System.out.println("Many-to-Many mapping using List completed.");
    }
}
