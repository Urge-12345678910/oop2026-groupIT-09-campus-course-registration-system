package edu.aitu.oop3.repository.jdbc;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entity.Course;
import edu.aitu.oop3.repository.CourseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseRepository implements CourseRepository {

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses (title, capacity, schedule) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getTitle());
            stmt.setInt(2, course.getCapacity());
            stmt.setString(3, course.getSchedule());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findById(Long id) {
        String sql = "SELECT id, title, capacity, schedule, type FROM courses WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return CourseFactory.create(
                        rs.getString("type"),
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getInt("capacity"),
                        rs.getString("schedule")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Course> findAll() {
        String sql = "SELECT id, title, capacity, schedule, type FROM courses";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courses.add(
                        CourseFactory.create(
                                rs.getString("type"),
                                rs.getLong("id"),
                                rs.getString("title"),
                                rs.getInt("capacity"),
                                rs.getString("schedule")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courses;
    }


    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM courses WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}