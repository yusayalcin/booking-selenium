package parabank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private final By emailInputBox = By.xpath("//*[@id='loginPanel']/form/div[1]/input");
    private final By passwordInputBox = By.xpath("//*[@id='loginPanel']/form/div[2]/input");
    private final By signIn = By.xpath("//*[@id='loginPanel']/form/div[3]/input");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String email, String password) throws InterruptedException {
        this.waitVisibiiltyAndFindElement(emailInputBox).sendKeys(email);
        this.waitVisibiiltyAndFindElement(passwordInputBox).sendKeys(password);
        this.waitVisibiiltyAndFindElement(signIn).click();
        Thread.sleep(1500);
        return new DashboardPage(this.driver);
    }
}
