package booking;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CarRentalsPage extends PageBase {

    private final By placeholder = By.xpath("//*[@id='ss']");

    public CarRentalsPage(WebDriver driver) {
        super(driver);
    }

    public String fillRadio(JavascriptExecutor js) {
        js.executeScript("document.getElementById('return-location-different').click();");
        return this.waitVisibiiltyAndFindElement(placeholder).getAttribute("placeholder");
    }
}
