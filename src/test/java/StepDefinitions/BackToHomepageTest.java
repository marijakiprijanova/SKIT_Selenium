package StepDefinitions;

import Pages.BlogPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackToHomepageTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }
    BlogPage blogPage = new BlogPage(getDriver());

    @Given("^navigated to a blogpost page")
    public void navigateToBlogPage() {
        blogPage.open();
        assertTrue(blogPage.isLoaded());
    }
    @When("^clicked on page title$")
    public void clickHome() {
        blogPage.clickHome();
    }
    @Then("^go back to homepage$")
    public void backHomePage() {
        assertTrue(blogPage.navigateBackToHP());
    }
    @After
    public void close() {
        blogPage.teardown();
    }
}
