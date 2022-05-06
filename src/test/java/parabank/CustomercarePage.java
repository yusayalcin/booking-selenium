package parabank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomercarePage extends PageBase {

    private final By nameInputBox = By.xpath("//*[@id='name']");
    private final By emailInputBox = By.xpath("//*[@id='email']");
    private final By phoneInputBox = By.xpath("//*[@id='phone']");
    private final By messageInputBox = By.xpath("//*[@id='message']");
    private final By submit = By.xpath("//*[@id='contactForm']/table/tbody/tr[5]/td[2]/input");

    public CustomercarePage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage sendFormWithUser(String name, String email, String phone, String message)
            throws InterruptedException {

        this.waitVisibiiltyAndFindElement(nameInputBox).sendKeys(name);
        this.waitVisibiiltyAndFindElement(emailInputBox).sendKeys(email);
        this.waitVisibiiltyAndFindElement(phoneInputBox).sendKeys(phone);
        this.waitVisibiiltyAndFindElement(messageInputBox).sendKeys(message);
        this.waitVisibiiltyAndFindElement(submit).click();
        Thread.sleep(1500);

        return new DashboardPage(this.driver);
    }

}
