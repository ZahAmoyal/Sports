package PageObjects;

import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    String password = "a72Y53vXKjhNDAJn";
    String userName = "shilo";
    // MongoClient mongoClient;
    MongoDatabase db;
    //static String fullStory;


    public void createDb(String dbTableName) {
        System.out.println(this.db + " 0");
        String connectionString = "mongodb+srv://" + userName + ":" + password + "@chatnews.uaripa9.mongodb.net/?retryWrites=true&w=majority";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        System.out.println("Created Mongo Connection successfully");

        MongoClient mongoClient = MongoClients.create(connectionString);
        this.db = mongoClient.getDatabase(dbTableName);
        System.out.println(this.db + " 1");
        System.out.println("Get database is successful");
        System.out.println(this.db + " 0B");

    }

    public void mongoInsertData(String title, String summary, String paragraph, String imageLink, int count, String name) {

        MongoCollection<Document> collection = db.getCollection(name);
        InsertOneResult result = collection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("num", count)
                .append("title", title)
                .append("summary", summary)
                .append("paragraph", paragraph)
                .append("imageLink", imageLink));
    }

    public void mongoUpdateData(String summary, String paragraph, String title, String count, int imageLink, String name) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("num", count);


        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject()//.append("date_time", date)
                        .append("title", title)
                        .append("summary", summary)
                        .append("paragraph", paragraph)
                        .append("imageLink", imageLink));
        db.getCollection(name).updateOne(searchQuery, updateQuery);
    }

    public Boolean tableStatus(int i) {
        MongoCollection<Document> collection = db.getCollection("chatNews2");
        if (collection.countDocuments() < i) {
            return false;
        }
        return true;
    }

    //one
    By allArticlesOne = By.cssSelector("div.leagues-right-column");

    //Sport5
    By allArticlesSportFive = By.cssSelector("div.col-sm-12.col-md-12.col-lg-8.col-xs-8.article-list.article-list-alt");

    //Resetting the page
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(6000000));
    }

    //Get Text
    public String getText(By elementLocation) {
        waitVisibility(elementLocation);
        return driver.findElement(elementLocation).getText();
    }

    // A function for waiting for an element's visibility
    public void waitVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6000000));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }

    public void handles() {
       /*  ((JavascriptExecutor) driver).executeScript("window.open()");
         ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(0));*/
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


    public void dakaAharDake() {
        waitVisibility(By.cssSelector("div.main-block div.table_cont_daka"));
        /*By allArticle = By.cssSelector("div.main-block div.table_cont_daka");
        WebElement elementAllArticles = driver.findElement(allArticle);
        List<WebElement> articlesDaD = elementAllArticles.findElements(By.tagName("tr"));
        for (int i = 0; i < articlesDaD.size(); i++) {
            System.out.println(articlesDaD.get(i).getText());
            WebElement time_Date = driver.findElement(By.className(articlesDaD.get(i).getAttribute("class")));
            System.out.println(time_Date.findElement(By.cssSelector("div.first")).getText());
            String pic = articlesDaD.get(i).getAttribute("src");
            System.out.println(pic);*/
            /*//articlesDaD.get(i).By.cssSelector("td.tblRtColumn"));
            System.out.println(time.get(i).getText());
            List<WebElement>iframes = elementAllArticles.findElements(By.tagName("iframe"));
            driver.switchTo().frame(iframes.get(i).getAttribute("id"));


            //WebElement iframe = driver.findElement(By.id("twitter-widget-" + i));
            //driver.switchTo().frame(iframe);
            By picture = By.cssSelector(".css-1dbjc4n.r-14lw9ot.r-1ny4l3l.r-iphfwy.r-1qhn6m8.r-i023vh.r-ttdzmv.r-o7ynqc.r-6416eg .css-1dbjc4n.r-1ets6dv.r-1q9bdsx.r-1phboty.r-rs99b7.r-1s2bzr4.r-1udh08x .css-1dbjc4n .css-1dbjc4n.r-1p0dtai.r-1mlwlqe.r-1d2f490.r-1udh08x.r-u8s1d.r-zchlnj.r-ipm5af.r-417010 img");
            WebElement pic = driver.findElement(picture);
            List<WebElement>pic1 = pic.findElements(By.tagName("a"));
            System.out.println(pic1.get(i).getAttribute("href"));*/
        }


    public void oneArticles(String url) {
        createDb("GQ-Dashboard");
        driver.get(url);
        WebElement web = driver.findElement(allArticlesOne);
        List<WebElement> articles = web.findElements(By.tagName("a"));
        System.out.println(articles.size() + " size");
        for (int i = 0; i < articles.size() - 1; i++) {
            System.out.println("-------------------------------");
            System.out.println("One");
            WebElement webb = driver.findElement(allArticlesOne);
            List<WebElement> articlesb = webb.findElements(By.tagName("a"));
            articlesb.get(i).click();
            getArticlesOne(i + 1);
            driver.navigate().back();
        }
    }

    public void getArticlesOne(int countx) {
        String fullStory=null;
        By story = By.cssSelector("div.article-center-column");
        waitVisibility(story);
        WebElement elementStory = driver.findElement(story);
        By articleContent = By.cssSelector("[itemprop=articleBody]");
        waitVisibility(articleContent);
        WebElement element = driver.findElement(articleContent);
        List<WebElement> date_timeList = driver.findElements(By.cssSelector(".article-body-container .article-credit time"));
        List<WebElement> articleContentList = element.findElements(By.tagName("p"));
        List<WebElement> picList = elementStory.findElements(By.tagName("img"));
        String title1 = elementStory.findElement(By.tagName("h1")).getText();
        String subtitle1 = elementStory.findElement(By.tagName("h2")).getText();
        System.out.println(title1);
        System.out.println(subtitle1);
        System.out.println(date_timeList.get(0).getText());
        String imagePath = picList.get(6).getAttribute("src");
        System.out.println(imagePath);
        for (int i = 0; i < articleContentList.size(); i++) {
            fullStory += articleContentList.get(i).getText() + '\n';
            System.out.println(articleContentList.get(i).getText());
        }
        if (!tableStatus(articleContentList.size())) {
            mongoInsertData(title1, fullStory, subtitle1, imagePath, countx, "One");
        } else {
            mongoUpdateData(title1, fullStory, subtitle1, imagePath, countx, "One");
            System.out.println("Title: " + title1);
            System.out.println("SubTitle: " + subtitle1);
            System.out.println("summery: " + fullStory);
            System.out.println("picLink: " + imagePath);
            System.out.println("num" + countx);
        }

    }

    public void SportFiveArticles(String url) throws InterruptedException {
        createDb("GQ-Dashboard");
        driver.get(url);
        String handle = driver.getWindowHandle();
        WebElement article = driver.findElement(allArticlesSportFive);
        List<WebElement> articles = article.findElements(By.cssSelector("div.section.section-.color-alt- h2 a"));
        System.out.println(articles.size() + " size");
        for (int i = 0; i < articles.size(); i++) {
            System.out.println("-------------------------------");
            System.out.println("Sport5");
        /*    WebElement article1 = driver.findElement(allArticlesSportFive);
            List<WebElement> articles = article1.findElements(By.cssSelector("div.section.section-.color-alt- h2"));*/
            System.out.println(i + " III");
            System.out.println(articles.get(i).getText());
            System.out.println(articles.size());
            Thread.sleep(3000);
    /*        Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL).
                    click(articles.get(i)).
                    keyUp(Keys.LEFT_CONTROL).
                    build().perform();*/
            String urlNew = articles.get(i).getAttribute("href");
            System.out.println("urlNew "+urlNew);
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", urlNew);
            handles();
            System.out.println("Target - " + driver.getWindowHandle());
            String title = driver.getTitle();
            //System.out.println(title);
            System.out.println(title.contains("דקה אחר דקה"));
            if (!driver.getTitle().contains("דקה אחר דקה"))
                //dakaAharDake();
                getArticlesSportFive(i + 1);
            /*else {
                getArticlesSportFive(i + 1);
            }*/
            driver.close();
            driver.switchTo().window(handle);
            article = driver.findElement(allArticlesSportFive);
             articles = article.findElements(By.cssSelector("div.section.section-.color-alt- h2 a"));
            System.out.println(articles.get(i).getAttribute("href"));
        }
    }

    public void getArticlesSportFive(int countx) {
        String title = getTitle();
        String subTitle = getSubTitle();
        By articleContent = By.cssSelector("div.article-content");
        By picture = By.cssSelector("div.content-holder");
        WebElement pictureElement = driver.findElement(picture);
        WebElement elementArticleContent = driver.findElement(articleContent);
        List<WebElement> pic = pictureElement.findElements(By.tagName("img"));
        String image =getImagePath(pic);
        //System.out.println(pic.size());

        //String image = (pic.get(0).getAttribute("src"));
        List<WebElement> storyList = elementArticleContent.findElements(By.tagName("p"));
        String fullStory = getStory(storyList);
/*        for (WebElement story : storyList) {
            fullStory += story.getText() + '\n';
        }*/
        if (!tableStatus(storyList.size())) {
            mongoInsertData(title, fullStory, subTitle, image, countx, "sport5");
        } else {
            mongoUpdateData(title, fullStory, subTitle, image, countx, "sport5");
            System.out.println("Title: " + title);
            System.out.println("SubTitle: " + subTitle);
            System.out.println("summery: " + fullStory);
            System.out.println("picLink: " + image);
            System.out.println("num" + countx);
        }
    }

    public String getTitle() {
        By title = By.cssSelector("h1.article-main");
        return getText(title);
    }

    public String getSubTitle() {
        By subTitle = By.cssSelector("h2.article-sub-main");
        return getText(subTitle);
    }
    public String getStory(List<WebElement>storyList){
        String fullStory1 = null;
        for (WebElement story : storyList) {
            fullStory1 += story.getText() + '\n';
        }
        return fullStory1;
    }
    public String getImagePath(List<WebElement>pic){
        return pic.get(1).getAttribute("src");
    }
}


