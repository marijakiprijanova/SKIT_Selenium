package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

        public final WebDriverWait wait;
        protected WebDriver wd;

        public BasePage(WebDriver wd) {
            this.wd = wd;
            wait = new WebDriverWait(wd, 10);
        }
}
