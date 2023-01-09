package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.ShuttleComponent;

import java.util.Arrays;
import java.util.List;

public class ShuttleItemSteps {

    @Autowired
    private ShuttleComponent shuttleComponent;

    /**
     * Step moves given options in shuttle item
     * @param options - options separated by coma, e.g. option 1, option2.
     * @param itemName - name of the shuttle item
     */
    @When("user moves value(s) {listOfStrings} in {myString} shuttle item")
    public void user_moves_values(List<String> options, String itemName) {
        shuttleComponent.moveShuttleOptionName(itemName, options);
    }

    /**
     * Step removes given options in shuttle item
     * @param options - options separated by coma, e.g. option 1, option2.
     * @param itemName - name of the shuttle item
     */
    @When("user removes value(s) {listOfStrings} in {myString} shuttle item")
    public void user_removes_values(List<String> options, String itemName) {
        shuttleComponent.removeShuttleOptionName(itemName, options);
    }

    /**
     * Step moves all options in shuttle item
     * @param itemName - name of the shuttle item
     */
    @When("user moves all values in {myString} shuttle item")
    public void user_moves_all_values(String itemName) {
        shuttleComponent.moveAllShuttleOptions(itemName);
    }

    /**
     * Step removes all options in shuttle item
     * @param itemName - name of the shuttle item
     */
    @When("user removes all values in {myString} shuttle item")
    public void remove_all_values(String itemName) {
        shuttleComponent.removeAllShuttleOptions(itemName);
    }

    /**
     * Step verify, if given option are moved in shuttle item
     * @param options - options separated by coma, e.g. option 1, option2.
     * @param itemName - name of the shuttle item
     */
    @Then("value(s) {listOfStrings} in {myString} shuttle item is/are chosen")
    public void verify_chosen_values(List<String> options, String itemName) {
        shuttleComponent.verifyShuttleByOptionName(itemName,options);
    }

    /**
     * Step, verifies if none of the option is moved in shuttle item
     * @param itemName - name of the shuttle item
     */
    @Then("no value(s) in {myString} shuttle item is/are chosen")
    public void verify_chosen_values(String itemName) {
        shuttleComponent.verifyNoShuttleOptionChosen(itemName);
    }


}
