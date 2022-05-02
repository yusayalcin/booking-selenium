import java.util.Collections;

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
        //System.setProperty("webdriver.chrome.driver","/home/yusa/Downloads/chromedriver");
        
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-blink-features=AutomationControlled");



        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        
    }

    @Test
    public void loginTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.openSignin();
        DashboardPage dashBoardPage = loginPage.login("jekosed421@pantabi.com", "Yusayalcin123");
        System.out.println(dashBoardPage.getBodyText());
        Assert.assertTrue(dashBoardPage.getBodyText().contains("Yusa Yalcin"));
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
    public void logoutTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        LoginPage loginPage = mainPage.openSignin();
        DashboardPage dashBoardPage = loginPage.login("cicaji2185@pantabi.com", "Yusayalcin12");

        LogoutPage logoutPage = mainPage.openSignout();
        logoutPage.logout();

        Assert.assertTrue(dashBoardPage.getBodyText().contains("Sign in"));
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
        MainPage mainPage =    new MainPage(this.driver);
        String hoveredData = mainPage.hover();
        Assert.assertEquals("Choose your language", hoveredData);
    }

    @Test
    public void sendFormWithUserTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        LoginPage loginPage = mainPage.openSignin();
        loginPage.login("jekosed421@pantabi.com", "Yusayalcin123");

        ProfilePage profilePage = mainPage.openSendForm();
        profilePage.sendFormWithUser("Budapest", js);
        //System.out.println(sendFormPage.getAddress().contains("Budapest"));
        //System.out.println(sendFormPage.getAddress());
        Assert.assertTrue(profilePage.getAddress().contains("Budapest"));

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
/*
@Test
public void uploadFile() throws InterruptedException {
    MainPage mainPage = new MainPage(this.driver);
    LoginPage loginPage = mainPage.openSignin();
    loginPage.login("xihali9414@wowcg.com", "Yusayalcin12");

    ProfilePage profilePage = mainPage.openSendForm();
    profilePage.uploadFile();
   
   // Assert.assertTrue(sendFormPage.getAddress().contains("Budapest"));

}*/

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
