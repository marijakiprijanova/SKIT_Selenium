package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        wd.get("https://miniblogcore.azurewebsites.net/");
    }

     public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/article[1]/header"))).isDisplayed();
    }
    public void clickOnBP() {
        wd.findElement(By.xpath("/html/body/main/article[1]/header/h1/a")).click();
    }
    public boolean enterBP() {
        wd.findElement(By.id("comments"));
        return true;
    }
    public void teardown() {
        wd.quit();
    }
}
