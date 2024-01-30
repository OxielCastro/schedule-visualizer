package edu.hanover.schedulevisualizer.core;

import java.util.List;

public enum Weekday {
    Monday("M"),
    Tuesday("T"),
    Wednesday("W"),
    Thursday("R"),
    Friday("F");

    private final String abbr;

    Weekday(final String abbr) {
        this.abbr = abbr;
    }

    public static List<Weekday> MWF() {
        return List.of(Monday, Wednesday, Friday);
    }

    public static List<Weekday> MW() {
        return List.of(Monday, Wednesday);
    }

    public static List<Weekday> TR() {
        return List.of(Tuesday, Thursday);
    }

    public String toShortString() {
        return this.abbr;
    }

    public boolean isTR() {
        return this == Tuesday || this == Thursday;
    }
}
