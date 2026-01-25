package edu.aitu.oop3.service;

import edu.aitu.oop3.entity.Student;
import edu.aitu.oop3.repository.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(String name, String email) {
        studentRepository.save(new Student(null, name, email));
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id);
    }
}