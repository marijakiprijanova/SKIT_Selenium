package StepDefinitions;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnsuccessfulLoginTest {

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    LoginPage loginPage = new LoginPage(getDriver());

    @Given("^navigated to the login page$")
    public void navigateToLoginPage() throws InterruptedException {

        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }
    @When("^entered wrong username or password$")
    public void enterUsernamePassword() throws InterruptedException {

        //assertTrue(loginPage.isLoaded());
        loginPage.login("demo", "wrong");
    }
    @Then("^display error message$")
    public void failToLogIn() {
        String errorMessage = loginPage.getErrorMessage();
        assertEquals("Username or password is invalid.",errorMessage);
    }

}
