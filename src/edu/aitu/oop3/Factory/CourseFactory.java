package edu.aitu.oop3.Factory;
import edu.aitu.oop3.entity.Course;

public class CourseFactory {

    public static Course create(
            String type,
            Long id,
            String title,
            int capacity,
            String schedule
    ) {
        return switch (type) {
            case "LAB" -> new LabCourse(id, title, capacity, schedule);
            case "ONLINE" -> new OnlineCourse(id, title, capacity, schedule);
            case "LECTURE" -> new LectureCourse(id, title, capacity, schedule);
            default -> throw new IllegalArgumentException("Unknown course type: " + type);
        };
    }
}
