package pl.dataconsulting.APEX_TAF.framework.config;

import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import pl.dataconsulting.APEX_TAF.framework.annotation.BeanThreadScope;
import java.time.Duration;


@Lazy
@Configuration
public class WebDriverConfig {

    @Value("${timeout:300}")
    private Duration timeout;

    @Value("${wait.implicit:10000}")
    private Duration implicitWait;

    @Value("${driver.acceptInsecureConnection:false}")
    private boolean acceptInsecureConnection;

    @Value("${driver.maximizeWindow:true}")
    private boolean maximizeWindow;


    @BeanThreadScope
    @Scope("driverscope")
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(acceptInsecureConnection);
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        configureBrowser(chromeDriver);
        return chromeDriver;
    }

    @BeanThreadScope
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(acceptInsecureConnection);
        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        configureBrowser(firefoxDriver);
        return firefoxDriver;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait webDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, this.timeout);
    }

    private void configureBrowser(RemoteWebDriver driver) {
        driver.manage().timeouts().implicitlyWait(implicitWait);
        if (maximizeWindow)
            driver.manage().window().maximize();
    }


}
