package Tests;
import Flows.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    static WebDriver driver;

    static SportFiveFlow sportFiveFlow;
    static OneFlow oneFlow;


   /* By allArticlesSportFive = By.cssSelector(".row .col-sm-12.col-md-12.col-lg-8.col-xs-8.article-list.article-list-alt");
    String ar = ".section.section-.color-alt-";*/


/*    //one
    By allArticlesOne = By.cssSelector("div.leagues-right-column");
    String ar2 = "a.article-plain.rtl.right";*/
/*
    public static void resetFlows() {
        OneFlow = new oneFlow(driver);
        SportFiveFlow = new sportFiveFlow(driver);
    }*/

    // ביצוע איתחול לכל הflows
    public static void resetFlows() {
        sportFiveFlow = new SportFiveFlow(driver);
        oneFlow = new OneFlow(driver);
    }


    public static void setupBrowzer() {
        System.setProperty("webdriver.chrome.driver", "src/Data/chromedriver1.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60000));
        driver.manage().window().maximize();
    }
    public void waitVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @BeforeClass
    public static void setup(){
        setupBrowzer();
        resetFlows();
    }


//    @AfterClass
//    public static void closeDriver() {
//        driver.close();
//        driver.quit();
//    }

   /* public void sportFive(String url){
        driver.get(url);
        String handle=driver.getWindowHandle();
        List<WebElement> articles;
        //if (url.contains("sport")) {
            WebElement web = driver.findElement(allArticlesSportFive);
            articles = web.findElements(By.cssSelector("div.section.section-.color-alt-"));
            System.out.println(articles.size() + " size");
            for (int i = 0; i < articles.size() - 1; i++) {
                System.out.println("-------------------------------");
                System.out.println("Sport5");
                WebElement webv = driver.findElement(allArticlesSportFive);
                List<WebElement> articlesb = webv.findElements(By.cssSelector("div.section.section-.color-alt-"));
                WebElement check = articlesb.get(i).findElement(By.tagName("h2"));
                System.out.println(i+" III");
                Actions actions = new Actions(driver);
                actions.keyDown(Keys.LEFT_CONTROL)
                        .click(articlesb.get(i))
                        .keyUp(Keys.LEFT_CONTROL)
                        .build()
                        .perform();

              handles();
                String pageSource=driver.getPageSource();
                System.out.println("Source -"+handle);


                System.out.println("Target - "+driver.getWindowHandle());
                String title=driver.getTitle();
                System.out.println(title);
                System.out.println(title.contains("דקה אחר דקה"));
                if (driver.getTitle().contains("דקה אחר דקה")) {
                    //   articlesb.get(i).click();
                 dakaAharDake();
                }
                else {
                    //articlesb.get(i).click();
                    getArticlesSportFive();
                    //driver.navigate().back();
                }
                driver.close();
                driver.switchTo().window(handle);
                //webv = driver.findElement(allArticlesSportFive);
                //articlesb = webv.findElements(By.cssSelector("div.section.section-.color-alt-"));
            }
        } else {
            *//*WebElement web = driver.findElement(allArticlesOne);
            articles = web.findElements(By.tagName("a"));
            System.out.println(articles.size() + " size");
            for (int i = 0; i < articles.size() - 1; i++) {
                System.out.println("-------------------------------");
                System.out.println("OneTests");
                WebElement webb = driver.findElement(allArticlesOne);
                List<WebElement> articlesb = webb.findElements(By.tagName("a"));
                articlesb.get(i).click();
                getArticlesOne();
                driver.navigate().back();
            }*//*
        }
    }*/
   /* public void getArticlesSportFive() {
        By koteret = By.cssSelector("h1.article-main");
        WebElement elementKotert = driver.findElement(koteret);
        By tatKoteret = By.cssSelector("h2.article-sub-main");
        By tohen = By.cssSelector("div.article-content");
        By picture = By.cssSelector("div.content-holder");
        WebElement pictureElement = driver.findElement(picture);
        WebElement elementKatava = driver.findElement(tohen);
        WebElement tatKoteretK = driver.findElement(tatKoteret);
        System.out.println(elementKotert.getText());
        System.out.println(tatKoteretK.getText());
        //List<WebElement> koteret1 = elementKotert.findElements(By.tagName("span"));
        //for (int i=0; i<= koteret1.size();i++){
        //System.out.println(koteret1.get(0).getText());
        //System.out.println(elementKotert.getText());
        //System.out.println(tatKoteretK.getText());
        List<WebElement> pic = pictureElement.findElements(By.tagName("img"));
        System.out.println(pic.size());
        System.out.println(pic.get(1).getAttribute("src"));
        //  }
        List<WebElement> katava = elementKatava.findElements(By.tagName("p"));
        for (int i = 0; i < katava.size(); i++) {
            System.out.println(katava.get(i).getText());
        }
    }*/

   /* public void handles()
    {
       *//*  ((JavascriptExecutor) driver).executeScript("window.open()");
         ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(0));*//*
        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
        }

    }


    public void dakaAharDake(){
        //  System.out.println(driver.findElement(By.cssSelector("div.main-block div.table_cont_daka")).isDisplayed()+" table");
        //handles();
        waitVisibility(By.cssSelector("div.main-block div.table_cont_daka"));
        By dakaAharDaka1 = By.cssSelector("div.main-block div.table_cont_daka");
        WebElement elementKatava = driver.findElement(dakaAharDaka1);
        List<WebElement> articlesb = elementKatava.findElements(By.tagName("tr"));
        for (int i = 0; i < articlesb.size(); i++) {
            System.out.println(articlesb.get(i).getText());
            List<WebElement> time = elementKatava.findElements(By.cssSelector("td.tblRtColumn"));
            System.out.println(time.get(i).getText());
            WebElement iframe = driver.findElement(By.id("twitter-widget-"+i));
            driver.switchTo().frame(iframe);
            By picture = By.cssSelector(".css-1dbjc4n.r-14lw9ot.r-1ny4l3l.r-iphfwy.r-1qhn6m8.r-i023vh.r-ttdzmv.r-o7ynqc.r-6416eg .css-1dbjc4n.r-1ets6dv.r-1q9bdsx.r-1phboty.r-rs99b7.r-1s2bzr4.r-1udh08x .css-1dbjc4n .css-1dbjc4n.r-1p0dtai.r-1mlwlqe.r-1d2f490.r-1udh08x.r-u8s1d.r-zchlnj.r-ipm5af.r-417010 img");
            WebElement pic = driver.findElement(picture);
            //List<WebElement>pic1 = pic.findElements(By.tagName("img"));
            System.out.println(pic.getAttribute("src"));
        }
    }
*/

   /* public void getArticlesOne() {
        By tohen = By.cssSelector("[itemprop=articleBody]");
        By katava1 = By.cssSelector("[class='article-center-column']");
        WebElement elementKatava = driver.findElement(katava1);
        WebElement element = driver.findElement(tohen);
        List<WebElement> time = driver.findElements(By.cssSelector(".article-body-container .article-credit time"));
        List<WebElement> katava = element.findElements(By.tagName("p"));
        List<WebElement> pic = elementKatava.findElements(By.tagName("img"));
        List<WebElement> koteret = elementKatava.findElements(By.tagName("header"));
        for (int i = 0; i < koteret.size(); i++) {
            System.out.println(koteret.get(i).getText());
        }
        System.out.println(time.get(0).getText());
        System.out.println(pic.get(6).getAttribute("src"));
        for (int i = 0; i < katava.size(); i++) {
            System.out.println(katava.get(i).getText());
        }
    }*/


}
