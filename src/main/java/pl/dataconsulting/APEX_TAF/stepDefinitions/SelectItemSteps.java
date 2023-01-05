package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.SelectItemComponent;

import java.util.List;
import java.util.Map;


public class SelectItemSteps {


    @Autowired
    private SelectItemComponent selectItemComponent;


    /**
     * Step selects an option on the select item
     * @param value - option to be selected
     * @param fieldName - name of the filed
     * @param frameName - frame/pop-up name
     */
    @Given("user selected {string} in {string} field on {string} pop-up")
    @When("user selects {string} in {string} field on {string} pop-up")
    public void user_set_value_in_select_item(String value, String fieldName, String frameName) {
        selectItemComponent.selectItem(frameName, value, fieldName);
    }

    /**
     * Step selects an option on the select item
     * @param value - option to be selected
     * @param fieldName - name of the filed
     */
    @Given("user selected {string} in {string} field")
    @When("user selects {string} in {string} field")
    public void user_set_value_in_select_item(String value, String fieldName) {
        selectItemComponent.selectItem(value, fieldName);
    }

    /**
     * Step selects option on the select item according to table
     *
     * @param dataTable - table that contain field names and values to enter in format:
     *                  | field1  | field2  |
     *                  | option1 | option2 |
     */
    @Given("option is selected in fields according to data in table:")
    @When("user selects option in fields according to data in table:")
    public void usre_enters_in_cells(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                selectItemComponent.selectItem(v,k);

            });
        }

    }

    /**
     * Step verifies a value in the select item
     * @param expectedValue - option to be selected
     * @param fieldName - name of the filed
     */
    @Then("value {string} is selected in {string} field")
    public void verify_select_item(String expectedValue, String fieldName) {
        selectItemComponent.verifySelectItemValue(expectedValue, fieldName);
    }


}
