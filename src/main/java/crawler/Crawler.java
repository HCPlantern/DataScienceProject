package crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
/*
accountZ = {15371939295, Zjh320682@nju}
accountD = {13961496689, xjX2MS!WWPZdcT}
 */

public class Crawler {
    WebDriver driver;


    public Crawler() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void go() {
        driver.get("https://wenshu.court.gov.cn/");
        String index = driver.getWindowHandle();
        driver.findElements(By.cssSelector("iframe")).size();
        //login
        WebElement login = driver.findElement(By.id("loginLi"));
        if (login.getText().contains("登录")) {
            login(login, "15371939295", "Zjh320682@nju");
            // 确保返回了首页
            while (!driver.getWindowHandle().equals(index)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e ) {
                    e.printStackTrace();
                }
                login(login, "15371939295", "Zjh320682@nju");
            }
        } // 如果登录不成功，尝试在 1s 后重新登录
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e ) {
            e.printStackTrace();
        }
        // 登录成功
        WebElement search = driver.findElement(By.cssSelector(".search-rightBtn.search-click"));
        //easyCrawl(search, "周岩松");
        crawlByDate(new Date(110, 1, 1), new Date());
        driver.quit();
    }

    private void login(WebElement loginButton, String account, String pswd) {
        if (!loginButton.getText().contains("登录")) return;
        loginButton.click();

        while (driver.findElements(By.cssSelector("iframe")).size() == 0) {
            try {
                Thread.sleep(500);
                driver.navigate().refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 输入框被一个 iframe 包裹, 先切换

        driver.switchTo().frame("contentIframe");
        // 填写用户名和密码
        driver.findElement(By.cssSelector(".phone-number-input")).sendKeys(account);
        driver.findElement(By.cssSelector(".password")).sendKeys(pswd);
        // 登陆后跳转回首页
        driver.findElement(By.cssSelector("span.button-primary")).click();
    }

    private void crawlByDate(Date start, Date end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(start);
        String endDate = format.format(end);
        driver.findElement(By.cssSelector(".advenced-search")).click();
        WebElement startIn = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(By.cssSelector("#cprqStart")));
        startIn.sendKeys(startDate);
        driver.findElement(By.cssSelector("#cprqEnd")).sendKeys(endDate);
        driver.findElement(By.cssSelector("#searchBtn")).click();
        // finish search
    }

    private void easyCrawl(WebElement search, String name) {

        driver.findElement(By.cssSelector(".searchKey.search-inp")).sendKeys(name);
        search.click();
    }

    private void download(WebElement download) {
        download(download, 1);
    }// 默认下载1份

    private void download(WebElement download, int num) {

    }

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.go();
    }
}
