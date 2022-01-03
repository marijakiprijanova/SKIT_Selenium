package StepDefinitions;

import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditBlogPostTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }
    LoginPage loginPage = new LoginPage(getDriver());

    @Given("^entered the login page$")
    public void enterLoginPage() throws InterruptedException {
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }
    @When("^logged in with correct username and password$")
    public void enterUsernamePassword() throws InterruptedException {
        loginPage.login("demo", "demo");
    }
    @And("^clicked on the Edit button$")
    public void clickEditButton() throws InterruptedException {
        loginPage.clickEdit();
    }
    @And("^changed the contents of the blogpost$")
    public void changeBlogContents() throws InterruptedException {
        loginPage.editPost("This is the edited blogpost");
    }
    @Then("^blogpost edited successfully$")
    public void checkIfEdited() {
        loginPage.goHome();
        String editedExcerpt = loginPage.getExcerpt();
        assertEquals("This is the edited blogpost", editedExcerpt);
    }
    @After
    public void close() {
        loginPage.teardown();
    }

}
