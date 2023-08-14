package StepDefinitions;

import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class CurrentlyEnrolledClassesStepDefinition extends FeatureSteps {
    @Then("The user should see enrolled classes separate from other classes")
    public void theUserShouldSeeEnrolledClassesSeparateFromOtherClasses() {
        ClassListPOM pom = new ClassListPOM(driver);

        // No class in enrolled exists on available
        for (WebElement enrolledClassRow : pom.getEnrolledClasses()) {
            Assert.assertFalse(pom.getAvailableClasses().contains(enrolledClassRow));
        }
    }

    @And("The list should not contain pending classes")
    public void theListShouldNotContainPendingClasses() {
        ClassListPOM pom = new ClassListPOM(driver);

        // Each enrolled class does not exist in active concessions
        for (WebElement enrolledClassRow : pom.getEnrolledClasses()) {
            Assert.assertFalse(pom.getActiveConcessions().contains(enrolledClassRow));
        }
    }
}
