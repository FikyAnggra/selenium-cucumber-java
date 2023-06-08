package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class googleSearch {

    private WebDriver driver;

    @Given("Saya membuka halaman Google")
    public void saya_membuka_halaman_google() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @When("Saya memasukkan {string} ke dalam kolom pencarian")
    public void enterSearchTerm(String searchTerm) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
    }

    @When("Saya menekan tombol pencarian")
    public void clickSearchButton() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(Keys.RETURN);
    }

    @Then("Saya harus melihat hasil pencarian yang berhubungan dengan {string}")
    public void verifySearchResults(String expectedText) {
        assertTrue(driver.getTitle().contains(expectedText));
        driver.quit();
    }
}
