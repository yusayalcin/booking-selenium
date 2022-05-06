package parabank;

import com.github.javafaker.Faker;
import org.junit.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class ParabankTest {

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
    public void loginTest() throws InterruptedException {
        new MainPage(this.driver);

        DashboardPage dashBoardPage = login();

        Assert.assertTrue(dashBoardPage.getBodyText().contains("Welcome"));
    }

    @Test
    public void logoutTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        DashboardPage dashBoardPage = login();
        mainPage.signOut();

        Assert.assertTrue(dashBoardPage.getBodyText().contains("Customer Login"));
    }

    @Test
    public void sendFormWithUserTest() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);

        DashboardPage dashBoardPage = login();


        CustomercarePage CustomercarePage = mainPage.openSendForm();
        CustomercarePage.sendFormWithUser("Yusa Yalcin", "yusa123@mail.com", "123456", "Hello, I need your help.");
        Assert.assertTrue(dashBoardPage.getBodyText().contains("Thank you Yusa Yalcin"));

    }

    @Test
    public void sendFormWithRandomDataTest() throws InterruptedException {
        Faker faker = new Faker();

        String name = faker.name().fullName();
        String email = faker.name().username() + "@gmail.com";
        String phone = faker.phoneNumber().cellPhone();
        String message = faker.finance().iban();

        MainPage mainPage = new MainPage(this.driver);

        DashboardPage dashBoardPage = login();

        CustomercarePage CustomercarePage = mainPage.openSendForm();
        CustomercarePage.sendFormWithUser(name, email, phone, message);
        Assert.assertTrue(dashBoardPage.getBodyText().contains("Thank you " + name));

    }
    

    private DashboardPage login() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashBoardPage = loginPage.login("yusa123@mail.com", "Yusayalcin1");
        return dashBoardPage;
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
