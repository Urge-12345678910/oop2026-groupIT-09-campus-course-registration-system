package edu.aitu.oop3.repository;
import edu.aitu.oop3.entity.Course;
import java.util.List;
public interface CourseRepository {
    void deleteByID(int id);
    Course FindById(int id);
    List<Course> findAll();
}
