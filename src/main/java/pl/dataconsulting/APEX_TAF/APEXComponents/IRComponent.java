package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;
import pl.dataconsulting.APEX_TAF.framework.util.Asserts;

import java.util.List;
import java.util.Map;

@APEXComponent
public class IRComponent extends BaseComponent {

    //== XPATH Templates ==//
    private String IR_TEMPLATE_XPATH = "//div[@aria-label='%s' and contains(@class, 't-IRR-region')]";
    private String CELL_XPATH_TEMPLATE = ".//tr[%d]/td[@class=' u-tL' or @class=' u-tR'][%d]";
    private String IR_HEADER_XPATH_TEMPLATE = ".//table[@class='a-IRR-table']/tr/th/a[text()='%s']";
    private String SEARCH_WINDOW_XPATH_TEMPLATE = "//input[@class='a-IRR-sortWidget-searchField']";
    private String WORKSHEET_RECORDS_XPATH_TEMPLATE = ".//table[@class='a-IRR-table']/tbody/tr";
    private String COLUMNS_IDX_XPATH_TEMPLATE = ".//table[@class='a-IRR-table']/tr/th/a";


    //== variables ==//
    @Autowired
    private Asserts asserts;

    //== Methods ==//

    /**
     * Sets main IR Filter
     *
     * @param IRName - name of the IR component
     * @param filter - Filter to be set. List of Maps, where k = header and v = value to set
     */
    public void setMainIRGFilter(String IRName, List<Map<String, String>> filter) {
        switchToMainFrame();
        for (Map<String, String> row : filter) {
            row.forEach((k, v) -> setIRFilter(getWorksheetByName(IRName), k, v));
        }
    }

    /**
     * Verifies that at least one record is dsiplayed in IR
     *
     * @param IRName - name of the IR component
     */
    public void verifyAtLeastOneRowExists(String IRName) {
        verifyAtLeastOneRecord(getWorksheetByName(IRName));
    }


    /**
     * Compares values in IR cell
     *
     * @param IRName   - name of the IR component
     * @param expected - expected value. List of Maps, where k = header and v = value to compare
     * @param startIdx - row from which comparison should be start
     */
    public void compareMainIRValues(String IRName, List<Map<String, String>> expected, int startIdx) {
        String action = "Compare Activities IR Values. ";

        for (Map<String, String> row : expected) {
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                asserts.assertEqualRegexp(action, k, v, getCellValue(getWorksheetByName(IRName), startIdx - 1, k));
            });
        }
    }

    /**
     * Clicks on the element in the cell in Interactive Grid
     *
     * @param rowNumber   - the row number
     * @param columnName  - the label or the icon name of the column.
     * @param igName      - name of the IG that is visible to user
     */
    public void clickOnElementInCell(String igName, int rowNumber, String columnName) {
        // try to find column na Name
        WebElement worksheet = getWorksheetByName(igName);
        int columnNumber = getColumnIdx(worksheet, columnName);
        String xpathFinal = String.format(CELL_XPATH_TEMPLATE, rowNumber + 2, columnNumber + 1);
        WebElement cell = worksheet.findElement(By.xpath(xpathFinal));
        clickOnCell(cell);
    }


    /**
     * Gets the value from cell
     *
     * @param worksheet  - IR component element
     * @param row        - row number
     * @param columnName - column name
     * @return - returns value from cell
     */
    private String getCellValue(WebElement worksheet, int row, String columnName) {
        int columnNumber = getColumnIdx(worksheet, columnName);
        String xpathFinal = String.format(CELL_XPATH_TEMPLATE, row + 2, columnNumber + 1);
        WebElement cell = worksheet.findElement(By.xpath(xpathFinal));
        return cell.getText();
    }


    /**
     * Sets main IR Filter
     *
     * @param worksheet  - IR component element
     * @param columnName - column name
     * @param value      - value to be set
     */
    private void setIRFilter(WebElement worksheet, String columnName, String value) {
        waitForApex();
        String description = "Setting the filter for the column: %s with value %s";
        WebElement element = worksheet.findElement(By.xpath(String.format(IR_HEADER_XPATH_TEMPLATE, columnName.toString())));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        waitForApex();
        WebElement searchWindow = worksheet.findElement(By.xpath(SEARCH_WINDOW_XPATH_TEMPLATE));
        sendKeys(searchWindow, value);
        sendKey(Keys.ENTER);
        waitForApex();
    }

    /**
     * Verifies that at least one record is displayed in IR
     *
     * @param worksheet - IR component element
     */
    private void verifyAtLeastOneRecord(WebElement worksheet) {
        List<WebElement> elements = worksheet.findElements(By.xpath(WORKSHEET_RECORDS_XPATH_TEMPLATE));
        Assert.assertTrue(elements.size() >= 2, "Verify that at least one record has been found in IR");
    }


    /**
     * Get column index
     *
     * @param worksheet  - IR component element
     * @param columnName - name of the column to search
     * @return - returns an index of the column
     */
    private int getColumnIdx(WebElement worksheet, String columnName) {
        String action = "Get Column Idx";
        int idx = -1;
        try {
            List<WebElement> elements = worksheet.findElements(By.xpath(COLUMNS_IDX_XPATH_TEMPLATE));

            for (int i = 0; i < elements.size(); i++) {
                String tmp = elements.get(i).getText();
                if (elements.get(i).getText().split("\n")[0].equalsIgnoreCase(columnName)) {
                    return i;
                }

            }
        } catch (NumberFormatException e) {
            Assert.fail("Number format exception by getting column index:" + e);
        }
        return idx;
    }

    /**
     * Gets the Worksheet by name
     *
     * @param IRName - name of the IR
     * @return - IR component webElement
     */
    private WebElement getWorksheetByName(String IRName) {
        WebElement searchedWebElement = driver.findElement(By.xpath(String.format(IR_TEMPLATE_XPATH, IRName)));
        return searchedWebElement;
    }


    private void clickOnCell(WebElement cell) {
        List<WebElement> elements = cell.findElements(By.xpath("./child::*"));
        if (elements.isEmpty()) {
            cell.click();
        } else {
            elements.get(0).click();
        }
    }
}



