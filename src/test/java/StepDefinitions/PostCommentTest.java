package StepDefinitions;

import Pages.BlogPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostCommentTest {
    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Marija Kiprijanova/Desktop/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    BlogPage blogPage = new BlogPage(getDriver());

    @Given("^navigated to the blog page$")
    public void navigateToBlogPage() {
        blogPage.open();
        assertTrue(blogPage.isLoaded());
    }

    @When("^entered a comment$")
    public void enterComment() {
        blogPage.addContent("Comment");
    }
    @And("^entered your name$")
    public void enterName() {
        blogPage.addName("Moeto Ime");
    }
    @And("^entered your email$")
    public void enterMail() {
        blogPage.addMail("nekojaadresa@primer.com");
    }

    /** ovoj test treba da padne bidejkji ne raboti funkcionalnosta za postiranje komentari */

    @Then("^comment posted successfully$")
    public void leftComment() {
        String text = blogPage.checkIfCommentPosted();
        assertNotEquals("0 comments", text);
    }



}
