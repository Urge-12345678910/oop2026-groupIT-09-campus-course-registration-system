package edu.aitu.oop3.entity;

public class Course {
    private Long id;
    private String title;
    private int capacity;
    private String schedule;
    public Course(Long id,String title, int capacity,String schedule){
        this.id=id;
        this.title=title;
        this.capacity=capacity;
        this.schedule=schedule;
    }
    public Long getid() {
        return id;
    }
    public String getTitle(){
        return title;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getSchedule() {
        return schedule;
    }

}
