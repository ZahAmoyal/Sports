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
    //    String password = "a72Y53vXKjhNDAJn";
//    String userName = "shilo";
    // MongoClient mongoClient;
    MongoDatabase db;
    static String fullStory;


    public void createDb(String dbTableName) {
        System.out.println(this.db + " 0");

        String connectionString = "mongodb+srv://yaal-2122:wsmJQ3ggbFxFtHX@cluster0.qnlfmxm.mongodb.net/GQ-Dashboard?retryWrites=true&w=majority";
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

    public void mongoInsertData(int count, String author, String title, String subtitle, String summary, String time, String date, String image, String video) {
        String name = "sportnews";
        MongoCollection<Document> collection = db.getCollection(name);
        InsertOneResult result = collection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("num", count)
                .append("author", author)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("summary", summary)
                .append("time", time)
                .append("date", date)
                .append("image", image)
                .append("video", video));
    }

    public void mongoUpdateData(int count, String author, String title, String subtitle, String summary, String time, String date, String image, String video) {
        String name = "sportnews";
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("num", count);


        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
                new BasicDBObject()
                        .append("author", author)
                        .append("title", title)
                        .append("subtitle", subtitle)
                        .append("summary", summary)
                        .append("time", time)
                        .append("date", date)
                        .append("image", image)
                        .append("video", video));


        db.getCollection(name).updateOne(searchQuery, updateQuery);
    }

    public Boolean tableStatus(int i) {
        MongoCollection<Document> collection = db.getCollection("chatNews2");
        if (collection.countDocuments() != i) {
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
        By allArticle = By.cssSelector("div.main-block div.table_cont_daka");
        WebElement elementAllArticles = driver.findElement(allArticle);
        List<WebElement> articlesDaD = elementAllArticles.findElements(By.tagName("tr"));
        for (int i = 0; i < articlesDaD.size(); i++) {
            System.out.println(articlesDaD.get(i).getText());
            WebElement time_Date = driver.findElement(By.className(articlesDaD.get(i).getAttribute("class")));
            System.out.println(time_Date.findElement(By.cssSelector("div.first")).getText());
            String pic = articlesDaD.get(i).getAttribute("src");
            System.out.println(pic);
            //articlesDaD.get(i).By.cssSelector("td.tblRtColumn");
            //System.out.println(time.get(i).getText());
            List<WebElement> iframes = elementAllArticles.findElements(By.tagName("iframe"));
            driver.switchTo().frame(iframes.get(i).getAttribute("id"));


            WebElement iframe = driver.findElement(By.id("twitter-widget-" + i));
            driver.switchTo().frame(iframe);
            By picture = By.cssSelector(".css-1dbjc4n.r-14lw9ot.r-1ny4l3l.r-iphfwy.r-1qhn6m8.r-i023vh.r-ttdzmv.r-o7ynqc.r-6416eg .css-1dbjc4n.r-1ets6dv.r-1q9bdsx.r-1phboty.r-rs99b7.r-1s2bzr4.r-1udh08x .css-1dbjc4n .css-1dbjc4n.r-1p0dtai.r-1mlwlqe.r-1d2f490.r-1udh08x.r-u8s1d.r-zchlnj.r-ipm5af.r-417010 img");
            WebElement picture1 = driver.findElement(picture);
            List<WebElement> pic1 = picture1.findElements(By.tagName("a"));
            System.out.println(pic1.get(i).getAttribute("href"));
        }
    }


    public void oneArticles(String url, int check) {
        createDb("GQ-Dashboard");
        driver.get(url);
        WebElement web = driver.findElement(allArticlesOne);
        List<WebElement> articles = web.findElements(By.tagName("a"));
        System.out.println(articles.size() + " size");
        for (int i = 0; i < 5; i++) {
            System.out.println("-------------------------------");
            System.out.println("One");
            System.out.println(check);
            WebElement webb = driver.findElement(allArticlesOne);
            List<WebElement> articlesb = webb.findElements(By.tagName("a"));
            articlesb.get(i).click();
            By story = By.cssSelector("div.article-center-column");
            waitVisibility(story);
            WebElement elementStory = driver.findElement(story);
            if (elementStory.getText().contains("דעת המבקר")) {
                driver.navigate().back();
            } else {
                check++;
                getArticlesOne(check, elementStory);
                driver.navigate().back();
            }
        }
    }

    public void getArticlesOne(int countx, WebElement elementStory) {
        String fullStory = null;
        String author = "One";
        By articleContent = By.cssSelector("[itemprop=articleBody]");
        waitVisibility(articleContent);
        WebElement element = driver.findElement(articleContent);
        By time_date = By.cssSelector(".article-body-container .article-credit time");
        String[] arr = getText(time_date).split(" ");
        String time = arr[1];
        String date = arr[0];
        List<WebElement> articleContentList = element.findElements(By.tagName("p"));
        List<WebElement> picList = elementStory.findElements(By.tagName("img"));
        String title = elementStory.findElement(By.tagName("h1")).getText();
        String subtitle = elementStory.findElement(By.tagName("h2")).getText();
        System.out.println(title);
        System.out.println(subtitle);
        //System.out.println(date_timeList.get(0).getText());
        String image = picList.get(6).getAttribute("src");
        String video = null;
        //System.out.println(imagePath);
     /*   for (int i = 0; i < articleContentList.size(); i++) {
            fullStory += articleContentList.get(i).getText() + '\n';
            System.out.println(articleContentList.get(i).getText());
        }
        fullStory = fullStory.replace("null", "");*/
        fullStory = getFullStoryOne(articleContentList);
        mongoDBSportOne(countx, author, title, subtitle, fullStory, time, date, image, video);
        fullStory = "";
      /*  if (tableStatus(20)) {
            mongoInsertData(countx, author, title, subtitle ,fullStory, time, date, image, video);
        } else {
            mongoUpdateData(countx, author, title, subtitle ,fullStory, time, date, image, video);
            System.out.println("num" + countx);
            System.out.println("author: " + author);
            System.out.println("title: " + title);
            System.out.println("subtitle: " + subtitle);
            System.out.println("summery: " + fullStory);
            System.out.println("time: " + time);
            System.out.println("date: " + date);
            System.out.println("image: " + image);
            System.out.println("video: " + video);
        }*/
    }


    // Is displayed
    public boolean displayed(By elementLocation) {
        waitVisibility(elementLocation);
        return driver.findElement(elementLocation).isDisplayed();
    }

    public boolean check(By by) {
        if (displayed(by))
            return true;
        return false;
    }
int num = 0;
    public void SportFiveArticles(String url, int zah) throws InterruptedException {
        createDb("GQ-Dashboard");
        driver.get(url);
        firstArticleSport5(num);
        num=num+5;
        String handle = driver.getWindowHandle();
        WebElement article = driver.findElement(allArticlesSportFive);
        List<WebElement> articles = article.findElements(By.cssSelector("div.section.section-.color-alt- h2 a"));
        System.out.println(articles.size() + " size");
        for (int i = 0; i < 4; i++) {
            System.out.println("-------------------------------");
            System.out.println("Sport5");
            System.out.println(zah);
            System.out.println(i + " III");
            System.out.println(articles.get(i).getText());
            System.out.println(articles.size());
            Thread.sleep(3000);
            String urlNew = articles.get(i).getAttribute("href");
            System.out.println("urlNew " + urlNew);
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", urlNew);
            handles();
            System.out.println("Target - " + driver.getWindowHandle());
            String title = driver.getTitle();
            //System.out.println(title);
            System.out.println(title.contains("דקה אחר דקה"));
            if (!driver.getTitle().contains("דקה אחר דקה")) {
                //dakaAharDake();
                zah++;
                System.out.println("The number is :"+zah);
                getArticlesSportFive(zah);
                driver.close();
                driver.switchTo().window(handle);
            } else {
                driver.close();
                driver.switchTo().window(handle);
            }
            article = driver.findElement(allArticlesSportFive);
            articles = article.findElements(By.cssSelector("div.section.section-.color-alt- h2 a"));
            System.out.println(articles.get(i).getAttribute("href"));
        }
        zah = zah + 5;
    }

    public String[] date_time() {
        By time_date = By.cssSelector("span.hint");
        String[] arr = getText(time_date).split(" ");
        return arr;
    }

    public String time(String[] arr) {
        String time = arr[4].substring(0, 5);
        return time;
    }

    public String date(String[] arr) {
        String date = arr[2];
        return date;
    }

    public void getArticlesSportFive(int countx) {
        String author = "Sport5";
  /*      String title = getTitle();
        String subtitle = getSubtitle();
        String image = getImagePath();*/
        //System.out.println(pic.size());
        String video = null;
        //String image = (pic.get(0).getAttribute("src"));
        //fullStory = getFullStorySport5();
        /*fullStory = fullStory.replace("null", "");*/

        mongoDBSport5(countx, author, getTitle(), getSubtitle(), getFullStorySport5(), time(date_time()), date(date_time()), getImagePath(), video);
        fullStory = "";
    }

    public void mongoDBSport5(int countx, String author, String title, String subtitle, String fullStory, String time, String date, String image, String video) {
/*
        if (tableStatus(10)) {
*/
        mongoInsertData(countx, author, title, subtitle, fullStory, time, date, image, video);
/*        } else {
            mongoUpdateData(countx, author, title, subtitle, fullStory, time, date, image, video);
            System.out.println("num" + countx);
            System.out.println("author: " + author);
            System.out.println("title: " + title);
            System.out.println("subtitle: " + subtitle);
            System.out.println("summery: " + fullStory);
            System.out.println("time:" + time);
            System.out.println("date:" + date);
            System.out.println("picLink: " + image);
            System.out.println("video: " + video);
        }*/
    }

    public void mongoDBSportOne(int countx, String author, String title, String subtitle, String fullStory, String time, String date, String image, String video) {
        /*   if (tableStatus(20)) {*/
        mongoInsertData(countx, author, title, subtitle, fullStory, time, date, image, video);
      /*  } else {
            mongoUpdateData(countx, author, title, subtitle, fullStory, time, date, image, video);
            System.out.println("num" + countx);
            System.out.println("author: " + author);
            System.out.println("title: " + title);
            System.out.println("subtitle: " + subtitle);
            System.out.println("summery: " + fullStory);
            System.out.println("time:" + time);
            System.out.println("date:" + date);
            System.out.println("picLink: " + image);
            System.out.println("video: " + video);
        }*/
    }


    public String getTitle() {
        By title = By.cssSelector("h1.article-main");
        return getText(title);
    }

    public String getSubtitle() {
        By subtitle = By.cssSelector("h2.article-sub-main");
        return getText(subtitle);
    }

    public String getFullStorySport5() {
        By articleContent = By.cssSelector("div.article-content");
        WebElement elementArticleContent = driver.findElement(articleContent);
        List<WebElement> storyList = elementArticleContent.findElements(By.tagName("p"));
        /*if (storyList.size() == 0) {
            List<WebElement> fullStoryList = elementArticleContent.findElements(By.tagName("strong"));
            for (WebElement story : fullStoryList) {
                fullStory += story.getText() + '\n';
            }
            fullStory = fullStory.replace("null", "");
            return fullStory;
        } else {*/
        String fullStory = null;
        for (WebElement story : storyList) {
            fullStory += story.getText() + '\n';
        }
        fullStory = fullStory.replace("null", "");
        return fullStory;
    }


    public String getFullStoryOne(List<WebElement> storyList) {
        String fullStory = null;
        for (WebElement story : storyList) {
            fullStory += story.getText() + '\n';
        }
        fullStory = fullStory.replace("null", "");
        return fullStory;
    }

    public String getImagePath() {
        By picture = By.cssSelector("div.main-block");
        WebElement pictureElement = driver.findElement(picture);
        List<WebElement> pic = pictureElement.findElements(By.tagName("img"));
        String url = "https://www.יחצ.co.il/wp-content/uploads/2019/04/%D7%A1%D7%A4%D7%A8.jpg";
        if (pic.size() == 1)
            return url;
        return pic.get(1).getAttribute("src");
    }

    public int firstArticleSport5(int countx) {
        String author = "Sport5";
        String video = null;
        String handle = driver.getWindowHandle();
        WebElement firstArticle = driver.findElement(By.cssSelector("div.word-main-article.main-article.banner-holder.mainarticle-world .desc h2"));
        String urlNew = firstArticle.getAttribute("href");
        System.out.println("urlNew " + urlNew);
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", urlNew);
        handles();
        if (!driver.getTitle().contains("דקה אחר דקה")) {
            mongoDBSport5(countx, author, getTitle(), getSubtitle(), getFullStorySport5(), time(date_time()), date(date_time()), getImagePath(), video);
            driver.close();
            driver.switchTo().window(handle);
        }else{
            driver.close();
            driver.switchTo().window(handle);
            }
        driver.navigate().back();
        countx = countx++;
        return countx;
    }


        public void iframes() {
            By allPageContent = By.cssSelector(".twitter-tweet.twitter-tweet-rendered");
            List<WebElement> allIframes = driver.findElements(By.cssSelector("iframe"));
            for (int i = 0; i < allIframes.size(); i++) {
                WebElement pic = driver.findElement(By.id(allIframes.get(i).getAttribute("iframe")));
                System.out.println(pic.findElement(By.cssSelector("")));
            }
        }
    }


