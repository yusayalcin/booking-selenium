import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private final By emailInputBox = By.xpath("//*[@id='username']");
    private final By passwordInputBox = By.xpath("//*[@id='password']");
    private final By continueWithEmail = By
            .xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/div[3]/button");
    private final By signIn = By.xpath("//*[@id='root']/div/div/div/div[2]/div[1]/div/div/div/div/div/div/form/button");
                                                        
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String email, String password) throws InterruptedException {
        this.waitVisibiiltyAndFindElement(emailInputBox).sendKeys(email);
        Thread.sleep(2000);
        this.waitVisibiiltyAndFindElement(continueWithEmail).click();
        this.waitVisibiiltyAndFindElement(passwordInputBox).sendKeys(password);
        Thread.sleep(2000);
        this.waitVisibiiltyAndFindElement(signIn).click();
        return new DashboardPage(this.driver);
    }
}
