package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BlogPage extends BasePage {


    public BlogPage(WebDriver wd) {
        super(wd);
    }
    public void open() {
        wd.get("https://miniblogcore.azurewebsites.net/blog/this-is-a-title/");
    }
    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comments"))).isDisplayed();
    }
    //go back to homepage test
    public void clickHome() {
        wd.findElement(By.xpath("/html/body/header/div/p/a")).click();
    }
    public boolean navigateBackToHP() {
        wd.findElement(By.xpath("/html/body/main/article[1]"));
        return true;
    }
    //post comment test
    public void addContent(String comment) {
        wd.findElement(By.id("content")).sendKeys(comment);
        wd.findElement(By.id("content")).sendKeys(Keys.TAB);
    }
    public void addName(String name) {
        wd.findElement(By.id("author")).sendKeys(name);
       wd.findElement(By.id("author")).sendKeys(Keys.TAB);
    }
    public void addMail(String mail) {
        WebElement maill = wd.findElement(By.id("email"));
        maill.sendKeys(mail);
        maill.sendKeys(Keys.TAB);
        wd.findElement(By.xpath("//*[@id=\"comments\"]/div/form/div/input[3]")).sendKeys(Keys.ENTER);
    }
    public String checkIfCommentPosted() {
        WebElement element = wd.findElement(By.xpath("/html/body/main/article/footer/a"));
        return element.getText();

    }
//    public void submit() {
//        wd.findElement(By.xpath("//*[@id=\"comments\"]/div/form/div/input[3]")).click();
//    }
    //teardown
    public void teardown() {
        wd.quit();
    }

}
