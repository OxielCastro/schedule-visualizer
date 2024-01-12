package edu.hanover.schedulevisualizer.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class AppTest {
    private Button button;
    private App app;

    @Start
    private void start(Stage stage) throws IOException {
        app = new App();
        app.startApp(stage);
    }

    @Disabled
    @Test
    void should_contain_button_with_text(FxRobot robot) {
        FxAssert.verifyThat("#helloButton", LabeledMatchers.hasText("Hello!"));
    }

    @Disabled
    @Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        // when:
        robot.clickOn("#helloButton");

        // then:
        FxAssert.verifyThat("#welcomeLabel",
                            LabeledMatchers.hasText(MatchesPattern.matchesPattern("^Welcome.*")));
    }
}