package parabank;

import org.openqa.selenium.*;

public class MainPage extends PageBase {
    private final By signoutButton = By.xpath("//*[@id='leftPanel']/ul/li[8]/a");
    private final By customercareButton = By.xpath("//*[@id='headerPanel']/ul[2]/li[3]/a");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://parabank.parasoft.com/");
    }

    public void signOut() {
        try {
            this.waitVisibiiltyAndFindElement(signoutButton).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    public CustomercarePage openSendForm() {
        try {
            this.waitVisibiiltyAndFindElement(customercareButton).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return new CustomercarePage(this.driver);
    }

}
