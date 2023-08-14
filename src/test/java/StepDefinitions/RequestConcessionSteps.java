package StepDefinitions;

import PageObjects.ConcessionsPOM;
import backend.Concession;
import io.cucumber.java.en.And;
import org.junit.Assert;

public class RequestConcessionSteps extends FeatureSteps {

    @And ("User can click request concession")
    public void user_can_click_request_concession(){
        ConcessionsPOM pom = new ConcessionsPOM(driver);
        pom.clickRequestConcessionLink();
        Assert.assertTrue(pom.getLink().getText().equals("Request sent"));
    }
}
