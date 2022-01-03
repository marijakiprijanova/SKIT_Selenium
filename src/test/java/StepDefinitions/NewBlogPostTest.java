package StepDefinitions;

import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewBlogPostTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    LoginPage loginPage = new LoginPage(getDriver());

    @Given("^navigated to the log in page$")
    public void navigateToLoginPage() throws InterruptedException {

        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }
    @When("^logged in successfully$")
    public void enterUsernamePassword() throws InterruptedException {
        loginPage.login("demo", "demo");
    }
    @And("^entered the new post page and filled out title and excerpt$")
    public void postNewBlog() {
        loginPage.addNewBP("This is a title", "This is a description");
    }
    @And("^clicked the submit button$")
    public void submitBP() {
        loginPage.submitClicked();
    }
    @Then("^error message is displayed$")
    public void getErrorMessage() {
        String errorMessage  = loginPage.getBPErrorMessage();
        assertEquals("The Content field is required.", errorMessage);
    }


}
