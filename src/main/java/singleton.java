
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class singleton {
    public static WebDriver driver = null;
    public static String browserName = "chrome";

    public static void initialize() {
        assert driver != null;
        driver.manage().window().maximize();
        singleton.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tempmail.plus/ru/");
    }

    public static void quit(){
        driver.quit();
    }
    public static WebDriver getDriver(){
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver","./chromedriver/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }
}