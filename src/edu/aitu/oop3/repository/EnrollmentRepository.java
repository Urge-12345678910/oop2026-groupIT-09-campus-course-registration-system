package edu.aitu.oop3.repository;

import edu.aitu.oop3.entity.Enrollment;
import java.util.List;

public interface EnrollmentRepository {

    void enroll(int studentId, int courseId);

    void remove(int studentId, int courseId);

    List<Enrollment> findByStudentId(int studentId);

    List<Enrollment> findByCourseId(int courseId);
}