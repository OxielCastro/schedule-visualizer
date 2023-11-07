package edu.hanover.schedulevisualizer.core;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstructorTest {
    @Test
    public void instructorsHaveFullName() {
        Instructor instructor1 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        Instructor instructor2 = new Instructor("Bradley","Burdick", "burdick@hanover.edu");
        assertThat(instructor2.getFullName(), equalTo(instructor1.getFullName()));
    }
}
