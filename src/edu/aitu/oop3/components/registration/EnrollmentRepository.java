package edu.aitu.oop3.components.registration;

import java.util.List;

public interface EnrollmentRepository {

    void enroll(Long studentId, Long courseId);

    void remove(Long studentId, Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);
}
