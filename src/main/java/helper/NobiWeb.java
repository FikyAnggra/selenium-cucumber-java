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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class NobiWeb {

    private static WebDriver driver;

    public static WebDriver OpenBrowser(String browserName, String browserLink) {

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

    public static void CloseBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void Click(By ObjectRepository) {
        driver.findElement(ObjectRepository).click();
    }

    public static WebElement SetText(String Text, By ObjectRepository) {
        WebElement searchBox = driver.findElement(ObjectRepository);
        searchBox.sendKeys(Text);
        return searchBox;
    }

    public static WebElement WaitForElementPresent(By ObjectRepository, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository));
    }

    public static boolean VerifyElementPresent(By ObjectRepository, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository));
            return true;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public static By ElementTagByParameter(String elementTag, String elementParameter, String elementValue) {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/ObjectRepository/ElementTagByParameter.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            String xpath = properties.getProperty("xpath");
            String formattedXpath = xpath.replace("${elementTag}", elementTag)
                    .replace("${elementParameter}", elementParameter)
                    .replace("${elementValue}", elementValue);

            return By.xpath(formattedXpath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static By ElementTagByText(String elementTag, String elementText) {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/java/ObjectRepository/ElementTagByText.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            String xpath = properties.getProperty("xpath");
            String formattedXpath = xpath.replace("${elementTag}", elementTag)
                    .replace("${elementText}", elementText);

            return By.xpath(formattedXpath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
