package com.workhard.wenshu.service;

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
    int WAIT_SECONDS = 5;

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
        login("15371939295", "Zjh320682@nju", index);

        easyCrawl("周岩松");
        //crawlByDate(new Date(110, 1, 1), new Date());
        download(6);

        driver.quit();
    }

    private void back2HomePage() {
        driver.findElement(By.cssSelector(".case.souye")).click();
    }

    private void refresh() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean login(String account, String pswd, String preIndex) {
        WebElement loginButton = driver.findElement(By.id("loginLi"));
        if (!loginButton.getText().contains("登录")) return true;

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
        WebElement login = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS).getSeconds())
                .until(driver -> driver.findElement(By.cssSelector(".phone-number-input")));
        login.sendKeys(account);
        driver.findElement(By.cssSelector(".password")).sendKeys(pswd);
        // 登陆后跳转回首页
        driver.findElement(By.cssSelector("span.button-primary")).click();
        refresh();

        return !loginButton.getText().contains("登录") && !driver.getWindowHandle().equals(preIndex);
        // 确保返回了首页
        // 登录成功
    }


    private void crawlByDate(Date start, Date end) {
        WebElement adSearch = driver.findElement(By.cssSelector(".advenced-search"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(start);
        String endDate = format.format(end);
        adSearch.click();
        WebElement startIn = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS).getSeconds())
                .until(driver -> driver.findElement(By.cssSelector("#cprqStart")));
        startIn.sendKeys(startDate);
        driver.findElement(By.cssSelector("#cprqEnd")).sendKeys(endDate);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("#searchBtn")).click();
        // finish search
    }

    private void easyCrawl(String name) {
        WebElement search = driver.findElement(By.cssSelector(".search-rightBtn.search-click"));
        driver.findElement(By.cssSelector(".searchKey.search-inp")).sendKeys(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search.click();
        refresh();
    }

    private void download() {
        download(1);
    }// 默认下载1份

    private void download(int num) {
        //检索之后，一个页面有5个记录

        List<WebElement> pages = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS * 3).getSeconds())
                .until((driver -> driver.findElements(By.cssSelector(".caseName"))));
        String handle = driver.getWindowHandle();

        if (pages.size() == 0) {
            refresh();
            pages = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS * 3).getSeconds())
                    .until((driver -> driver.findElements(By.cssSelector(".caseName"))));
            if (pages.size() == 0) {
                System.err.println("No such case");
                return;
            }
        }

        if (pages.size() < 5) {
            for (WebElement page : pages) pageDownload(page, handle);
            return;
        } //如果单页的记录少于5个，说明就这么多
        if (num > 5) {
            for (WebElement page : pages) {
                pageDownload(page, handle);
            }
            WebElement nextPage = driver.findElement(By.linkText("下一页"));
            nextPage.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            download(num - pages.size());
        } else {
            for (int i = 0; i < num; i++) {
                pageDownload(pages.get(i), handle);
            }
        }
    }

    private void pageDownload(WebElement tab, String previousHandle) {
        tab.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!previousHandle.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        refresh();
        String pdfTitle = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS).getSeconds())
                .until(driver -> driver.findElement(By.cssSelector(".PDF_title")).getText());
        WebElement pdfPox = driver.findElement(By.cssSelector(".PDF_pox"));
        StringBuilder toWriteIn = new StringBuilder();
        driver.findElements(By.cssSelector("#iframedf .text-ellipsis"))
                .forEach(WebElement -> toWriteIn.append(WebElement.getText()));
        toWriteIn.append(System.lineSeparator());
        toWriteIn.append(pdfPox.getText());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/" + pdfTitle))) {
            writer.write(toWriteIn.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.close();
        driver.switchTo().window(previousHandle);
    }

}
