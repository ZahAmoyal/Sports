package PageObjects;

import org.openqa.selenium.WebDriver;

public class OneOlami extends BasePage{

    //constructor
    public OneOlami(WebDriver driver) {
        super(driver);
    }

    public void sportOneOlami() {
        oneArticles("https://www.one.co.il/Soccer/News/3",14);
    }


}
