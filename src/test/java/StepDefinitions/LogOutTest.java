package StepDefinitions;

import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }
    LoginPage loginPage = new LoginPage(getDriver());

    @Given("^navigated to the login site$")
    public void enterLoginPage() throws InterruptedException {
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }
    @When("^logged in with the correct credentials$")
    public void enterUsernamePassword() throws InterruptedException {
        loginPage.login("demo", "demo");
    }
    @And("^clicked the sign out button in the bottom$")
    public void signOut() {
        loginPage.signOut();
    }
    @Then("^log out successfully$")
    public void logOutSuccess() {
        String text = loginPage.checkIfSignedOut();
        assertEquals("Sign in",text);
    }
}
