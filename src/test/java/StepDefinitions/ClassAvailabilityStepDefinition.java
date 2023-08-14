package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClassAvailabilityStepDefinition extends FeatureSteps {
    private String openClassName;

    @And("The user views the list of classes")
    public void theUserViewsTheListOfClasses() {
        AcademicProfilePOM pom = new AcademicProfilePOM(driver);

        pom.goToClassList();
    }

    @When("A class is not full")
    public void aClassIsNotFull() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getEnrolledClasses();

        Boolean hasOpenClass = false;
        for (WebElement classRow :
                classRows) {
            Boolean isOpen = classRow.getText().contains("Open");
            if (isOpen) {
                hasOpenClass = true;
                this.openClassName = classRow.findElement(By.className("class-name")).getText();
                break;
            }
        }

        Assert.assertTrue(hasOpenClass);
    }

    @Then("The class should be marked as open")
    public void theClassShouldBeMarkedAsOpen() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getEnrolledClasses();

        WebElement openClassRow = classRows.stream()
                .filter(classRow ->
                        classRow.getText().contains(this.openClassName))
                .findAny()
                .orElse(null);

        String openClassRowStatus = openClassRow.findElement(By.cssSelector(".class-status")).getText();

        Assert.assertNotNull(openClassRow);
        Assert.assertEquals("Open", openClassRowStatus);
    }

    @And("The number of remaining seats should be displayed")
    public void theNumberOfRemainingSeatsShouldBeDisplayed() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getEnrolledClasses();

        WebElement openClassRow = classRows.stream()
                .filter(classRow ->
                        classRow.getText().contains(this.openClassName))
                .findAny()
                .orElse(null);

        String openClassSeats = openClassRow.findElement(By.cssSelector(".class-seats")).getText();

        try {
            Integer.parseInt(openClassSeats);
            Assert.assertTrue(true);
        }
        catch (NumberFormatException e) {
            Assert.assertTrue(false);
        }
    }

    @Then("The class should be marked as full")
    public void theClassShouldBeMarkedAsFull() {
        String className = "Full Class";
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getEnrolledClasses();

        WebElement fullClassRow = classRows.stream()
                .filter(classRow ->
                        classRow.getText().contains(className))
                .findAny()
                .orElse(null);

        String fullClassStatus = fullClassRow.findElement(By.cssSelector(".class-status")).getText();

        Assert.assertNotNull(fullClassRow);
        Assert.assertEquals("Full", fullClassStatus);
    }

    @And("The user is informed that they will be referred to the waiting list")
    public void theUserIsInformedThatTheyWillBeReferredToTheWaitingList() {
        String className = "Full Class";
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getEnrolledClasses();

        WebElement fullClassRow = classRows.stream()
                .filter(classRow ->
                        classRow.getText().contains(className))
                .findAny()
                .orElse(null);

        String fullClassNote = fullClassRow.findElement(By.cssSelector(".class-note")).getText();

        Assert.assertNotNull(fullClassRow);
        Assert.assertEquals("Will be referred to waiting list", fullClassNote);
    }
}
