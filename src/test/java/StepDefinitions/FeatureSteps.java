package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class FeatureSteps {
    protected static WebDriver driver;

    public FeatureSteps() {
        System.setProperty("webdriver.chrome.driver", "selenium/binaries/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "selenium/binaries/geckodriver.exe");

        if (driver == null) {
            // Change this to ChromeDriver/FirefoxDriver
            driver = new FirefoxDriver();
            //driver = new ChromeDriver();
        }
    }
}
