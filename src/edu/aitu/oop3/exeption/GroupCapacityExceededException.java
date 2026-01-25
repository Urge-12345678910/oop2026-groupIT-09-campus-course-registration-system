package edu.aitu.oop3.exeption;

public class GroupCapacityExceededException extends RuntimeException {
    public GroupCapacityExceededException(String message) {
        super(message);
    }
}
