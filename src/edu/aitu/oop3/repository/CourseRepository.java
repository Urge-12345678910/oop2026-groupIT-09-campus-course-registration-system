package edu.aitu.oop3.repository;
import edu.aitu.oop3.entity.Course;
import java.util.List;
public interface CourseRepository {
    void deleteByID(Long id);
    Course FindById(Long id);
    List<Course> findAll();
}
