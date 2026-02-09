package edu.aitu.oop3.components.registration;

public class OnlineCourse extends Course {

    public OnlineCourse(Long id, String title, int capacity, String schedule) {
        super(id, title, capacity, schedule);
    }

    @Override
    public String getType() {
        return "ONLINE";
    }
}