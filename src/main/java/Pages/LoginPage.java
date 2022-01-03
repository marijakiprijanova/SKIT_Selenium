package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver wd) {

        super(wd);
    }

    public void open() {

        wd.get("https://miniblogcore.azurewebsites.net/login/?returnUrl=/");
    }

    public boolean isLoaded() throws InterruptedException {
        //Thread.sleep(5000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName"))).isDisplayed();
    }
    public void login(String user, String password) {
        wd.findElement(By.id("UserName")).clear();
        wd.findElement(By.id("UserName")).sendKeys(user);
        wd.findElement(By.id("Password")).sendKeys(password);
        wd.findElement(By.id("Password")).sendKeys(Keys.ENTER);
    }
    public String getErrorMessage() {
        WebElement errorPage = wd.findElement(By.xpath("//*[@id='login']/div"));
        return errorPage.getText();
    }
    public boolean signedIn() {
        WebElement success = wd.findElement(By.xpath("/html/body/footer/div/nav/ul/li[2]/a"));
        return true;
    }
    public void addNewBP(String title, String excerpt) {
        wd.findElement(By.xpath("/html/body/footer/div/nav/ul/li[1]/a")).click();
        wd.findElement(By.id("Title")).sendKeys(title);
        wd.findElement(By.id("Excerpt")).sendKeys(excerpt);
    }
    public void submitClicked() {
        wd.findElement(By.xpath("//*[@id=\"edit\"]/input[8]")).click();
    }
    public String getBPErrorMessage() {
        WebElement errorMessage = wd.findElement(By.xpath("//*[@id=\"edit\"]/div[1]"));
        return errorMessage.getText();
    }
    public String getBPTitle() {
        WebElement title = wd.findElement(By.xpath("/html/body/main/article/header/h1/a"));
        return title.getText();
    }
    //edit blog post test
    public void clickEdit() {
        wd.findElement(By.xpath("/html/body/main/article[1]/header/a")).click();
    }
    public void editPost(String content) {
        wd.findElement(By.xpath("//*[@id=\"Excerpt\"]")).sendKeys(Keys.CONTROL, "a");
        wd.findElement(By.xpath("//*[@id=\"Excerpt\"]")).sendKeys(Keys.DELETE);
        wd.findElement(By.xpath("//*[@id=\"Excerpt\"]")).sendKeys(content);
        wd.findElement(By.xpath("//*[@id=\"edit\"]/input[8]")).click();
    }
    public String getExcerpt() {
        WebElement excerpt = wd.findElement(By.xpath("/html/body/main/article[1]/div"));
        return excerpt.getText();
    }
    //ne znam shto e ova
    public void goHome() {
       wd.findElement(By.xpath("/html/body/header/div/p/a")).click();
    }
    //sign out test
    public void signOut() {
        wd.findElement(By.xpath("/html/body/footer/div/nav/ul/li[2]/a")).click();
    }
    public String checkIfSignedOut() {
    WebElement text = wd.findElement(By.xpath("/html/body/footer/div/nav/ul/li"));
    return text.getText();
    }

    //teardown
    public void teardown() {
        wd.quit();
    }

}
