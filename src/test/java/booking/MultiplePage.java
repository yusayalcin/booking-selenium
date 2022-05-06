package booking;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;

public class MultiplePage extends PageBase {
    protected static List<String> urls;

    public MultiplePage(WebDriver driver, String url) {
        super(driver);
        this.driver.get(url);
    }

    static {
        urls = new ArrayList<>();
        urls.add("https://www.booking.com/index.en-gb.html");
        urls.add("https://www.booking.com/flights/index.en.html");
        urls.add("https://www.booking.com/cars/index.en-gb.html");
    }

}
