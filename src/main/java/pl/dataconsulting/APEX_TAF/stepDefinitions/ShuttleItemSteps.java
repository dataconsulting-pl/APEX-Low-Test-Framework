package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.ShuttleComponent;

import java.util.List;

public class ShuttleItemSteps {

    @Autowired
    private ShuttleComponent shuttleComponent;

    @When("user moves value(s) {listOfStrings} in {string} shuttle item")
    public void user_moves_values(List<String> options, String itemName) {
        shuttleComponent.moveShuttleOptionName(itemName, options);
    }

    @When("user removes value(s) {listOfStrings} in {string} shuttle item")
    public void user_removes_values(List<String> options, String itemName) {
        shuttleComponent.removeShuttleOptionName(itemName, options);
    }

    @When("user moves all values in {string} shuttle item")
    public void user_moves_all_values(String itemName) {
        shuttleComponent.moveAllShuttleOptions(itemName);
    }

    @When("user removes all values in {string} shuttle item")
    public void remove_all_values(String itemName) {
        shuttleComponent.removeAllShuttleOptions(itemName);
    }

    @Then("value(s) {listOfStrings} in {string} shuttle item is/are chosen")
    public void verify_chosen_values(List<String> options, String itemName) {
        shuttleComponent.verifyShuttleByOptionName(itemName,options);
    }

    @Then("no value(s) in {string} shuttle item is/are chosen")
    public void verify_chosen_values(String itemName) {
        shuttleComponent.verifyNoShuttleOptionChosen(itemName);
    }


}
