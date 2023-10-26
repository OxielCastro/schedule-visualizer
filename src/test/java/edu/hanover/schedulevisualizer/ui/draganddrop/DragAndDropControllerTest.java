package edu.hanover.schedulevisualizer.ui.draganddrop;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DragAndDropControllerTest {
    private DragAndDropController controller;
    private DropTarget target;
    private Node node;

    @Before
    public void setUp() {
        controller = new DragAndDropController();
        target = mock(DropTarget.class);
        node = mock(Node.class);
        when(target.getNode()).thenReturn(node);
    }

    @Ignore
    @Test
    public void controllerCallsSetOnDragOverWhenSettingDropTarget() {
        controller.setupDropTarget(target);
        verify(node).setOnDragOver(any());
    }

    @Ignore
    @Test
    public void cannotDoDropOnTargetSameAsSource() {
        DragEvent event = mock(DragEvent.class);
        when(event.getGestureSource()).thenReturn(target);
        controller.setupDropTarget(target);
        ArgumentCaptor<EventHandler<DragEvent>> handlerCaptor = ArgumentCaptor.forClass(EventHandler.class);
        verify(node).setOnDragOver(handlerCaptor.capture());
        EventHandler<DragEvent> handler = handlerCaptor.getValue();
        handler.handle(event);
        verify(event, never()).acceptTransferModes(any());
    }
}