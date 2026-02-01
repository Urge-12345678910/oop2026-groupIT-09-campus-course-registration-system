package edu.aitu.oop3.entity;

import java.util.List;

public class StudentSchedule {

    private final Long studentId;
    private final List<Course> courses;

    private StudentSchedule(Long studentId, List<Course> courses) {
        this.studentId = studentId;
        this.courses = courses;
    }

    public Long getStudentId() {
        return studentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // ===== BUILDER =====
    public static class Builder {

        private Long studentId;
        private List<Course> courses;

        public Builder studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder courses(List<Course> courses) {
            this.courses = courses;
            return this;
        }

        public StudentSchedule build() {
            return new StudentSchedule(studentId, courses);
        }
    }
}
