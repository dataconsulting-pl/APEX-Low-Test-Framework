package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.SwitchItemComponent;

public class SwitchItemSteps {

    @Autowired
    private SwitchItemComponent switchItemComponent;

    /**
     * Step switches on the switch item
     * @param switchName - name of the switch item element
     * @param frameTitle - frame/pop-up name
     */
    @Given("user switched on the {string} switch item on {string} pop-up")
    @When("user switch on the {string} switch item on {string} pop-up")
    public void user_sets_on_switch(String switchName, String frameTitle) {
        switchItemComponent.switchItemOn(switchName,frameTitle);
    }

    /**
     * Step switches on the switch item
     * @param switchName - name of the switch item element
     */
    @Given("user switched on the {string} switch item")
    @When("user switches on the {string} switch item")
    public void user_sets_on_switch(String switchName) {
        switchItemComponent.switchItemOn(switchName);
    }

    /**
     * Step switches off the switch item
     * @param switchName - name of the switch item element
     * @param frameTitle - frame/pop-up name
     */
    @Given("user switched off the {string} switch item on {string} pop-up")
    @When("user switches off the {string} switch item on {string} pop-up")
    public void user_sets_off_switch(String switchName, String frameTitle) {
        switchItemComponent.switchItemOff(switchName,frameTitle);
    }

    /**
     * Step switches off the switch item
     * @param switchName - name of the switch item element
     */
    @Given("user switched off the {string} switch item")
    @When("user switches off the {string} switch item")
    public void user_sets_ff_switch(String switchName) {
        switchItemComponent.switchItemOff(switchName);
    }

    /**
     * Step verifies, if given switch item is on
     * @param switchName - name of the switch item element
     */
    @Then("verify, that switch item {string} is on")
    public void verify_switch_item_on(String switchName) {
        switchItemComponent.verifySwitchItem(switchName,true);
    }

    /**
     * Step verifies, if given switch item is off
     * @param switchName - name of the switch item element
     */
    @Then("verify, that switch item {string} is off")
    public void verify_switch_item_off(String switchName) {
        switchItemComponent.verifySwitchItem(switchName, false);
    }




}
