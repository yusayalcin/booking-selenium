package booking;

import org.openqa.selenium.*;

public class MainPage extends PageBase {
    private final By dropDownButton = By.xpath("//*[@id='b2indexPage']/header/nav[1]/div[2]/div[6]/div/a");
    private final By searchLocator = By.xpath("//*[@id='frm']/div[1]/div[4]/div[2]/button");
    private final By carRentals = By.xpath("//*[@id='b2indexPage']/header/nav[2]/ul/li[4]/a");
    private final By hoverButton = By.xpath("//*[@id='b2indexPage']/header/nav[1]/div[2]/div[2]/button");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.booking.com/index.en-gb.html");
    }

  
    public SearchResultPage searchDestination(String searchQuery) {
        WebElement searchBarElement = waitVisibiiltyAndFindElement(searchLocator);
        searchBarElement.sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public ProfilePage openProfilePage() {
        try {
            this.waitVisibiiltyAndFindElement(dropDownButton).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return new ProfilePage(this.driver);
    }

    public String javascriptExecutorGetTitle(JavascriptExecutor js) {

        String TitleName = js.executeScript("return document.title;").toString();
        System.out.println("Title of the page = " + TitleName);
        js.executeScript("window.location = 'flights/'");
        return TitleName;
    }

    public CarRentalsPage openCarRentalsPage() {
        try {
            this.waitVisibiiltyAndFindElement(carRentals).click();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return new CarRentalsPage(this.driver);
    }

    public String hover() {
        return this.waitVisibiiltyAndFindElement(hoverButton).getAttribute("data-tooltip-text");
      }
}
