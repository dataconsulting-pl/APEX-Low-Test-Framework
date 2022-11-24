package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;


@APEXComponent
public class PopupLovComponent extends BaseComponent {


    /**
     * Select element in the popup lov component
     *
     * @param fieldName - name of the field
     * @param text      - text to enter
     */
    public void selectElement(String fieldName, String text) {
        String childXpath = "//*[@class='a-PopupLOV-searchBar']/input";
        // click on the lov button to open the search window
        WebElement popupLov = getWebElement(fieldName);
        String popupLovBtn = popupLov.getAttribute("id") + "_lov_btn";
        try {
            WebElement popupLovBtnEl = driver.findElement(By.id(popupLovBtn));
            popupLovBtnEl.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail(String.format("Popup lov button for element: %s could not be found", fieldName), e.getCause());
        }
        try {
            WebElement searchTextEl = driver.findElement(By.xpath(childXpath));
            sendKeys(searchTextEl, text);
            searchTextEl.sendKeys(Keys.ENTER);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail(String.format("Search Text of lov element: %s could not be found", fieldName), e.getCause());
        }

        String resultXpath = String.format("//*[@class='a-PopupLOV-results a-TMV']//li[not(text())]/span[text()='%s']", text);
        try {
            WebElement resultEl = driver.findElement(By.xpath(resultXpath));
            if (resultEl.getText().equals(text)) {
                resultEl.click();
            } else {
                Assert.fail(String.format("Did not found any result in %s popup lov component", fieldName));
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail(String.format("Search result for value: %s in lov element: %s could not be found", text, fieldName), e.getCause());
        }
    }

    /**
     * Step verifies a value in the popup lov item
     *
     * @param expectedValue - expected value in the popup lov field to be selected
     * @param fieldName     - name of the filed
     */
    public void verifyPopupLovValue(String expectedValue, String fieldName) {
        Assert.assertEquals(getDisplayValue(getWebElement(fieldName)), expectedValue);
    }


}
