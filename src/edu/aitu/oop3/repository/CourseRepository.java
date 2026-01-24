package edu.aitu.oop3.repository;
import edu.aitu.oop3.entity.Course;
import java.util.List;
public interface CourseRepository {
    void save(Course course);
    void deleteById(Long id);
    Course findById(Long id);
    List<Course> findAll();
}
