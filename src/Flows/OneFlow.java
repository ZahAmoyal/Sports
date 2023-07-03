package Flows;

import PageObjects.OneIsraeli;
import PageObjects.OneOlami;
import org.openqa.selenium.WebDriver;


public class OneFlow {

    OneIsraeli oneIsraeli;
    OneOlami oneOlami;

    public OneFlow(WebDriver driver) {
        oneIsraeli = new OneIsraeli(driver);
        oneOlami = new OneOlami(driver);
    }

    public void flowOne(){
        oneIsraeli.sportOneIsraeli();
        oneOlami.sportOneOlami();
    }

}
