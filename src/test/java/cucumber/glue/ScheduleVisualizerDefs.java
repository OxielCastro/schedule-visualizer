package cucumber.glue;

import edu.hanover.schedulevisualizer.core.Context;
import edu.hanover.schedulevisualizer.core.entity.Weekday;
import edu.hanover.schedulevisualizer.ui.elements.MainView;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Optional;
import java.util.concurrent.Semaphore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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
    public void whenThePageIsUpdated() throws InterruptedException {
        Context.getInstance().getData();
        waitForUIUpdate();
    }

    private static void waitForUIUpdate() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Platform.runLater(() -> semaphore.release());
        semaphore.acquire();
    }

    @Given("A blank schedule")
    public void aBlankSchedule() {}

    @Given("A section with prefix {string} number {string} and description {string}")
    public void aSectionWith(String prefix, String number, String description) {
        Context.getInstance().createNewCourse(prefix, number, description);
    }

    @Then("The section {string} is scheduled on {weekday} at slot {int}")
    public void sectionScheduledAtTimeSlot(String sectionName, Weekday day, int slotnum) {
        Parent root = mainScene.getRoot();
        Optional<Node> optNode = from(root)
                .lookup("#slot" + slotnum + "column" + day)
                .tryQuery()
                .flatMap(tsc -> from(tsc).lookup("CS 220").tryQuery());
        assertThat(optNode.isPresent(), equalTo(true));
    }
}
