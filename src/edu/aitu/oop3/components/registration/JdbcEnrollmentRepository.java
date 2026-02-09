package edu.aitu.oop3.components.registration;
import edu.aitu.oop3.components.common.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEnrollmentRepository implements EnrollmentRepository {

    @Override
    public void enroll(Long studentId, Long courseId) {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, studentId);
            stmt.setLong(2, courseId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long studentId, Long courseId) {
        String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, studentId);
            stmt.setLong(2, courseId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Enrollment> findByStudentId(Long studentId) {
        String sql = "SELECT id, student_id, course_id FROM enrollments WHERE student_id = ?";
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getLong("id"),
                        rs.getLong("student_id"),
                        rs.getLong("course_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return enrollments;
    }

    @Override
    public List<Enrollment> findByCourseId(Long courseId) {
        String sql = "SELECT id, student_id, course_id FROM enrollments WHERE course_id = ?";
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getLong("id"),
                        rs.getLong("student_id"),
                        rs.getLong("course_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return enrollments;
    }
}

