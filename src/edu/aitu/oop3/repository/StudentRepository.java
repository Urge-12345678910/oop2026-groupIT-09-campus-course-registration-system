package edu.aitu.oop3.repository;

import edu.aitu.oop3.entity.Student;
import java.util.List;

public interface StudentRepository {
    void save(Student student);
    Student findById(Long id);
    List<Student> findAll();
}
//by ehsanullah alimy