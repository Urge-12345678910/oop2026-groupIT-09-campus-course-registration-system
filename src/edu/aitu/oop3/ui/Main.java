package edu.aitu.oop3.ui;

import edu.aitu.oop3.entity.Course;
import edu.aitu.oop3.entity.Enrollment;
import edu.aitu.oop3.entity.Student;
import edu.aitu.oop3.exeption.*;
import edu.aitu.oop3.repository.jdbc.*;
import edu.aitu.oop3.service.*;
import edu.aitu.oop3.Factory.CourseFactory;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        var studentRepo = new JdbcStudentRepository();
        var courseRepo = new JdbcCourseRepository();
        var enrollmentRepo = new JdbcEnrollmentRepository();

        var studentService = new StudentService(studentRepo);
        var registrationService = new RegistrationService(courseRepo, enrollmentRepo);
        var courseService = new CourseService(courseRepo, enrollmentRepo);

        while (true) {
            System.out.println("\n=== Campus Course Registration System ===");
            System.out.println("1. Add student");
            System.out.println("2. Add course");
            System.out.println("3. View all students");
            System.out.println("4. View all courses");
            System.out.println("5. Register student to course");
            System.out.println("6. Drop student from course");
            System.out.println("7. View students enrolled in a course");
            System.out.println("8. Delete course");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {

                    case 1 -> {
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        studentService.addStudent(name, email);
                        System.out.println("Student added.");
                    }

                    case 2 -> {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Capacity: ");
                        int capacity = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Schedule: ");
                        String schedule = scanner.nextLine();

                        System.out.print("Course type (LECTURE / LAB / ONLINE): ");
                        String type = scanner.nextLine();

                        Course course = CourseFactory.createCourse(
                                type,
                                null,
                                title,
                                capacity,
                                schedule
                        );

                        courseRepo.save(course);
                        System.out.println("Course added.");
                    }

                    case 3 -> {
                        List<Student> students = studentService.findAllStudents();
                        for (Student s : students) {
                            System.out.println(
                                    s.getId() + " | " + s.getName() + " | " + s.getEmail()
                            );
                        }
                    }
                    case 4 -> {
                        List<Course> courses = courseRepo.findAll();
                        courses.forEach(c ->
                                System.out.println(c.getid() + " | " + c.getTitle()
                                        + " | " + c.getCapacity()
                                        + " | " + c.getSchedule())
                        );
                    }

                    case 5 -> {
                        System.out.print("Student ID: ");
                        Long studentId = scanner.nextLong();
                        System.out.print("Course ID: ");
                        Long courseId = scanner.nextLong();

                        registrationService.register(studentId, courseId);
                        System.out.println("Student registered successfully.");
                    }
                    case 6 -> {
                        System.out.print("Student ID: ");
                        Long studentId = scanner.nextLong();
                        System.out.print("Course ID: ");
                        Long courseId = scanner.nextLong();

                        registrationService.drop(studentId, courseId);
                        System.out.println("Enrollment removed.");
                    }

                    case 7 -> {
                        System.out.print("Course ID: ");
                        Long courseId = scanner.nextLong();

                        List<Enrollment> enrollments =
                                enrollmentRepo.findByCourseId(courseId);

                        for (Enrollment e : enrollments) {
                            Student s = studentRepo.findById(e.getStudentId());
                            System.out.println(
                                    s.getId() + " | " + s.getName() + " | " + s.getEmail()
                            );
                        }
                    }

                    case 8 -> {
                        System.out.print("Course ID: ");
                        Long courseId = scanner.nextLong();
                        courseService.deleteCourse(courseId);
                        System.out.println("Course deleted.");
                    }

                    case 0 -> {
                        System.out.println("Bye!");
                        return;
                    }

                    default -> System.out.println("Invalid option.");
                }

            } catch (GroupCapacityExceededException |
                     TimeConflictException |
                     CourseHasActiveEnrollmentsException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}