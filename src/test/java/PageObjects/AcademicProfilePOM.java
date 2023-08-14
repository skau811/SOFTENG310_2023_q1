package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AcademicProfilePOM extends BasePOM {
    private WebDriver driver;

    @FindBy(id = "currentUser")
    private WebElement currentUserField;
    @FindBy(css = ".nav-link.view-classes")
    private WebElement viewClassLink;
    @FindBy(css = ".nav-link.available-classes")
    private WebElement availableClassesLink;

    public AcademicProfilePOM(WebDriver driver) {
        super(driver);
    }

    public String getCurrentUserText(){
        waitForElement(currentUserField);
        return currentUserField.getText();
    }

    public void goToClassList() {
        waitForElement(viewClassLink);
        viewClassLink.click();
    }
}