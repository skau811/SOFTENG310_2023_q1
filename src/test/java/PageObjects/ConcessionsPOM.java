package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ConcessionsPOM extends BasePOM {
    private WebElement availableClassesLink;

    @FindBy(id = "COMPSCI 711ConcessionLink")
    private WebElement testClassConcessionLink;

    public ConcessionsPOM(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getHeadings() {
        List<WebElement> headings = driver.findElements(By.cssSelector("table > thead tr th"));
        return headings;
    }

    public WebElement getLink(){
        waitForElement(testClassConcessionLink);
        return testClassConcessionLink;
    }

    public void clickRequestConcessionLink(){
        waitForElement(testClassConcessionLink);
        testClassConcessionLink.click();
    }
}


