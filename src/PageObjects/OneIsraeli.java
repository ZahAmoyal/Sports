package PageObjects;

import org.openqa.selenium.WebDriver;

public class OneIsraeli extends BasePage {

    //constructor
    public OneIsraeli(WebDriver driver) {
        super(driver);
    }

    public void sportOneIsraeli() {
        oneArticles("https://www.one.co.il/Soccer/News/1",9);
    }


}
