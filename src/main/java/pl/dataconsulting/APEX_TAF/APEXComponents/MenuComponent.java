package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

import java.util.ArrayList;
import java.util.List;

@APEXComponent
public class MenuComponent extends BaseComponent {

    private final String MAIN_MENU_TEMPLATE_XPATH_MAIN = "//*[@aria-level=1][contains(text(),'%s')]";

    private final String MAIN_MENU_TEMPLATE_XPATH_SUB = "/../..//*[@aria-level=%d][contains(text(),'%s')]";

    private @FindBy(id = "t_Button_navControl")
    WebElement navButton;

    /**
     * Navigates to menu element.
     *
     * @param menuOptions - List of menu and sub-menu elements counted from top.
     */
    public void navigateToMenu(List<String> menuOptions) {

        // click on the main menu
        if (menuOptions.size() > 0) {
            extendMenu();
            List<String> menuWebElements = getMenuElementsXpath(menuOptions);
            for (int i = 0; i < menuWebElements.size(); i++) {
                WebElement menuElement = driver.findElement(By.xpath(menuWebElements.get(i)));
                wait.until(ExpectedConditions.elementToBeClickable(menuElement)).click();

                // get Again menu element
                menuElement = driver.findElement(By.xpath(menuWebElements.get(i)));
                if (menuElement.getAttribute("aria-expanded") != null &&
                        menuElement.getAttribute("aria-expanded").equals("false") &&
                        i < menuWebElements.size() - 1) {

                    wait.until(ExpectedConditions.elementToBeClickable(
                            menuElement.findElement(By.xpath("../../span[@class='a-TreeView-toggle']")))
                    ).click();
                    waitForApex();
                }
            }
        }
    }

    /**
     * Verifies, if menu option is selected
     *
     * @param menuOption - List of menu and sub-menu elements counted from top.
     */
    public void verifyMenuOption(List<String> menuOption) {
        String lastOptionXpath = getMenuElementsXpath(menuOption).get(getMenuElementsXpath(menuOption).size() - 1);
        WebElement menuElement = driver.findElement(By.xpath(lastOptionXpath));
        Assert.assertTrue(menuElement.getAttribute("aria-selected") != null &&
                menuElement.getAttribute("aria-selected").equals("true"), "Menu element selected: " + menuElement.getText());

    }

    /**
     * Gets xpaths of menu options
     *
     * @param menuOption - List of menu and sub-menu elements counted from top.
     * @return - List contains xpath of menu options
     */
    private List<String> getMenuElementsXpath(List<String> menuOption) {

        List<String> menuElements = new ArrayList<>();
        String elementXpath = String.format(MAIN_MENU_TEMPLATE_XPATH_MAIN, menuOption.get(0));
        menuElements.add(elementXpath);
        for (int i = 1; i < menuOption.size(); i++) {
            elementXpath = elementXpath + String.format(MAIN_MENU_TEMPLATE_XPATH_SUB, i + 1, menuOption.get(i));
            menuElements.add(elementXpath);
        }

        return menuElements;
    }


    /**
     * Extend main APEX menu
     */
    private void extendMenu() {
        String ariaExtendedIs = navButton.getAttribute("aria-expanded");
        if (ariaExtendedIs.equalsIgnoreCase("false")) {
            wait.until(ExpectedConditions.elementToBeClickable(navButton)).click();
        }
    }
}
