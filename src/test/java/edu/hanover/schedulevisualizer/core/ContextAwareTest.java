package edu.hanover.schedulevisualizer.core;

import edu.hanover.schedulevisualizer.core.entity.EntityFactory;

public class ContextAwareTest {
    protected TestableContext context = new TestableContext();
    protected EntityFactory ef = context.ef;
}
