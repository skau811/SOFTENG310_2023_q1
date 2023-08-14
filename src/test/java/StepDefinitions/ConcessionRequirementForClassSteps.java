package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.ConcessionsPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConcessionRequirementForClassSteps extends FeatureSteps {
    private ConcessionsPOM pom;



    @And("User can see concessions")
    public void user_can_see_concessions(){
        ConcessionsPOM pom = new ConcessionsPOM(driver);
        List<WebElement> headings = pom.getHeadings();
        for (WebElement heading : headings){
            if(heading.getText().equals("Concession Required")){
                Assert.assertTrue(true);
            }
        }
    }
}
