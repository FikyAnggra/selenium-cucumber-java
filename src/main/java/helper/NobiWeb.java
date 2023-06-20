package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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
import utils.ExcelReader;

import java.util.Set;
import java.util.function.Consumer;


public class NobiWeb {

    private static WebDriver driver;
    private static JavascriptExecutor jsExecutor;

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

    public static void refresh() {
        driver.navigate().refresh();
    }

    public static void click(By ObjectRepository) {
        driver.findElement(ObjectRepository).click();
    }

    public static WebElement check(By ObjectRepository) {
        //belum dilakukan pengetesan
        WebElement checkbox = driver.findElement(ObjectRepository);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return checkbox;
    }

    public static WebElement unCheck(By ObjectRepository) {
        //belum dilakukan pengetesan
        WebElement checkbox = driver.findElement(ObjectRepository);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
        return checkbox;
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

    public static void waitForElementPresent(By ObjectRepository, int Timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository));
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            String message = "Element is not present: " + e.getMessage();
            throw new AssertionError(message);
        }
    }

    public static Boolean verifyElementPresent(By ObjectRepository, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository));
            return true;
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            String message = "Element is not present: " + e.getMessage();
            throw new AssertionError(message);
        }
    }

    public static void delay(int Timeout) {
        try {
            Thread.sleep(Timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public static WebElement scrollToElement(By ObjectRepository) {
        WebElement element = driver.findElement(ObjectRepository);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public static void scrollToPosition(int x, int y) {
        String script = String.format("window.scrollTo(%d, %d);", x, y);
        jsExecutor.executeScript(script);
    }

    public static WebElement swithToFrame(By ObjectRepository) {
        WebElement element = driver.findElement(ObjectRepository);
        driver.switchTo().frame(element);
        return element;
    }

    public static void switchToWindowsByIndex(int WindowsIndex) {
        Set<String> windowsHandles = driver.getWindowHandles();
        if (WindowsIndex >= 0 && WindowsIndex < windowsHandles.size()) {
            String[] handles = windowsHandles.toArray(new String[0]);
            driver.switchTo().window(handles[WindowsIndex]);
        } else {
            throw new IllegalArgumentException("Invalid Windows Index : "+WindowsIndex);
        }
    }

    public static void switchToWindowsByTitle(String WindowsTitle) {
        String currentWindowsHandle = driver.getWindowHandle();
        Set<String> windowsHendle = driver.getWindowHandles();

        for (String handle : windowsHendle) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(WindowsTitle)); {
                return;
            }
        }

        driver.switchTo().window(currentWindowsHandle);
        throw new IllegalArgumentException("Windows with title not found :" +WindowsTitle);
    }

    public static void switchToWindowsByUrl(String WindowsUrl) {
        String currentWindowsHandle = driver.getWindowHandle();
        Set<String> windowsHandle = driver.getWindowHandles();

        for (String hanle : windowsHandle) {
            driver.switchTo().window(hanle);
            if (driver.getCurrentUrl().equals(WindowsUrl)) {
                return;
            }
        }

        driver.switchTo().window(currentWindowsHandle);
        throw new IllegalArgumentException("Windows with URL not found : " + WindowsUrl);
    }

    public static void TestDataDriven(String ExcelName, String SheetName, Consumer<Object[]> action) {
        Object[][] testData = ExcelReader.getTestData(ExcelName,SheetName);

        for (Object[] header : testData) {
            action.accept(header);
            closeBrowser();
        }
    }
}
