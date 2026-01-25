package edu.aitu.oop3.service;
import edu.aitu.oop3.exeption.CourseHasActiveEnrollmentsException;
import edu.aitu.oop3.repository.CourseRepository;
import edu.aitu.oop3.repository.EnrollmentRepository;
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void deleteCourse(Long courseId) {
        if (!enrollmentRepository.findByCourseId(courseId).isEmpty()) {
            throw new CourseHasActiveEnrollmentsException(
                    "Cannot delete course with active enrollments"
            );
        }
        courseRepository.deleteById(courseId);
    }
}