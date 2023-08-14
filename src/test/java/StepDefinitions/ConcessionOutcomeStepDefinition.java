package StepDefinitions;

import PageObjects.AcademicProfilePOM;
import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ConcessionOutcomeStepDefinition extends FeatureSteps{
    private AcademicProfilePOM apPOM;
    private ClassListPOM clPOM;

    @And("The user navigates to their classes list")
    public void theUserNavigatesToTheirClassesList() {
        apPOM = new AcademicProfilePOM(driver);
        apPOM.goToClassList();
    }

    @When("The user has an active concession")
    public void theUserHasAnActiveConcession() {
        clPOM = new ClassListPOM(driver);
        Assert.assertNotEquals(0, clPOM.getActiveConcessions().size());
    }

    @And("The active concession is approved")
    public void theActiveConcessionIsApproved() {
        clPOM.clickApproveConcession("SoftEng 754");
    }

    @Then("The status of the concession should display approved")
    public void theStatusOfTheConcessionShouldDisplayApproved() {
        String concStat = clPOM.getConcessionStatus("SoftEng 754");
        Assert.assertEquals("Approved", concStat);
    }

    @And("The active concession is declined")
    public void theActiveConcessionIsDeclined() {
        clPOM.clickDeclineConcession("SoftEng 754");
    }

    @Then("The status of the concession should display declined")
    public void theStatusOfTheConcessionShouldDisplayDeclined() {
        String concStat = clPOM.getConcessionStatus("SoftEng 754");
        Assert.assertEquals("Declined", concStat);
    }
}
