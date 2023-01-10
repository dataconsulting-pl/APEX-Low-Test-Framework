package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.TextComponent;

import java.util.List;
import java.util.Map;


public class TextFieldSteps {

    @Autowired
    private TextComponent textComponent;

    /**
     * Step enters given text into the text field
     *
     * @param value     - text to enter
     * @param fieldName - text field name
     * @param frameName - frame/pop-up name
     */
    @Given("user entered {myString} in {myString} field on {myString} pop-up")
    @When("user enters {myString} in {myString} field on {myString} pop-up")
    public void user_enters_text_into_field(String value, String fieldName, String frameName) {
        textComponent.enterStringIntoTextItem(frameName, fieldName, value);
    }

    /**
     * Step enters given text into the text field
     *
     * @param value     - text to enter
     * @param fieldName - text field name
     */
    @Given("user entered {myString} in {myString} field")
    @When("user enters {myString} in {myString} field")
    public void user_enters_text_into_field(String value, String fieldName) {
        textComponent.enterStringIntoTextItem(fieldName, value);
    }

    /**
     * Step sends a key to text field. Supported keys are:
     *
     * @param key     - key to send
     * @param fieldName - text field name
     */
    @When("user sends {key} in {myString} field")
    public void user_sends_key_into_field(Keys key, String fieldName) {
        textComponent.sendKeyIntoTextItem(fieldName, key);
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
    public void user_enters_in_cells(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                textComponent.enterStringIntoTextItem(k, v);

            });
        }

    }

    /**
     * Step verifies the text in text field item
     *
     * @param fieldName     - text field name
     * @param expectedValue - expected text of the text field
     */
    @Then("verify, that value of text item {myString} is {myString}")
    @Then("text item {myString} value is {myString}")
    public void user_verifies_value_in_text_field(String fieldName, String expectedValue) {
        textComponent.verifyTextItemValue(fieldName, expectedValue);
    }

    /**
     * Steps reads the text from text item and save it in variable. Value saved in variables can be passed to other steps. To pass the variable
     * a prefix @$ must be used. For example @$variableName
     *
     * @param fieldName - text field name
     * @param variable  - name of the variable where data will be saved
     */
    @Given("text from text field {myString} is saved in -> {word}")
    @Then("save the text from text field {myString} in -> {word}")
    public void get_text_and_save_it(String fieldName, String variable) {
        textComponent.saveTestData(variable, textComponent.getTextItemValue(fieldName));
    }


}
