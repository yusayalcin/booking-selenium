import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends PageBase {

    private final By logoutButton = By.xpath("//*[@id='b2indexPage']/header/nav[1]/div[2]/div[6]/div/div/div/ul/form/li/button");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage logout() {
        this.waitVisibiiltyAndFindElement(logoutButton).click();
        return new DashboardPage(this.driver);
    }
}
