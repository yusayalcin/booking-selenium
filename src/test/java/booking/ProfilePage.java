package booking;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ProfilePage extends PageBase{

    private final By accountButton = By.xpath("//*[@id='b2indexPage']/header/nav[1]/div[2]/div[6]/div/div/div/ul/li[1]/a");
    private final By personalDetails = By.xpath("//*[@id='root']/div/div/div[1]/div/div/nav/ul/li[1]/a");
    private final By editAddressButton = By.xpath("//*[@id='root']/div/div/div[1]/div/div/div/div/div[2]/div[8]/div/div[1]/button");
    private static final By addressInputBox = By.xpath("//*[@name='line1']");
    private final By save = By.xpath("//*[@id='root']/div/div/div[1]/div/div/div/div/div[2]/div[8]/div/div[2]/form/div[2]/div/button");
    private final By address = By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div/div/div[2]/div[8]/div/div[2]/div/div/div[2]/div/div/div");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

      public DashboardPage sendFormWithUser(String input, JavascriptExecutor js) throws InterruptedException {
        this.waitVisibiiltyAndFindElement(accountButton).click();
        this.waitVisibiiltyAndFindElement(personalDetails).click();
        this.waitVisibiiltyAndFindElement(editAddressButton).click();
        this.waitVisibiiltyAndFindElement(addressInputBox).sendKeys(input);

        WebElement element = this.waitVisibiiltyAndFindElement(save); 
        js.executeScript("arguments[0].click();", element);

        return new DashboardPage(this.driver);
    }

    public String getAddress() {
        return this.waitVisibiiltyAndFindElement(address).getText();
    }

}
