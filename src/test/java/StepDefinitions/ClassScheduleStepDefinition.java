package StepDefinitions;

import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClassScheduleStepDefinition extends FeatureSteps{

    @Then("The classes in the available classes list shows the timetable for all classes")
    public void theClassesInTheAvailableClassesListShowsTheTimetableForAllClasses() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getAvailableClasses();
        int showsTimetable = 0;
        for (WebElement classRow :
                classRows) {
            if (classRow.findElement(By.id("Timetable")).getText() != null) {
                showsTimetable++;
            }
        }
        Assert.assertEquals(classRows.size(),showsTimetable);
    }

    @And("The classes in the available classes list shows an alternative timetable for classes with this set")
    public void theClassesInTheAvailableClassesListShowsAnAlternativeTimetableForClassesWithThisSet() {
        ClassListPOM pom = new ClassListPOM(driver);

        List<WebElement> classRows = pom.getAvailableClasses();
        int showsAltTimetable = 0;
        for (WebElement classRow :
                classRows) {
            if (classRow.findElement(By.id("AltTimetable")).getText() != null) {
                showsAltTimetable++;
            }
        }
        Assert.assertTrue( showsAltTimetable != 0);
    }

}
