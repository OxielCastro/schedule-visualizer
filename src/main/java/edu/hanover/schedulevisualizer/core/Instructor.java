package edu.hanover.schedulevisualizer.core;

public class Instructor {
    private final String first;
    private final String last;
    private final String Id;

    public Instructor(String first, String last, String Id) {
        this.first = first;
        this.last = last;
        this.Id = Id;
    }
    public String getFullName() {
        String fullName = first + " " + last;
        return fullName;
    }
}
