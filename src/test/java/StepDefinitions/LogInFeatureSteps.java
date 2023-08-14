package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.LoginPOM;
import backend.UserAccessLevel;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogInFeatureSteps extends FeatureSteps {
    private LoginPOM loginPOM;
    private AcademicProfilePOM academicProfilePOM;

    @Given("User navigates to the Login page")
    public void user_navigates_to_login_page() {
        driver.get("http://localhost:8080");
        loginPOM = new LoginPOM(driver);
    }


    @When("User logs in with invalid username: \\{string} and invalid password: \\{string}")
    public void userLogsInWithInvalidUsernameStringAndInvalidPasswordString() {
        loginPOM.inputUsername("invalidUsername");
        loginPOM.inputPassword("!");
        loginPOM.selectUserType(UserAccessLevel.STAFF_ADMINISTRATOR);
    }

    @When("User logs in with valid username: \\{string} and valid password: \\{string}")
    public void user_log_in_with_valid_username_and_valid_password(){
        loginPOM.inputUsername("test123");
        loginPOM.inputPassword("testpassword123!");
        loginPOM.selectUserType(UserAccessLevel.STUDENT);
    }

    @And("User clicks on Login button")
    public void user_clicks_on_Login_button(){
        loginPOM.clickLogin();
    }

    @Then("The user is sent to to their Academic profile")
    public void the_user_is_sent_to_to_their_Academic_profile() {

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/AcademicProfile", currentUrl);

        academicProfilePOM = new AcademicProfilePOM(driver);
        String userName = academicProfilePOM.getCurrentUserText();;
        Assert.assertEquals("Student : test123", userName);
    }
    
    @Then("The user is not logged in and remains on the login page")
    public void theUserIsNotLoggedInAndRemainsOnTheLoginPage() {
        Assert.assertEquals("http://localhost:8080/", driver.getCurrentUrl());
    }
}
