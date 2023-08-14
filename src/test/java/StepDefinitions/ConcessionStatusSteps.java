package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.ConcessionsPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConcessionStatusSteps extends FeatureSteps{

    @Then ("User is navigated to the view classes list")
    public void user_is_navigated_to_the_view_classes_list(){
        Assert.assertEquals("http://localhost:8080/my-classes", driver.getCurrentUrl());
    }

    @And ("User can see concession status")
    public void user_can_see_concession_status(){
        ConcessionsPOM pom = new ConcessionsPOM(driver);
        List<WebElement> headings = pom.getHeadings();
        Boolean containsHeading = false;
        for (WebElement heading : headings){
            if(heading.getText().equals("Concession Status")){
                containsHeading = true;
            }
        }
        Assert.assertTrue(containsHeading);
    }
}
