package edu.aitu.oop3.components.registration;

public class LectureCourse extends Course {

    public LectureCourse(Long id, String title, int capacity, String schedule) {
        super(id, title, capacity, schedule);
    }

    @Override
    public String getType() {
        return "LECTURE";
    }
}