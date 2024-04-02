package cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

public class MyAppleDefs {
    Map<String, Integer> appleMap = new HashMap<>();
    @Given("^(\\w+) starts with (\\d+) apple(?:s)?$")
    public void personStartsWithApples(String person, Integer number) {
        appleMap.put(person, number);
    }

    @Then("{word} will have {int} apple(s)")
    public void personHasApples(String person, int expectedApples) {
        assertThat(appleMap.get(person), equalTo(expectedApples));
    }

    @When("{word} gives {int} apples to {word}")
    public void personGivesApplesToOther(String source, int number, String target) {
        appleMap.put(source, appleMap.get(source) - number);
        appleMap.put(target, appleMap.get(target) + number);
    }
}
