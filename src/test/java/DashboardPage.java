
import org.openqa.selenium.*;

public class DashboardPage extends PageBase {

    private final By cardBy = By.xpath("//*[@id='profile-menu-trigger--title']");
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getMainCardTitle() {
        return this.waitVisibiiltyAndFindElement(cardBy).getText();
    }

}
