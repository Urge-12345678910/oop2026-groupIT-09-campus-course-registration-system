package edu.aitu.oop3.ui;

import edu.aitu.oop3.components.registration.*;
import edu.aitu.oop3.components.user_management.JdbcStudentRepository;
import edu.aitu.oop3.components.user_management.Student;
import edu.aitu.oop3.components.user_management.StudentService;
import edu.aitu.oop3.components.reporting.StudentSchedule;

import java.util.Comparator;
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
            System.out.println("9. View student schedule");
            System.out.println("10. View courses sorted by capacity");
            System.out.println("11. View courses with capacity > N");
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

                        Course course = CourseFactory.create(
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
                                System.out.println(
                                        c.getid() + " | " + c.getTitle()
                                                + " | " + c.getCapacity()
                                                + " | " + c.getSchedule()
                                                + " | " + c.getType()
                                )
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
                    case 9 -> {
                        System.out.print("Student ID: ");
                        Long studentId = scanner.nextLong();

                        StudentSchedule schedule =
                                registrationService.getStudentSchedule(studentId);

                        System.out.println("Schedule for student " + studentId + ":");

                        schedule.getCourses().forEach(c ->
                                System.out.println(
                                        c.getTitle() + " | " +
                                                c.getSchedule() + " | " +
                                                c.getType()
                                )
                        );
                    }
                    case 10 -> {
                        List<Course> courses = courseRepo.findAll();

                        courses.stream()
                                .sorted(Comparator.comparingInt(Course::getCapacity))
                                .forEach(c ->
                                        System.out.println(
                                                c.getid() + " | " +
                                                        c.getTitle() + " | " +
                                                        c.getCapacity()
                                        )
                                );
                    }
                    case 11 -> {
                        System.out.print("Minimum capacity: ");
                        int min = scanner.nextInt();
                        scanner.nextLine();

                        List<Course> courses = courseRepo.findAll();

                        courses.stream()
                                .filter(c -> c.getCapacity() > min)
                                .forEach(c ->
                                        System.out.println(
                                                c.getid() + " | " +
                                                        c.getTitle() + " | " +
                                                        c.getCapacity()
                                        )
                                );
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