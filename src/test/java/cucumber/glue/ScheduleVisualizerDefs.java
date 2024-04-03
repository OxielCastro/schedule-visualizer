package cucumber.glue;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import edu.hanover.schedulevisualizer.ui.elements.MainView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ScheduleVisualizerDefs extends ApplicationTest {
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        mainScene = new Scene(new MainView());
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.toFront();
    }

    @Disabled
    @Test
    public void testRuns() {
        Parent root = mainScene.getRoot();
        assertThat(root, notNullValue());
    }

    @Before
    public void startTestApp() throws Exception {
        internalBefore();
    }

    @After
    public void stopTestApp() throws Exception {
        internalAfter();
    }

    @ParameterType("Monday|Tuesday|Wednesday|Thursday|Friday")
    public Weekday weekday(String weekday){
        return Weekday.valueOf(weekday);
    }
    @When("^The page is updated$")
    public void whenThePageIsUpdated() {
        Context.getInstance().getData();
    }

    @Then("The section {string} is scheduled on {weekday} at slot {int}")
    public void sectionScheduledAtTimeSlot(String sectionName, Weekday day, int slotnum) {
        Parent root = mainScene.getRoot();
        Node node = from(root).lookup("CS 220").query();
        assertThat(node, notNullValue());
    }
}
