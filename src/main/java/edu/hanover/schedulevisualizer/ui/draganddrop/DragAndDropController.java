package edu.hanover.schedulevisualizer.ui.draganddrop;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DragAndDropController {
    private final static DragAndDropController instance = new DragAndDropController();
    private DropExecutor dropExecutor = null;

    public static DragAndDropController getInstance() {
        return instance;
    }

    public DragAndDropController() {}

    public void setupDropTarget(final DropTarget target) {
        setupOnDragOver(target);
        setupOnDragEntered(target);
        setupOnDragExited(target);
        setupOnDragDropped(target);
    }

    public void setupDragSource(final DragSource source) {
        source.getNode().setOnDragDetected(event -> {
            final Dragboard db = source.getNode().startDragAndDrop(TransferMode.ANY);

            final ClipboardContent content = new ClipboardContent();
            content.putString(source.getDraggedContent());
            db.setContent(content);
            source.indicateDragStarted();
            event.consume();
        });
        source.getNode().setOnDragDone(event -> {
            source.indicateDragEnded();
        });
    }


    private void setupOnDragDropped(final DropTarget target) {
        target.getNode().setOnDragDropped(event -> {
            // TODO: needs to move correct version of this code to actual target
            final boolean success = completeTheDragAndDrop(event, target);
            event.setDropCompleted(success);

            event.consume();
        });
    }

    private boolean completeTheDragAndDrop(final DragEvent event, final DropTarget target) {
        final Dragboard db = event.getDragboard();

        if (db.hasString()) {
            System.out.println(db.getString());
            return dropExecutor.executeTheDrop(target, db);
        }
        return false;
    }

    private void setupOnDragExited(final DropTarget target) {
        target.getNode().setOnDragExited(event -> {
            target.clearValidDropTarget();
            event.consume();
        });
    }

    private void setupOnDragEntered(final DropTarget target) {
        target.getNode().setOnDragEntered(event -> {
            if (isValidSourceAndContent(event, target)) {
                target.signalValidDropTarget();
            }

            event.consume();
        });
    }

    private boolean isValidSourceAndContent(final DragEvent event, final DropTarget target) {
        return containsGestureSource(event.getGestureSource(), target) &&
                isValidDragboardContent(event);
    }

    private static boolean isValidDragboardContent(final DragEvent event) {
        return event.getDragboard().hasString();
    }

    private boolean containsGestureSource(final Object gestureSource, final DropTarget target) {
        // TODO: Not exactly what it says, allows self-drops
        return gestureSource != target;
    }

    private void setupOnDragOver(final DropTarget target) {
        target.getNode().setOnDragOver(
                event -> {
                    if (isValidSourceAndContent(event, target)) {
                        System.out.println("Accepted modes: " + event.getDragboard().getTransferModes());
                        System.out.println("Default mode: " + event.getTransferMode());
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }

                    event.consume();
                });
    }

    public void setDropExecutor(final DropExecutor dropExecutor) {
        this.dropExecutor = dropExecutor;
    }
}