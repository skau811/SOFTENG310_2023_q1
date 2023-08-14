package StepDefinitions;

import PageObjects.ClassListPOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class RevokeClassApplicationStepDefinition extends FeatureSteps{
    private WebElement activeConcessionRow;

    @And("The user clicks on Revoke application on a concession")
    public void theUserClicksOnRevokeApplicationOnAConcession() {
        ClassListPOM pom = new ClassListPOM(driver);

        activeConcessionRow = pom.getActiveConcessions().get(0);

        pom.clickRevokeApplication(activeConcessionRow);
    }

    @Then("The concession is removed from the active concessions list")
    public void theConcessionIsRemovedFromTheActiveConcessionsList() {
        ClassListPOM pom = new ClassListPOM(driver);

        Boolean hasActiveConcession = pom.getActiveConcessions().contains(activeConcessionRow);
        Assert.assertFalse(hasActiveConcession);
    }
}
