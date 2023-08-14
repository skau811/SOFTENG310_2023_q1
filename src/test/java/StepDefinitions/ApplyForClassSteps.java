package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.ApplyForClassPOM;
import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ApplyForClassSteps extends FeatureSteps{
    public final String className = "COMPSCI 712";

    @Then ("User enrols in a class")
    public void user_enrols_in_a_class(){
        ApplyForClassPOM pom = new ApplyForClassPOM(driver);
        pom.clickEnrolBtn();
    }

    @Then ("User can see this class in their class list")
    public void user_can_see_this_class_in_their_class_list(){
        AcademicProfilePOM pom = new AcademicProfilePOM(driver);
        pom.goToClassList();

        ClassListPOM pom2 = new ClassListPOM(driver);
        List<WebElement> classRows = pom2.getEnrolledClasses();

        Boolean hasClass = false;
        for (WebElement classRow : classRows) {
            if (classRow.getText().contains(className)) {
                hasClass = true;
                break;
            }
        }
        Assert.assertTrue(hasClass);
    }
}
