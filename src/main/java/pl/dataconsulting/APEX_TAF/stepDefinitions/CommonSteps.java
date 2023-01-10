package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;

public class CommonSteps {

    @Autowired
    private BaseComponent baseComponent;

    /**
     * Step waits given time in seconds
     *
     * @param wait - wait time in seconds
     * @throws InterruptedException
     */
    @Then("waiting for {int} second(s)")
    public void wait_for_seconds(int wait) throws InterruptedException {
        Thread.sleep(wait * 1000L);
    }

    /**
     * Step save some string in variable. Data saved in variables can be passed to other steps. To pass the variable
     * a prefix @$ must be used. For example @$variableName
     *
     * @param data     - text to be saved in variable
     * @param variable - name of the variable
     */
    @Given("Data {myString} is saved in -> {word}")
    public void save_text_in_variable(String data, String variable) {
        baseComponent.saveTestData(variable, data);
    }

    /**
     * Step save some random string in variable.Can be used to randomize test data. Data saved in variables can be passed to other steps. To pass the variable
     * a prefix @$ must be used. For example @$variableName
     *
     * @param length   - length of the text to be generated
     * @param variable - name of the variable
     */
    @Given("random text whose length is {int} letter is saved in -> {word}")
    public void save_random_text_in_variable(int length, String variable) {
        assert length > 1 : "Random text must have at least 2 characters";
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, true, false);
        baseComponent.saveTestData(variable, generatedString);
    }

    /**
     * Step save some random number in variable.Can be used to randomize test data. Data saved in variables can be passed to other steps. To pass the variable
     * a prefix @$ must be used. For example @$variableName
     *
     * @param length   - length of the generated number
     * @param variable - name of the variable
     */
    @Given("random number whose length is {int} digits is saved in -> {word}")
    public void save_random_number_in_variable(int length, String variable) {
        assert length > 1 : "Random text must have at least 2 characters";
        String generatedString = RandomStringUtils.random(length, false, true);
        baseComponent.saveTestData(variable, generatedString);
    }


    /**
     * Step accepts alert
     */
    @Then("accept alert")
    @Then("user accepts alert")
    public void accept_alert() {
        baseComponent.acceptAlert();
    }

    /**
     * Step dismiss alert
     */
    @Then("dismiss alert")
    @Then("user dismisses alert")
    public void decline_alert() {
        baseComponent.dismissAlert();
    }

    /**
     * Step dismiss alert
     */
    @Then("user sends text {myString} to alert")
    public void send_text_to_alert(String text) {
        baseComponent.sendTextToAlert(text);
    }

    /**
     * Step gets Text from Alert and save it to variable some random number in variable. Data saved in variables can be passed to other steps. To pass the variable
     * a prefix @$ must be used. For example @$variableName
     *
     * @param variable - name of the variable
     */
    @Given("text of alert is saved in -> {word}")
    @Given("save the text of alert in -> {word}")
    public void save_text_from_alert_in_variable(String variable) {
        baseComponent.saveTestData(variable, baseComponent.getAlertText());
    }


}
