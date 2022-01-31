package StepDefinitions;

import Pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OlderPostsTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    HomePage homePage = new HomePage(getDriver());



    @Given("navigated to the homepage")
    public void navigatedToTheHomepage() {
        homePage.open();
        assertTrue(homePage.isLoaded());
    }

    @When("clicked on the older button")
    public void clickedOnTheOlderButton() {
        homePage.clickOlder();
    }

    @Then("go to the previous page of blogposts")
    public void goToThePreviousPageOfBlogposts() {
        assertTrue(homePage.isNavigatedToOlder());
    }
}
