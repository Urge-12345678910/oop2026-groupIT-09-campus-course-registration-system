package edu.aitu.oop3.entity;

public abstract class Course {
    protected Long id;
    protected String title;
    protected int capacity;
    protected String schedule;
    public Course(Long id,String title, int capacity,String schedule){
        this.id=id;
        this.title=title;
        this.capacity=capacity;
        this.schedule=schedule;
    }
    public abstract String getType();
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
