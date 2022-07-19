package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;

import java.util.List;
import java.util.Map;

public class CommonSteps {

    @Autowired
    private BaseComponent baseComponent;


    @When("user enters data to fields listed in table below:")
    public void enter_data_to_fields(io.cucumber.datatable.DataTable dataTable){
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
       for (Map<String,String> columns : rows) {
           //baseComponent.enterData(columns.get("Field Name"), columns.get("Value"));
       }
    }

    @Then("waiting for {int} second(s)")
    public void wait_for_seconds(int wait) throws InterruptedException {
        Thread.sleep(wait * 1000L);
    }





}
