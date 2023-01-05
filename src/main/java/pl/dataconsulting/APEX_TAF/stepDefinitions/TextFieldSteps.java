package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.FormComponent;

import java.util.List;
import java.util.Map;


public class TextFieldSteps {

    @Autowired
    private FormComponent formComponent;

    /**
     * Step enters given text into the text field
     *
     * @param value     - text to enter
     * @param fieldName - text field name
     * @param frameName - frame/pop-up name
     */
    @Given("user entered {string} in {string} field on {string} pop-up")
    @When("user enters {string} in {string} field on {string} pop-up")
    public void user_enters_text_into_field(String value, String fieldName, String frameName) {
        formComponent.enterStringIntoTextItem(frameName, fieldName, value);
    }

    /**
     * Step enters given text into the text field
     *
     * @param value     - text to enter
     * @param fieldName - text field name
     */
    @Given("user entered {string} in {string} field")
    @When("user enters {string} in {string} field")
    public void user_enters_text_into_field(String value, String fieldName) {
        formComponent.enterStringIntoTextItem(fieldName, value);
    }

    /**
     * Step enters text into text field according to table
     *
     * @param dataTable - table that contain field names and values to enter in format:
     *                  | field1  | field2  |
     *                  | value1  | value2  |
     */
    @Given("data is entered into text fields according to data in table:")
    @When("user enters text into text fields according to data in table:")
    public void usre_enters_in_cells(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                formComponent.enterStringIntoTextItem(k,v);

            });
        }

    }

    /**
     * Step verifies the text in text field item
     *
     * @param fieldName     - text field name
     * @param expectedValue - expected text of the text field
     */
    @Then("verify, that value of text item {string} is {string}")
    @Then("text item {string} value is {string}")
    public void user_verifies_value_in_text_field(String fieldName, String expectedValue) {
        formComponent.verifyTextItemValue(fieldName, expectedValue);
    }


}
