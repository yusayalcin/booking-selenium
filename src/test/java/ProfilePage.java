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
    private final By uploadFileButton = By.xpath("//*[@id='root']/div/div/div[1]/div/div/div/div/div[1]/button");
    private final By selectFileButton = By.xpath("//*[@id='my-settings-file-upload']");
    private final By saveFileButton = By.xpath("/html/body/div[4]/div/div/div/div/div/div/div/div/div[1]/div[2]/div[2]/button");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

      public DashboardPage sendFormWithUser(String input, JavascriptExecutor js) throws InterruptedException {
        this.waitVisibiiltyAndFindElement(accountButton).click();
        this.waitVisibiiltyAndFindElement(personalDetails).click();
        this.waitVisibiiltyAndFindElement(editAddressButton).click();

        //Thread.sleep(2000);

        this.waitVisibiiltyAndFindElement(addressInputBox).sendKeys(input);

        //Thread.sleep(2000);

        WebElement element = this.waitVisibiiltyAndFindElement(save); 
        js.executeScript("arguments[0].click();", element);


        return new DashboardPage(this.driver);
    }

    public String getAddress() {
        return this.waitVisibiiltyAndFindElement(address).getText();
    }

    public void uploadFile() throws InterruptedException {
        this.waitVisibiiltyAndFindElement(accountButton).click();
        this.waitVisibiiltyAndFindElement(personalDetails).click();
        Thread.sleep(2000);
        this.waitVisibiiltyAndFindElement(uploadFileButton).click();
        this.waitVisibiiltyAndFindElement(selectFileButton).click();

        Thread.sleep(1000);
        WebElement chooseFile = driver.findElement(By.id("custom-input"));
        chooseFile.sendKeys("/home/yusa/Downloads/elte.jpg");
        Thread.sleep(1000);

        this.waitVisibiiltyAndFindElement(saveFileButton).click();

        

    }

}
