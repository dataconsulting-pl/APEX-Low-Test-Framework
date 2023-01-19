package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;
import pl.dataconsulting.APEX_TAF.framework.util.Asserts;

import java.util.*;

@APEXComponent
public class IGComponent extends BaseComponent {


    //== variables ==//

    @Autowired
    private Asserts asserts;


    /**
     * Sets  filter on Interactive Grid component
     *
     * @param igName      - name of the IG that is visible to user
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
     */
    public void setIGColumnFilter(String igName, String columnLabel, String operator, String value) {

        String jsCommand = "return apex.region(\"%s\").call(\"addFilter\","
                + "{ type       : \"column\""
                + ", columnType : \"column\""
                + ", columnId: \"%s\""
                + ", operator   : \"%s\""
                + ", value : \"%s\""
                + "      });";
        String igId = getIGidByName(igName);
        executeJSCommand(String.format(jsCommand, igId, Objects.requireNonNull(getColumnAttributesByLabel(igId, columnLabel)).id, operator, value));
        waitForApex();
    }

    /**
     * Clicks on the element in the cell in Interactive Grid
     *
     * @param rowNumber   - the row number
     * @param columnLabel - the label or the icon name of the column.
     * @param igName      - name of the IG that is visible to user
     */
    public void clickOnElementInCell(String igName, int rowNumber, String columnLabel) {
        clickOnCell(getIgCellElement(igName, columnLabel, rowNumber));
    }

    /**
     * Clicks on the element in the cell in Interactive Grid
     *
     * @param rowNumber    - the row number
     * @param columnNumber - the column number
     * @param igName       - name of the IG that is visible to user
     */
    public void clickOnElementInCell(String igName, int rowNumber, int columnNumber) {
        // try to find column na Name
        String igId = getIGidByName(igName);
        if (igId == null) {
            Assert.fail(String.format("Could not find an interactive grid by its name: %s", igName));
        }
        String xpathTemplate = "//*[@id='%s_ig_grid_vc']//*[@data-rownum=%d]/td[%d]";
        String xpath = String.format(xpathTemplate, igId, rowNumber, columnNumber);
        WebElement cell = driver.findElement(By.xpath(xpath));
        clickOnCell(cell);
    }

