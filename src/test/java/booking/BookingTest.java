package booking;

import org.junit.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BookingTest {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void sendFormTest() {
        MainPage mainPage = new MainPage(this.driver);

        SearchResultPage searchResultPage = mainPage.searchDestination("Budapest");

        Assert.assertTrue(searchResultPage.getBodyText().contains("Budapest"));
        Assert.assertTrue(searchResultPage.getBodyText().contains("properties found"));

    }

    @Test
    public void checkBoxTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        SearchResultPage searchResultPage = mainPage.searchDestination("Budapest");
        searchResultPage.fillCheckbox(js);

        Thread.sleep(2000);
        Assert.assertTrue(searchResultPage.getBodyText().contains("Your results include aparthotels"));

    }

    @Test
    public void radioTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        CarRentalsPage carRentalsPage = mainPage.openCarRentalsPage();
        String placeholder = carRentalsPage.fillRadio(js);
        Assert.assertEquals(placeholder, "Drop-off location");

    }

    @Test
    public void titleTest() {
        MainPage mainPage = new MainPage(this.driver);

        Assert.assertTrue(mainPage.getBodyText().contains("A lifetime of discounts? It's Genius."));
    }

    @Test
    public void hoverTest() {
        MainPage mainPage = new MainPage(this.driver);
        String hoveredData = mainPage.hover();
        Assert.assertEquals("Choose your language", hoveredData);
    }

    @Test
    public void navigateBackTest() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.searchDestination("Budapest");
        this.driver.navigate().back();

        Assert.assertTrue(mainPage.getBodyText().contains("A lifetime of discounts? It's Genius."));
    }

    @Test
    public void javascriptExecutorTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        MainPage mainPage = new MainPage(this.driver);
        String title = mainPage.javascriptExecutorGetTitle(js);
        Assert.assertEquals(title,
                "Booking.com | Official site | The best hotels, flights, car rentals & accommodations");

        Assert.assertTrue(mainPage.getBodyText().contains("Compare and book flights with ease"));

    }

    @Test
    public void multiplePageTest() {
        MultiplePage.urls.stream().forEach(url -> {
            new MultiplePage(this.driver, url);
        });
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
