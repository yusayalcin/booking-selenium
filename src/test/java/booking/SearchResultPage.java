package booking;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends PageBase {

  private final By search = By.xpath("//*[@id='frm']/div[1]/div[4]/div[2]/button");

  public SearchResultPage(WebDriver driver) {
    super(driver);
  }

  public DashboardPage sendForm(String input) {
    this.waitVisibiiltyAndFindElement(search).click();
    return new DashboardPage(this.driver);
  }

  public void fillCheckbox(JavascriptExecutor js) {
    js.executeScript("document.getElementsByName('ht_id=204')[0].click();");
  }

}
