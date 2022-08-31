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
public class IRComponent extends BaseComponent{

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
     *
     * @param IRName
     * @param filter
     */
    public void setMainIGFilter(String IRName, List<Map<String, String>> filter) {
        switchToMainFrame();
        for (Map<String, String> row : filter) {
            row.forEach( (k,v) -> setIGFilter(getWorksheetByName(IRName),k,v) );
        }
    }

    /**
     *
     * @param IRName
     */
    public void verifyAtLeastOneRowExists(String IRName){
        verifyAtLeastOneRecord(getWorksheetByName(IRName));
    }

    /**
     *
     * @param IRName
     * @param expected
     * @param startIdx
     */
    public void compareMainIGValues(String IRName, List<Map<String, String>> expected, int startIdx) {
        String action = "Compare Activities IR Values. ";

        for (Map<String, String> row : expected) {
            row.forEach( (k,v) -> asserts.assertEqualRegexp(action,k,v,getCellValue(getWorksheetByName(IRName),startIdx-1, k)));
        }
    }

    /**
     *
     * @param worksheet
     * @param row
     * @param columnName
     * @return
     */
    private String getCellValue(WebElement worksheet, int row, String columnName) {


        int columnNumber = getColumnIdx(worksheet,columnName);
        String xpathFinal = String.format(CELL_XPATH_TEMPLATE, row + 2, columnNumber + 1);
        WebElement cell = worksheet.findElement(By.xpath(xpathFinal));
        return cell.getText();
    }

    /**
     *
     * @param worksheet
     * @param columnName
     * @param value
     */
    private void setIGFilter(WebElement worksheet, String columnName, String value) {

        String description = "Setting the filter for the column: %s with value %s";
        WebElement element = worksheet.findElement(By.xpath(String.format(IR_HEADER_XPATH_TEMPLATE, columnName.toString())));
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        waitForApex();
        WebElement searchWindow = worksheet.findElement(By.xpath(SEARCH_WINDOW_XPATH_TEMPLATE));
        sendKeys(searchWindow,value);
        sendKey(Keys.ENTER);
        waitForApex();
    }

    /**
     *
     * @param worksheet
     */
    private void verifyAtLeastOneRecord(WebElement worksheet) {
        List<WebElement> elements = worksheet.findElements(By.xpath(WORKSHEET_RECORDS_XPATH_TEMPLATE));
        Assert.assertTrue(elements.size() >= 2, "Verify that at least one record has been found in IR");
    }

    /**
     * Get column index
     *
     * @param worksheet
     * @param columnName - name of the column to search
     * @return return an index of the column
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

    /**     *
     * @param IRName
     * @return
     */
    private WebElement getWorksheetByName(String IRName){
        WebElement searchedWebElement = driver.findElement(By.xpath(String.format(IR_TEMPLATE_XPATH,IRName)));
        return searchedWebElement;
    }
}



