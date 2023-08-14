package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplyForClassPOM extends BasePOM {

    @FindBy(id = "COMPSCI 712EnrolBtn")
    private WebElement testClassEnrolBtn;

    public ApplyForClassPOM(WebDriver driver) {
        super(driver);
    }

    public void clickEnrolBtn(){
        waitForElement(testClassEnrolBtn);
        testClassEnrolBtn.click();
    }
}
