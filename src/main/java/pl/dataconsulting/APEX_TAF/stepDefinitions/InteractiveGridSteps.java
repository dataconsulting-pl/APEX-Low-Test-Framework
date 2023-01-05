package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
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
     * @param rowNumber    - the row number
     * @param columnNumber - the column number.
     * @param igName       - name of the IG that is visible to user
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

    /**
     * Step edits the value in the cell in Interactive Grid
     *
     * @param rowNumber   - the row number
     * @param columnLabel - the label or the icon name of the column.
     * @param igName      - name of the IG that is visible to user
     */
    @Then("user sets/selects the value {string} in the cell in {int} row in column {string} in {string} IG")
    @Given("value {string} in {int} row in column {string} in {string} IG is set/selected")
    public void set_value_in_cell(String value, int rowNumber, String columnLabel, String igName) {
        igComponent.setValueInCell(value, igName, rowNumber, columnLabel);
    }

    /**
     * Step sets/selects values in cells in Interactive Grid as presented in table
     *
     * @param igName    - name of the IG that is visible to user
     * @param rowNumber - number of first row to be edited
     * @param dataTable - table that present IG table in format:
     *                  | Header 1  | Header 2  |
     *                  | value1    | value2    |
     */
    @Then("user sets the values from table in cells in IG {string} starting from {int} row:")
    @Given("value from table are is in cells in IG {string} starting from {int} row:")
    public void set_values_in_cells(String igName, Integer rowNumber, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int rowIdx = rowNumber;
        for (Map<String, String> row : rows) {
            int finalRowIdx = rowIdx;
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                igComponent.setValueInCell(v, igName, finalRowIdx, k);

            });
            rowIdx++;
        }

    }

    /**
     * Step verifies the position of columns in IG
     *
     * @param igName    - name of the IG that is visible to user
     * @param dataTable - table that contain expected position in IG table in format:
     *                  |  1        | 2         |
     *                  | Header 1  | Header 2  |
     */
    @Then("the position of columns in IG {string} is as in table:")
    public void verify_columns_Order(String igName, io.cucumber.datatable.DataTable dataTable) {
        List<Map<Integer, String>> rows = dataTable.asMaps(Integer.class, String.class);
        igComponent.verifyColumnsOrder(igName, rows);
    }

    /**
     * Step selects all records in given Interactive Grid
     *
     * @param igName - name of the IG that is visible to user
     */
    @Given("records in IG {string} are selected")
    @When("user selects all records in IG {string}")
    public void select_all_records(String igName) {
        igComponent.selectAllRecords(igName);
    }

    /**
     * Step unselects records in given Interactive Grid
     *
     * @param igName - name of the IG that is visible to user
     */
    @Given("records in IG {string} are not selected")
    @When("user unselects records in IG {string}")
    public void unselect_records(String igName) {
        igComponent.selectAllRecords(igName);
    }

}
