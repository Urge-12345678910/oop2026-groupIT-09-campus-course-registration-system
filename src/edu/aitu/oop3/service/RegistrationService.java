package edu.aitu.oop3.service;

import edu.aitu.oop3.entity.Course;
import edu.aitu.oop3.entity.Enrollment;
import edu.aitu.oop3.exeption.GroupCapacityExceededException;
import edu.aitu.oop3.exeption.TimeConflictException;
import edu.aitu.oop3.repository.CourseRepository;
import edu.aitu.oop3.repository.EnrollmentRepository;
import edu.aitu.oop3.entity.StudentSchedule;
import java.util.List;

public class RegistrationService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public RegistrationService(CourseRepository courseRepository,
                               EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void register(Long studentId, Long courseId) {

        Course course = courseRepository.findById(courseId);
        List<Enrollment> enrollments =
                enrollmentRepository.findByCourseId(courseId);

        if (enrollments.size() >= course.getCapacity()) {
            throw new GroupCapacityExceededException(
                    "Course capacity exceeded"
            );
        }

        List<Enrollment> studentEnrollments =
                enrollmentRepository.findByStudentId(studentId);

        for (Enrollment e : studentEnrollments) {
            Course enrolledCourse =
                    courseRepository.findById(e.getCourseId());

            if (enrolledCourse.getSchedule()
                    .equals(course.getSchedule())) {
                throw new TimeConflictException(
                        "Time conflict with another course"
                );
            }
        }

        enrollmentRepository.enroll(studentId, courseId);
    }
    public StudentSchedule getStudentSchedule(Long studentId) {

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(studentId);

        List<Course> courses = enrollments.stream()
                .map(e -> courseRepository.findById(e.getCourseId()))
                .toList();

        return new StudentSchedule.Builder()
                .studentId(studentId)
                .courses(courses)
                .build();
    }

    public void drop(Long studentId, Long courseId) {
        enrollmentRepository.remove(studentId, courseId);
    }
}