package crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Crawler {
    WebDriver driver;

    public Crawler() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }


    public void go() {
        driver.get("https://wenshu.court.gov.cn/");
        String index = driver.getWindowHandle();

        //login
        WebElement login = driver.findElement(By.id("loginLi"));
        if (login.getText().contains("登录")) {
            login(login);
            // 确保返回了首页
            assert driver.getWindowHandle().equals(index);
            // TODO 若首页没有刷新登录状态, 则刷新首页
        }

        // 接下来正式开始爬文件

/*        driver.findElement(By.linkText("刑事案件")).click();
        System.out.println(driver.getCurrentUrl());*/
        driver.quit();
    }

    private void login(WebElement loginButton) {
        String user = "13961496689";
        String passwd = "xjX2MS!WWPZdcT";
        loginButton.click();
        // 在点击登录界面之后等待若干秒再刷新，以免触发验证码
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 不刷新就加载不出来填写框
        driver.navigate().refresh();
        // 输入框被一个 iframe 包裹, 先切换
        driver.switchTo().frame("contentIframe");
        // 填写用户名和密码
        driver.findElement(By.cssSelector(".phone-number-input")).sendKeys(user);
        driver.findElement(By.cssSelector(".password")).sendKeys(passwd);
        // 登陆后跳转回首页
        driver.findElement(By.cssSelector("span.button-primary")).click();
    }


    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.go();
    }
}
