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
    @Given("{myString} IR Filter is set on columns:")
    @Then("set {myString} IR Filter on columns:")
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
    @Then("at least one record can be found in {myString} IR by:")
    public void set_filter_and_verify_at_least_one_record_exists_in_IG(String IRName, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        irComponent.setMainIRGFilter(IRName, rows);
        irComponent.verifyAtLeastOneRowExists(IRName);
    }

    /**
     * Step verifies on Interactive Report component if at least one record exists
     * @param IRName - name of Interactive Report component
     */
    @Then("at least one record can be found in {myString} IR")
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
    @Then("in {myString} IR, data starting from {int} row match table below:")
    public void compare_data_ig(String IRName, Integer rowIdx, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        irComponent.compareMainIRValues(IRName, rows, rowIdx);
    }

    /**
     * Clicks on the element in the cell in Interactive Report
     *
     * @param rowNumber   - the row number
     * @param columnLabel - the label or the icon name of the column.
     * @param irName      - name of the IR that is visible to user
     */
    @Then("user clicks on the element in {int} row in column {myString} in {myString} IR")
    @Given("element in {int} row in column {myString} in {myString} IR has been clicked")
    public void click_on_element_in_cell(int rowNumber, String columnLabel, String irName) {
        irComponent.clickOnElementInCell(irName, rowNumber, columnLabel);
    }



}
