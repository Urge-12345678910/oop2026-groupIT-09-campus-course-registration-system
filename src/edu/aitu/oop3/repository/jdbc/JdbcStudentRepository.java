package edu.aitu.oop3.repository.jdbc;
import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entity.Student;
import edu.aitu.oop3.repository.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT id, name, email FROM students WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT id, name, email FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }
}
