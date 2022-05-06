package parabank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends PageBase {

    private final By logoutButton = By.xpath("//*[@id='leftPanel']/ul/li[8]/a");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage logout() {
        this.waitVisibiiltyAndFindElement(logoutButton).click();
        return new DashboardPage(this.driver);
    }
}
