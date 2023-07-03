package Flows;

import PageObjects.OneIsraeli;
import PageObjects.OneOlami;
import PageObjects.SportFiveIsraeli;
import PageObjects.SportFiveOlami;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SportFiveFlow {

    SportFiveOlami sportFiveOlami;
    SportFiveIsraeli sportFiveIsraeli;


    public SportFiveFlow(WebDriver driver) {
        sportFiveOlami = new SportFiveOlami(driver);
        sportFiveIsraeli = new SportFiveIsraeli(driver);
    }

    public void flowSportFive() throws InterruptedException {
        sportFiveIsraeli.sportFiveIsraeli();
        sportFiveOlami.sportFiveOlami();
    }


}
