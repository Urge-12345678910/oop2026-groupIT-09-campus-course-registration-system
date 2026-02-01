package edu.aitu.oop3.entity;

public class LabCourse extends Course {

    public LabCourse(Long id, String title, int capacity, String schedule) {
        super(id, title, capacity, schedule);
    }

    @Override
    public String getType() {
        return "LAB";
    }
}