    /**
     * Compares the data in Interactive Grid
     *
     * @param igName   - name of the IG that is visible to user
     * @param expected - table that present IG table in format:
     *                 | Header 1     | Header 2     |
     *                 | filter value | filter value |
     * @param startIdx - the row number
     */
    public void compareMainIGValues(String igName, List<Map<String, String>> expected, int startIdx) {
        String action = "Compare Activities IR Values. ";

        String igAd = getIGidByName(igName);
        int rowIdx = startIdx;
        for (Map<String, String> row : expected) {
            int finalRowIdx = rowIdx;
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                try {
                    asserts.assertEqualRegexp(action, k, v, getCellValue(igAd, finalRowIdx, k));
                } catch (NoSuchElementException e) {
                    Assert.fail(String.format("Unable to find a cell in IG %s, in column %s and row %d. %s", igName, k, startIdx, e.getLocalizedMessage()));
                }
            });
            rowIdx++;
        }
    }

    /**
     * Verify if the text value in given row and column do not match
     *
     * @param igName     - name of the IG that is visible to user
     * @param columnName - column label
     * @param row        - row inex
     * @param value      - value to be verfified
     */
    public void verifyMissingData(String igName, String columnName, int row, String value) {
        String igAd = getIGidByName(igName);

        // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
        if (value == null) {
            value = "";
        }
        Assert.assertNotEquals(String.format("Cell in %d row and %s column in IG %s has not expected %s value", row, columnName, igName, value), value, getCellValue(igAd, row, columnName));

    }

    /**
     * Set the value in the cell in Interactive Grid
     *
     * @param value       - value to be set
     * @param rowNumber   - the row number
     * @param columnLabel - the label or the icon name of the column.
     * @param igName      - name of the IG that is visible to user
     */
    public void setValueInCell(String value, String igName, int rowNumber, String columnLabel) {
        String jsCommand = "return apex.region(\"%s\").widget()" +
                ".interactiveGrid('getViews','grid')" +
                ".view$.grid('getModel').setValue(" +
                "apex.region('%s').widget().interactiveGrid('getViews','grid')." +
                "getSelectedRecords()[0], '%s','%s')";

        String igId = getIGidByName(igName);
        if (igId == null) {
            Assert.fail(String.format("Could not find an interactive grid by its name: %s", igName));
        }
        //select the row
        //selectRecord(igId, rowNumber);
        clickOnCell(getIgCellElement(igName, columnLabel, rowNumber));

        IgColumn igColumn = getColumnAttributesByLabel(igId, columnLabel);

        // check if in IG Cell there is a select list
        assert igColumn != null;
        if (igColumn.dataType.equals("NUMBER") && igColumn.mapDefaultValue) {
            // find option
            String xpath = "//select[@name='%s']/option[text()='%s']";
            try {
                value = driver.findElement(By.xpath(String.format(xpath, igColumn.id, value))).getAttribute("value");
            } catch (NoSuchElementException e) {
                Assert.fail(String.format("Element could not be found. Please verify is value is selectable." +
                        "IG: %s, rowNumber: %s, column name: %s, value to select: %s. Exception message: %s", igName, rowNumber, columnLabel, value, e.getLocalizedMessage()));
            }
        }


        String jsOutput = executeJSCommand(String.format(jsCommand, igId, igId, Objects.requireNonNull(igColumn).property, value));
        if (jsOutput.equals("SET") || jsOutput.equals("NC")) {
            Assert.assertTrue(String.format("%s value in IG %s cell in column %s and row %d has been set", value, igName, columnLabel, rowNumber), true);
        }
    }

    /**
     * Verifies is the order of the column on the page match the given order
     *
     * @param igName          - name of the IG that is visible to user
     * @param columnsToVerify - list of columns to verify
     */
    public void verifyColumnsOrder(String igName, List<Map<Integer, String>> columnsToVerify) {
        // get all not hidden columns
        String jsCommand = "return apex.region(\"%s\")"
                + ".call(\"getViews\", \"grid\")"
                + ".view$.grid(\"getColumns\")"
                + ".filter((c)=>{return (c.hidden == false)})";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(String.format(jsCommand, getIGidByName(igName)));
        ArrayList<Map<String, String>> iGColumns = (ArrayList<Map<String, String>>) jsResult;
        columnsToVerify.get(0).forEach((k, v) -> {
            if (iGColumns.size() < k - 1) {
                Assert.fail(String.format("Expected position is higher then the number of the IG columns. IG: %s, position: %d, IG size: %d", igName, k, iGColumns.size()));
            }
            Assert.assertEquals(String.format("Compare the order of column in IG: %s. Position: %s", igName, k), iGColumns.get(k - 1).get("heading"), v);
        });


    }

    /**
     * Select all visible records in IG
     *
     * @param igName - name of the IG that is visible to user
     */
    public void selectAllRecords(String igName) {
        String jsCommand = "return apex.region(\"%s\")"
                + ".call(\"getViews\", \"grid\")"
                + ".view$.grid(\"selectAll\")";

        executeJSCommand(String.format(jsCommand, getIGidByName(igName)));
    }


    /**
     * Unselect records in IG
     *
     * @param igName - name of the IG that is visible to user
     */
    public void unselectRecords(String igName) {
        String jsCommand = "return apex.region(\"%s\")"
                + ".call(\"getViews\", \"grid\")"
                + ".view$.grid(\"setSelection\",[])";

        executeJSCommand(String.format(jsCommand, getIGidByName(igName)));
    }

    /**
     * Gets the text from cell in Interactive Grid
     *
     * @param igName      - name of the IG that is visible to user
     * @param columnLabel - Name of the column
     * @param rowNumber   - the row number
     */
    public String getCellValue(String igName, String columnLabel, int rowNumber) {
        String igAd = getIGidByName(igName);
        return getCellValue(igAd, rowNumber, columnLabel);

    }

    /**
     * Verify, that no record is displayed in given IG
     *
     * @param igName        - static id of the interactive grid
     */
    public void verifyNoRecordDisplayed(String igName) {
        String jsCommand = "return apex.region(\"%s\")" +
                ".widget()" +
                ".interactiveGrid(\"getViews\",\"grid\")" +
                ".model" +
                ".recordAt(0)";

        String igAd = getIGidByName(igName);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(String.format(jsCommand, igAd));
        Assert.assertNull(String.format("IG %s has some records displayed", igName),jsResult);

    }


    /**
     * Select row in the IG
     *
     * @param igId      - id of the Interactive Grid
     * @param rowNumber - number of the row to be selected
     */
    private void selectRecord(String igId, int rowNumber) {
        String jsCommand = "return apex.region('%s')" +
                ".widget()" +
                ".interactiveGrid('getViews','grid')" +
                ".gotoCell(%d)";

        executeJSCommand(String.format(jsCommand, igId, rowNumber + 1));
    }

    /**
     * Get the text value of the cell
     *
     * @param igId        - static id of the interactive grid
     * @param row         - row number
     * @param columnLabel - the label or the icon name of the column.
     * @return text value of the IG cell
     */
    private String getCellValue(String igId, int row, String columnLabel) throws NoSuchElementException {
        int columnIdx = Objects.requireNonNull(getColumnAttributesByLabel(igId, columnLabel)).idx;

        String jsCommand = "return apex.region(\"%s\")" +
                ".widget()" +
                ".interactiveGrid(\"getViews\",\"grid\")" +
                ".model" +
                ".recordAt(%d)";

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(String.format(jsCommand, igId, row - 1));
        if (jsResult == null) {
            Assert.fail("No records could be found in IG: " + igId);
        }

        ArrayList<Objects> iGColumns = (ArrayList<Objects>) jsResult;
        return cellCast(iGColumns.get(columnIdx));

    }

    /**
     * Get column attributes by its label
     *
     * @param igId        - static id of the interactive grid
     * @param columnLabel - the label or the icon name of the column.
     * @return the id of the column
     */
    private IgColumn getColumnAttributesByLabel(String igId, String columnLabel) {
        // get all columns
        String jsCommand = "return apex.region(\"%s\")"
                + ".call(\"getViews\", \"grid\")"
                + ".view$.grid(\"getColumns\")";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(String.format(jsCommand, igId));
        ArrayList<Map<String, Object>> iGColumns = (ArrayList<Map<String, Object>>) jsResult;
        int htmlIdx = 1;
        for (int i = 0; i < iGColumns.size(); i++) {
            // skip the action column
            if (iGColumns.get(i).get("property").equals("APEX$ROW_ACTION")) {
                // increase the htmlIdx only if column is not hidden
                if (!(Boolean) iGColumns.get(i).get("hidden"))
                    htmlIdx++;
                continue;
            }
            // Get the attributes for non-hidden columns (Note: hidden columns do not need to have the name
            if (!(Boolean) iGColumns.get(i).get("hidden")) {

                // verify the column name
                if (Jsoup.parse((String) iGColumns.get(i).get("heading")).text().replaceAll("\\s+", "").equalsIgnoreCase(columnLabel.replaceAll("\\s+", ""))) {
                    return new IgColumn(
                            columnLabel,
                            iGColumns.get(i).get("property"),
                            iGColumns.get(i).get("dataType"),
                            iGColumns.get(i).get("defaultValue"),
                            iGColumns.get(i).get("id"),
                            iGColumns.get(i).get("index"),
                            htmlIdx);
                } else {
                    // column by name was not found. Try to search by the name in span, for example icon name used as column label
                    if (iGColumns.get(i).get("heading") != null
                            && ((String) iGColumns.get(i).get("heading")).startsWith("<span")
                            && ((String) iGColumns.get(i).get("heading")).contains(columnLabel)) {
                        return new IgColumn(
                                columnLabel,
                                iGColumns.get(i).get("property"),
                                iGColumns.get(i).get("dataType"),
                                iGColumns.get(i).get("defaultValue"),
                                iGColumns.get(i).get("id"),
                                iGColumns.get(i).get("index"),
                                htmlIdx);
                    }
                }
                htmlIdx++;
            }
        }
        return null;
    }

    /**
     * Gets the static Interactive Grid Id by its name
     *
     * @param igName - human-readable name of the Interactive Grid
     * @return static id of the Interactive Grid
     */
    private String getIGidByName(String igName) {
        // find all the IG on the page
        List<WebElement> igs = driver.findElements(By.xpath("//*[@class='a-IG'] | //*[@class='a-IG a-IG--noToolbar']"));
        for (WebElement el : igs) {
            if (el.getAttribute("id") != null) {
                // get the id of the element and add heading to it
                String igId = el.getAttribute("id").replaceAll("_ig$", "");
                WebElement IgElement = driver.findElement(By.id(igId));
                // try to find the name by IG aria-label
                if (IgElement.getAttribute("aria-label") != null) {
                    if (IgElement.getAttribute("aria-label").equals(igName))
                        return igId;
                } else {
                    // element has no aria-label. Try to find by its heading name
                    String igHeading = igId + "_heading";
                    List<WebElement> igHeadingEls = driver.findElements(By.xpath(String.format("//*[@id='%s']", igHeading)));

                    if (igHeadingEls.size() > 0 && (igHeadingEls.get(0).getAttribute("innerText").equals(igName))) {
                        return igId;
                    }

                }

            }
        }
        Assert.fail("Could not find the static id of the Interactive Grid: " + igName);
        return null;
    }

    /**
     * Gets the webElement that represent an IG Cell
     *
     * @param igName      - human-readable name of the Interactive Grid
     * @param columnLabel - the label or the icon name of the column.
     * @param rowNumber   - row number of the cell
     * @return - webElement object that represent na IG Cell
     */
    private WebElement getIgCellElement(String igName, String columnLabel, int rowNumber) {
        String igId = getIGidByName(igName);
        if (igId == null) {
            Assert.fail(String.format("Could not find an interactive grid by its name: %s", igName));
        }

        int columnIdx = Objects.requireNonNull(getColumnAttributesByLabel(igId, columnLabel)).htmlIdx;

        String xpathTemplate = "//*[@id='%s_ig_grid_vc']//*[@data-rownum=%d]/td[%d]";
        String xpath = String.format(xpathTemplate, igId, rowNumber, columnIdx);
        return driver.findElement(By.xpath(xpath));
    }


    /**
     * Clicks on the value in Cell
     *
     * @param cell - webElement object that represent na IG Cell
     */
    private void clickOnCell(WebElement cell) {
        List<WebElement> elements = cell.findElements(By.xpath("./child::*"));
        if (elements.isEmpty()) {
            cell.click();
        } else {
            elements.get(0).click();
        }
    }

    /**
     * Helper function to cast the object returned by JS. Variables returned by JS can be String or Map (e.g. in case of select List). Function will take the value from map and return as string
     *
     * @param object - object returned by JS
     * @return - object cast to String
     */
    private String cellCast(Object object) {
        if (object instanceof Map) {
            Map<String, String> cell = (Map<String, String>) object;
            Optional<String> value = cell.values().stream().findFirst();
            if (value.isPresent()) {
                return value.get();
            }
        } else if (object instanceof String) {
            return String.valueOf(object);
        }
        return null;
    }


}

/**
 * Helper class to store parameters of IG Column
 */
class IgColumn {
    public String label;
    public String property;
    //public int index;
    public String dataType;
    public boolean mapDefaultValue;
    public String id;
    public int idx;
    public int htmlIdx;

    public IgColumn(String label, Object property, Object dataType, Object defaultValue, Object id, Object idx, int htmlIdx) {

        this.label = label;
        this.property = (String) property;
        this.dataType = (String) dataType;
        this.mapDefaultValue = !(defaultValue instanceof String);
        this.id = (String) id;
        this.idx = ((Long) idx).intValue();
        this.htmlIdx = htmlIdx;
    }

}



