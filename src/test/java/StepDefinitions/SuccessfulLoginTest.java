package StepDefinitions;

import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuccessfulLoginTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    LoginPage loginPage = new LoginPage(getDriver());

    @Given("^navigated to the sign in page$")
    public void navigateToLoginPage() throws InterruptedException {

        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }
    @When("^entered the correct username and password$")
    public void enterUsernamePassword() throws InterruptedException {
        loginPage.login("demo", "demo");
    }
    @Then("^successfully sign in$")
    public void signIn() {
        assertTrue(loginPage.signedIn());
    }
    @After
    public void close() {
        loginPage.teardown();
    }

}
