package edu.aitu.oop3.repository;

import edu.aitu.oop3.entity.Student;
import java.util.List;

public interface StudentRepository {
    Void save(Student student);
    Student findById(Long id);
    List<Student> findAll();
}
