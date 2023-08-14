package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.ConcessionsPOM;
import PageObjects.LoginPOM;
import backend.UserAccessLevel;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AllCommonStepDefinitions extends FeatureSteps {
    @Given("The user is logged in as a Staff")
    public void theUserIsLoggedInAsAStaff() {
        driver.get("http://localhost:8080/");
        LoginPOM loginPOM = new LoginPOM(driver);

        loginPOM.inputUsername("test123");
        loginPOM.inputPassword("testpassword123!");
        loginPOM.selectUserType(UserAccessLevel.STAFF_ADMINISTRATOR);

        loginPOM.clickLogin();
    }

    @Given("The user is logged in")
    public void theUserIsLoggedIn() {
        driver.get("http://localhost:8080/");
        LoginPOM loginPOM = new LoginPOM(driver);

        loginPOM.inputUsername("test123");
        loginPOM.inputPassword("testpassword123!");
        loginPOM.selectUserType(UserAccessLevel.STUDENT);

        loginPOM.clickLogin();
    }

    @Given("The user is logged in as a Lecturer")
    public void theUserIsLoggedInAsLecturer() {
        driver.get("http://localhost:8080/");
        LoginPOM loginPOM = new LoginPOM(driver);

        loginPOM.inputUsername("test123");
        loginPOM.inputPassword("testpassword123!");
        loginPOM.selectUserType(UserAccessLevel.LECTURER);

        loginPOM.clickLogin();
    }

    @And("User is in academic profile page")
    public void user_is_in_academic_profile_page(){
        ConcessionsPOM pom = new ConcessionsPOM(driver);
    }

    @After
    public void afterScenario() {
        if (driver.toString().contains("(null)"))
            driver.quit();
    }
}
