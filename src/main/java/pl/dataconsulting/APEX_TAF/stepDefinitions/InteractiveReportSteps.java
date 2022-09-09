package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;
import pl.dataconsulting.APEX_TAF.APEXComponents.IRComponent;

import java.util.List;
import java.util.Map;

public class InteractiveReportSteps extends BaseComponent {

    @Autowired
    private IRComponent irComponent;

    /**
     * Step sets the filter on Interactive Report component
     * @param IRName - name of Interactive Report component
     * @param dataTable - table that present IR table in format:
     *                  | Header 1     | Header 2     |
     *                  | filter value | filter value |
     */
    @Given("{string} IR Filter is set on columns:")
    @Then("set {string} IR Filter on columns:")
    public void set_IR_Filter(String IRName, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        irComponent.setMainIRGFilter(IRName, rows);
    }

    /**
     * Step sets the filter on Interactive Report component and verify if at least one record exists
     * @param IRName - name of Interactive Report component
     * @param dataTable - table that present IR table in format:
     *                  | Header 1     | Header 2     |
     *                  | filter value | filter value |
     */
    @Then("at least one record can be found in {string} IR by:")
    public void set_filter_and_verify_at_least_one_record_exists_in_IG(String IRName, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        irComponent.setMainIRGFilter(IRName, rows);
        irComponent.verifyAtLeastOneRowExists(IRName);
    }

    /**
     * Step verifies on Interactive Report component if at least one record exists
     * @param IRName - name of Interactive Report component
     */
    @Then("at least one record can be found in {string} IR")
    public void verify_at_least_one_record_exists_in_IG(String IRName) {
        irComponent.verifyAtLeastOneRowExists(IRName);
    }

    /**
     * Step compares the data in IR
     * @param IRName - name of Interactive Report component
     * @param rowIdx - row number to be verified
     * @param dataTable - table that present IR table in format:
     *                  | Header 1     | Header 2     |
     *                  | filter value | filter value |
     */
    @Then("in {string} IR, data starting from {int} row match table below:")
    public void compare_data_ig(String IRName, Integer rowIdx, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        irComponent.compareMainIRValues(IRName, rows, rowIdx);
    }

}
