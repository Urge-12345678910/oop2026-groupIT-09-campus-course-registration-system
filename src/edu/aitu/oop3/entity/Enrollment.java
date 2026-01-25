package edu.aitu.oop3.entity;

public class Enrollment {

    private Long id;
    private Long studentId;
    private Long courseId;

    protected Enrollment(Long id, Long studentId, Long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }
    public Long getId() {
        return id;
    }

    public  Long getStudentId() {
        return studentId;
    }

    public  Long getCourseId() {
        return courseId;
    }

}
