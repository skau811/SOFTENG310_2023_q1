package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPOM extends BasePOM {

    @FindBy(css = ".nav-link.log-out")
    private WebElement logOutBtn;

    public LogOutPOM(WebDriver driver){
        super(driver);
    }

    public void clickLogOut(){
        waitForElementToAppear(By.cssSelector(".nav-link.log-out"));
        this.logOutBtn.click();
    }
}
