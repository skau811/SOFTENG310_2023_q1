package StepDefinitions;

import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropClassesStepDefinition extends FeatureSteps {
    private WebElement enrolledClassRow;
    private int previousEnrolledClassSeatCount;
    private int previousWaitlistedStudentsCount;

    @When("The user has an enrolled class")
    public void theUserHasAnEnrolledClass() {
        ClassListPOM pom = new ClassListPOM(driver);

        Assert.assertTrue(pom.getEnrolledClasses().size() > 0);
    }

    @And("The user clicks on Drop Class on an enrolled class")
    public void theUserClicksOnDropClassOnAnEnrolledClass() {
        ClassListPOM pom = new ClassListPOM(driver);

        enrolledClassRow = pom.getEnrolledClasses().get(0);
        previousEnrolledClassSeatCount = Integer.parseInt(
                enrolledClassRow.findElement(By.cssSelector("td.class-seats")).getText());
        previousWaitlistedStudentsCount = Integer.parseInt(
                enrolledClassRow.findElement(By.cssSelector("td.class-waitlisted-seats")).getText()
        );

        pom.clickDropClass(enrolledClassRow);
    }

    @Then("The class is removed from the enrolled class list")
    public void theClassIsRemovedFromTheEnrolledClassList() {
        ClassListPOM pom = new ClassListPOM(driver);

        boolean hasEnrolledClass = pom.getEnrolledClasses().contains(enrolledClassRow);

        Assert.assertFalse(hasEnrolledClass);
    }


    @Then("The Drop Class button should not appear on list of enrolled classes")
    public void theDropClassButtonShouldNotAppearOnListOfEnrolledClasses() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> enrolledClasses = pom.getEnrolledClasses();

        for (WebElement enrolledClassRow : enrolledClasses) {
            try {
                enrolledClassRow.findElement(By.cssSelector("button.btn-drop-class"));

                // If it reaches here, exception was not thrown
                Assert.assertTrue(false);
            }
            catch (NoSuchElementException ex) {
                break;
            }
        }
        Assert.assertTrue(true);
    }

    @And("The Drop Class button should not appear on list of available classes")
    public void theDropClassButtonShouldNotAppearOnListOfAvailableClasses() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> availableClasses = pom.getAvailableClasses();

        for (WebElement enrolledClassRow : availableClasses) {
            try {
                enrolledClassRow.findElement(By.cssSelector("button.btn-drop-class"));

                // If it reaches here, exception was not thrown
                Assert.assertTrue(false);
            }
            catch (NoSuchElementException ex) {
                break;
            }
        }

        Assert.assertTrue(true);
    }

    @And("The class is moved to the available class list")
    public void theClassIsMovedToTheAvailableClassList() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> availableClasses = pom.getAvailableClasses();

        Assert.assertTrue(availableClasses.contains(enrolledClassRow));
    }

    @And("A new slot is opened in the class")
    public void aNewSlotIsOpenedInTheClass() {
        WebElement countColumn = enrolledClassRow.findElement(By.cssSelector(".class-seats"));

        int count = Integer.parseInt(countColumn.getText());

        Assert.assertEquals(previousEnrolledClassSeatCount + 1, count);
    }

    @And("The number of waitlisted students decreases by {int}")
    public void theNumberOfWaitlistedStudentsIncreasesBy(int arg0) {
        WebElement waitlistedStudentsCount = enrolledClassRow.findElement(By.cssSelector(".class-waitlisted-seats"));

        int count = Integer.parseInt(waitlistedStudentsCount.getText());

        Assert.assertEquals(previousWaitlistedStudentsCount - 1, count);
    }
}
