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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        executeJSCommand(String.format(jsCommand, igId, getColumnIdByLabel(igId, columnLabel), operator, value));
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
        String igId = getIGidByName(igName);
        if (igId == null) {
            Assert.fail(String.format("Could not find an interactive grid by its name: %s", igName));
        }

        int columnIdx = getColumnIdxByName(columnLabel, igId);

        String xpathTemplate = "//*[@id='%s_ig_grid_vc']//*[@data-rownum=%d]/td[%d]";
        String xpath = String.format(xpathTemplate, igId, rowNumber, columnIdx);
        WebElement cell = driver.findElement(By.xpath(xpath));
        clickOnCell(cell);
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
        for (Map<String, String> row : expected) {
            row.forEach((k, v) -> {
                // if cell is empty, the value is passed as null by cucumber. Change it to empty string before comparison
                if (v == null) {
                    v = "";
                }
                try {
                    asserts.assertEqualRegexp(action, k, v, getCellValue(igAd, startIdx, k));
                } catch (NoSuchElementException e) {
                    Assert.fail(String.format("Unable to find a cell in IG %s, in column %s and row %d. %s", igName, k, startIdx, e.getLocalizedMessage()));
                }
            });
        }
    }

    /**
     * Get the text value of the cell
     *
     * @param igID        - static id of the interactive grid
     * @param row         - row number
     * @param columnLabel - the label or the icon name of the column.
     * @return text value of the IG cell
     */
    private String getCellValue(String igID, int row, String columnLabel) throws NoSuchElementException {
        String xpathTemplate = "//*[@id='%s_ig_grid_vc']//tr[@data-rownum='%d']/td[@role='gridcell'][%d]";
        int columnIdx = getColumnIdxByName(columnLabel, igID);
        String xpathFinal = String.format(xpathTemplate, igID, row, columnIdx);
        WebElement cell = driver.findElement(By.xpath(xpathFinal));
        return cell.getAttribute("innerText");

    }

    /**
     * Get the index of the column by its label
     *
     * @param columnLabel - the label or the icon name of the column.
     * @param igId        - static id of the interactive grid
     * @return the index of the column
     */
    private int getColumnIdxByName(String columnLabel, String igId) {

        String jsCommand = "return apex.region(\"%s\")"
                + ".call(\"getViews\", \"grid\")"
                + ".view$.grid(\"getColumns\")"
                + ".filter((c)=>{return (c.hidden == false)})";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(String.format(jsCommand, igId));
        ArrayList<Map<String, String>> iGColumns = (ArrayList<Map<String, String>>) jsResult;

        for (int i = 0; i < iGColumns.size(); i++) {

            // verify the column name
            if (Jsoup.parse(iGColumns.get(i).get("heading")).text().replaceAll("\\s+", "").equalsIgnoreCase(columnLabel.replaceAll("\\s+", ""))) {
                return i + 1;
            } else {
                // column by name was not found. Try to search by the name in span, for example icon name used as column label
                if (iGColumns.get(i).get("heading") != null
                        && iGColumns.get(i).get("heading").startsWith("<span")
                        && iGColumns.get(i).get("heading").contains(columnLabel)) {
                    return i + 1;
                }

            }
        }
        return -1;
    }


    /**
     * @param igId        - static id of the interactive grid
     * @param columnLabel - the label or the icon name of the column.
     * @return the id of the column
     */
    private String getColumnIdByLabel(String igId, String columnLabel) {
        // get all not hidden columns
        String jsCommand = "return apex.region(\"%s\")"
                + ".call(\"getViews\", \"grid\")"
                + ".view$.grid(\"getColumns\")"
                + ".filter((c)=>{return (c.hidden == false)})";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(String.format(jsCommand, igId));
        ArrayList<Map<String, String>> iGColumns = (ArrayList<Map<String, String>>) jsResult;

        for (int i = 0; i < iGColumns.size(); i++) {
            // verify the column name
            if (Jsoup.parse(iGColumns.get(i).get("heading")).text().replaceAll("\\s+", "").equalsIgnoreCase(columnLabel.replaceAll("\\s+", ""))) {
                return iGColumns.get(i).get("id");
            } else {
                // column by name was not found. Try to search by the name in span, for example icon name used as column label
                if (iGColumns.get(i).get("heading") != null
                        && iGColumns.get(i).get("heading").startsWith("<span")
                        && iGColumns.get(i).get("heading").contains(columnLabel)) {
                    return iGColumns.get(i).get("id");
                }
            }
        }
        return null;
    }

    /**
     * Gets the static Interactive Grid Id by its name
     *
     * @param igName - human redable name of the Interactive Grid
     * @return static id of the Interactive Grid
     */
    private String getIGidByName(String igName) {
        // find all the IG on the page
        List<WebElement> igs = driver.findElements(By.xpath("//*[@class=\"a-IG\"]"));
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
        return null;
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



