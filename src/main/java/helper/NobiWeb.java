package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NobiWeb {

    private static WebDriver driver;

    public static WebDriver openBrowser(String browserName, String browserLink) {

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            driver = new FirefoxDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        } else if (browserName.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            SafariOptions options = new SafariOptions();
            driver = new SafariDriver(options);
        }
        driver.get(browserLink);
        return driver;
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void back() {
        driver.navigate().back();
    }

    public static void click(By ObjectRepository) {
        driver.findElement(ObjectRepository).click();
    }

    public static void check(By ObjectRepository) {
        //belum dilakukan pengetesan
        WebElement checkbox = driver.findElement(ObjectRepository);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static void unCheck(By ObjectRepository) {
        //belum dilakukan pengetesan
        WebElement checkbox = driver.findElement(ObjectRepository);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static WebElement setText(By ObjectRepository,String Text) {
        WebElement element = driver.findElement(ObjectRepository);
        element.sendKeys(Text);
        return element;
    }

    public static String getText(By ObjectRepository) {
        WebElement element = driver.findElement(ObjectRepository);
        return element.getText();
    }

    public static WebElement waitForElementPresent(By ObjectRepository, int Timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Timeout);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository));
    }

    public static void delay(int Timeout) {
        try {
            Thread.sleep(Timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyElementPresent(By ObjectRepository, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository));
            return true;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public static boolean verifyElementNotClickable(By ObjectRepository, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository));
            return false;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return true;
        }
    }
}
