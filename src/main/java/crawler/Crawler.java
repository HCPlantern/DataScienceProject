package crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
/*
accountZ = {15371939295, Zjh320682@nju}
accountD = {13961496689, xjX2MS!WWPZdcT}
 */

public class Crawler {
    WebDriver driver;
    int WAIT_SECONDS = 3;

    public Crawler() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    public Crawler(int waitSecond) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        WAIT_SECONDS = waitSecond;
    }


    public void go() {
        driver.get("https://wenshu.court.gov.cn/");
        String index = driver.getWindowHandle();

        //login
        WebElement login = driver.findElement(By.id("loginLi"));
        if (login.getText().contains("登录")) {
            login(login, "15371939295", "Zjh320682@nju");
            // 确保返回了首页
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assert (!driver.getWindowHandle().equals(index));
        }
        // 登录成功
        WebElement search = driver.findElement(By.cssSelector(".search-rightBtn.search-click"));
        easyCrawl(search, "周岩松");

        //WebElement adSearch = driver.findElement(By.cssSelector(".advenced-search"));
        //crawlByDate(adSearch, new Date(110, 1, 1), new Date());
        download();
        driver.quit();
    }

    private void login(WebElement loginButton, String account, String pswd) {
        if (!loginButton.getText().contains("登录")) return;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginButton.click();

        while (driver.findElements(By.cssSelector("iframe")).size() == 0) {
            try {
                Thread.sleep(1000);
                driver.navigate().refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 输入框被一个 iframe 包裹, 先切换
        driver.switchTo().frame("contentIframe");
        // 填写用户名和密码
        WebElement login = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(driver -> driver.findElement(By.cssSelector(".phone-number-input")));
        login.sendKeys(account);
        driver.findElement(By.cssSelector(".password")).sendKeys(pswd);
        // 登陆后跳转回首页
        driver.findElement(By.cssSelector("span.button-primary")).click();
    }

    private void crawlByDate(WebElement adSearch, Date start, Date end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(start);
        String endDate = format.format(end);
        adSearch.click();
        WebElement startIn = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(driver -> driver.findElement(By.cssSelector("#cprqStart")));
        startIn.sendKeys(startDate);
        driver.findElement(By.cssSelector("#cprqEnd")).sendKeys(endDate);
        try {
            Thread.sleep(1000);
            driver.navigate().refresh();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("#searchBtn")).click();
        // finish search
    }

    private void easyCrawl(WebElement search, String name) {

        driver.findElement(By.cssSelector(".searchKey.search-inp")).sendKeys(name);
        try {
            Thread.sleep(1000);
            driver.navigate().refresh();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search.click();
    }

    private void download() {
        download(1);
    }// 默认下载1份

    private void download(int num) {
        //检索之后，一个页面有5个记录
        List<WebElement> pages = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until((driver -> driver.findElements(By.cssSelector(".caseName"))));
        driver.getWindowHandle();
        for (WebElement page : pages) {
            pageDownload(page, driver.getWindowHandle());
        }
        num -= pages.size();
        if (num > 0) {
            WebElement nextPage = driver.findElement(By.linkText("下一页"));
            nextPage.click();
            download(num);
        }
    }

    private void pageDownload(WebElement tab, String previosHandle) {
        tab.click();
        WebElement pdfPox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until((driver -> driver.findElement(By.cssSelector(".PDF_pox")))); //not Box, pox is content
        String pdfTitle = driver.findElement(By.cssSelector(".PDF_title")).getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources" + pdfTitle))) {
            writer.write(pdfPox.getAttribute("innerHTML"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.switchTo().window(previosHandle);
    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.go();
    }
}
