package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;
import pl.dataconsulting.APEX_TAF.APEXComponents.IGComponent;
import pl.dataconsulting.APEX_TAF.APEXComponents.IRComponent;

import java.util.List;
import java.util.Map;

public class InteractiveGridSteps extends BaseComponent {

    @Autowired
    private IGComponent igComponent;

    /**
     * Step sets the filter on Interactive Grid component
     *
     * @param columnLabel - the label or the icon name of the column.
     * @param operator    - operator to be set. Supported operators are:
     *                    - NEQ
     *                    - Not Equals
     *                    - LT
     *                    - Less Than
     *                    - LTE
     *                    - Less than or equal to
     *                    - GT
     *                    - Greater Than
     *                    - LIKE
     *                    - SQL Like operator
     *                    - NLIKE
     *                    - Not Like
     *                    - C
     *                    - Contains
     *                    - NC
     *                    - Not Contains
     * @param value       - value to be set on filter
     * @param igName      - name of the IG that is visible to user
     */
    @Given("filter on column {string} {operator} {string} in {string} IG is set")
    @Then("user sets filter on column {string} {operator} {string} in {string} IG")
    public void set_IR_Filter(String columnLabel, String operator, String value, String igName) {
        igComponent.setIGColumnFilter(igName, columnLabel, operator, value);
    }

    /**
     * Clicks on the element in the cell in Interactive Grid
     *
     * @param rowNumber   - the row number
     * @param columnLabel - the label or the icon name of the column.
     * @param igName      - name of the IG that is visible to user
     */
    @Then("user clicks on the element in {int} row in column {string} in {string} IG")
    @Given("element in {int} row in column {string} in {string} IG has been clicked")
    public void click_on_element_in_cell(int rowNumber, String columnLabel, String igName) {
        igComponent.clickOnElementInCell(igName, rowNumber, columnLabel);
    }

    /**
     * Clicks on the element in the cell in Interactive Grid
     *
     * @param rowNumber   - the row number
     * @param columnNumber - the column number.
     * @param igName      - name of the IG that is visible to user
     */
    @Then("user clicks on the element in {int} row in {int} column in {string} IG")
    @Given("element in {int} row in {int} column in {string} IG has been clicked")
    public void click_on_element_in_cell(int rowNumber, int columnNumber, String igName) {
        igComponent.clickOnElementInCell(igName, rowNumber, columnNumber);
    }

    /**
     * Step compares the data in Interactive Grid
     *
     * @param IgName    - name of the IG that is visible to user
     * @param rowNumber - the row number
     * @param dataTable - table that present IG table in format:
     *                  | Header 1     | Header 2     |
     *                  | filter value | filter value |
     */
    @Then("in {string} IG, data starting from {int} row match table below:")
    public void compare_data_ig(String IgName, Integer rowNumber, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        igComponent.compareMainIGValues(IgName, rows, rowNumber);
    }


}
