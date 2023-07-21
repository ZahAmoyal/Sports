package PageObjects;

import org.openqa.selenium.WebDriver;

public class SportFiveOlami extends BasePage {

    String urlOlamiSportFive = "https://www.sport5.co.il/world.aspx?FolderID=4453";

    public SportFiveOlami(WebDriver driver) {
        super(driver);
    }

    public void sportFiveOlami() throws InterruptedException {
        SportFiveArticles(urlOlamiSportFive,5);
    }
}
