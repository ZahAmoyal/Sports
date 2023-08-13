package PageObjects;

import org.openqa.selenium.WebDriver;

public class SportFiveIsraeli extends BasePage {

    String urlIsraeliSportFive = "https://www.sport5.co.il/world.aspx?FolderID=4439";

    public SportFiveIsraeli(WebDriver driver) {
        super(driver);
    }

    public void sportFiveIsraeli() throws InterruptedException {
        SportFiveArticles(urlIsraeliSportFive,0);
    }
}
//comment