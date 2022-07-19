package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class MenuComponent extends BaseComponent {

    private final String MAIN_MENU_TEMPLATE_XPATH = "//*[@aria-level=1][contains(text(),'%s')]";
    private @FindBy(id = "t_Button_navControl")
    WebElement navButton ;

//    public MainPagePO(TestContext testContext) {
//        super(testContext);
//    }

    public void navigateToMenu(String menuOption) {
        extendMenu();
        WebElement menuElement = driver.findElement(By.xpath(getMenuElement(menuOption)));
        wait.until(ExpectedConditions.elementToBeClickable(menuElement)).click();
    }

    private String getMenuElement(String label) {

        return String.format(MAIN_MENU_TEMPLATE_XPATH, label);
    }

    private void extendMenu(){
        String ariaExtendedIs = navButton.getAttribute("aria-expanded");
        if (ariaExtendedIs.equalsIgnoreCase("false")){
            wait.until(ExpectedConditions.elementToBeClickable(navButton)).click();
        }
    }
}
