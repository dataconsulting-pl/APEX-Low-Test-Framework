package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommonSteps {

    @Autowired
    private BaseComponent baseComponent;

    /**
     * Step waits given time in seconds
     * @param wait - wait time in seconds
     * @throws InterruptedException
     */
    @Then("waiting for {int} second(s)")
    public void wait_for_seconds(int wait) throws InterruptedException {
        Thread.sleep(wait * 1000L);
    }

}
