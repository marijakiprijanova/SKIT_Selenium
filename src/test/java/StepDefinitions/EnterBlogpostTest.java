package StepDefinitions;

import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnterBlogpostTest {

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    HomePage homePage = new HomePage(getDriver());

    @Given("^navigated to the home page$")
    public void navigateToHomePage() {
        homePage.open();
        assertTrue(homePage.isLoaded());
    }
    @When("^clicked on the blogpost title$")
    public void blogPostClick() {
        homePage.clickOnBP();
    }
    @Then("^enter the blogpost$")
    public void enterBlogPost() {
        assertTrue(homePage.enterBP());
    }

    @After
    public void quit() {
        homePage.teardown();
    }
}
