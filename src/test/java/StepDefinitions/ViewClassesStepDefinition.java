package StepDefinitions;

import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewClassesStepDefinition extends FeatureSteps {
    @And("The user filters the list of available classes to OPSMGT")
    public void theUserFiltersTheListOfAvailableClassesToOPSMGT() {
        ClassListPOM pom = new ClassListPOM(driver);

        pom.filterAvailableClasses("OPSMGT");
    }

    @Then("Only OPSMGT classes are displayed in the list of available classes")
    public void onlyOPSMGTClassesAreDisplayedInTheListOfAvailableClasses() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> availableClasses = pom.getAvailableClasses();

        for (WebElement availableClass : availableClasses) {
            String text = availableClass.getText();
            Assert.assertTrue(text.contains("OPSMGT"));
        }
    }
}
