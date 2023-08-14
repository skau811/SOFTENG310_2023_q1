package StepDefinitions;

import PageObjects.LogOutPOM;
import PageObjects.LoginPOM;
import backend.UserAccessLevel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogOutStepDefinition extends FeatureSteps{
    private LogOutPOM logOutPOM;

    @When("The user clicks the log out button")
    public void theUserClicksTheLogOutButton() {
        LogOutPOM logOutPOM = new LogOutPOM(driver);
        logOutPOM.clickLogOut();
    }

    @Then("The user is logged out and navigated to the log in page")
    public void theUserIsLoggedOutAndNavigatedToTheLogInPage() {
        Assert.assertEquals("http://localhost:8080/", driver.getCurrentUrl());
    }
}
