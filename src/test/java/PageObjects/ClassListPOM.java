package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ClassListPOM {
    private WebElement classList;
    private WebElement activeConcessionList;
    private WebDriver driver;

    public ClassListPOM(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getEnrolledClasses() {
        List<WebElement> classRows = driver
                .findElements(By.cssSelector("table.enrolled-classes > tbody tr"));
        return classRows;
    }

    public List<WebElement> getAvailableClasses() {
        List<WebElement> classRows = driver
                .findElements(By.cssSelector("table.active-classes > tbody tr"));
        return classRows;
    }
 public List<WebElement> getActiveConcessions() {
        List<WebElement> classRows = driver.findElements(By.cssSelector("table.active-concessions > tbody tr"));
        return classRows;
    }

    public String getConcessionStatus(String course) {
        WebElement concessionStatus = driver.findElement(By.id(course));
        return concessionStatus.getAttribute("value");
    }

    public void clickApproveConcession(String course){
        WebElement approveBtn = driver.findElement(By.id(course + "ApproveBtn"));
        approveBtn.click();
    }

    public void clickDeclineConcession(String course){
        WebElement declineBtn = driver.findElement(By.id(course + "DeclineBtn"));
        declineBtn.click();
    }

    public void clickDropClass(WebElement enrolledClassRow) {
        WebElement dropClassBtn = enrolledClassRow.findElement(By.cssSelector(".btn-drop-class"));

        dropClassBtn.click();
    }

    public void clickRevokeApplication(WebElement activeConcessionRow) {
        WebElement revokeApplicationBtn = activeConcessionRow.findElement(By.cssSelector(".btn-revoke-application"));

        revokeApplicationBtn.click();
    }

    public void filterAvailableClasses(String courseCategory) {
        Select availableClassFilterDropdown = new Select(driver.findElement(By.cssSelector("#availableClassFilter")));

        availableClassFilterDropdown.selectByVisibleText(courseCategory);
    }
}
