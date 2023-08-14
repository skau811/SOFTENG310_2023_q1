package PageObjects;

import backend.UserAccessLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPOM extends BasePOM {
    private final WebElement usernameField;
    private final WebElement passwordField;
    private final WebElement userTypeDropdown;
    private final WebElement loginButton;

    public LoginPOM(WebDriver driver) {
        super(driver);

        this.usernameField = driver.findElement(By.id("username-login"));
        this.passwordField = driver.findElement(By.id("password-login"));
        this.userTypeDropdown = driver.findElement(By.id("usertype"));
        this.loginButton = driver.findElement(By.id("button-login"));
    }

    public void clickLogin() {
        waitForElement(loginButton);
        loginButton.click();
    }

    public void inputUsername(String username) {
        waitForElement(usernameField);
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password) {
        waitForElement(passwordField);
        this.passwordField.sendKeys(password);
    }

    public void selectUserType(UserAccessLevel accessLevel) {
        waitForElement(userTypeDropdown);

        switch (accessLevel) {
            case STUDENT:
                this.userTypeDropdown.sendKeys("Student");
                break;
            case LECTURER:
                this.userTypeDropdown.sendKeys("Lecturer");
                break;
            case STAFF_ADMINISTRATOR:
                this.userTypeDropdown.sendKeys("Staff Administrator");
                break;
        }
    }
}
