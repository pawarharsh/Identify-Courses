package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class HomePage extends Base {
    
    WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // WebElement for the search box
    @FindBy(id = "search-autocomplete-input")
    WebElement searchBox;
    
    // List of WebElements for the autocomplete suggestions
    @FindBy(id = "react-autowhatever-1")
    List<WebElement> list;

    // Method to open the URL and maximize the browser window
    public void openURL() {
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
    }
    
    // Method to get the current URL
    public String getUrl() {
        return driver.getCurrentUrl();
    }
    
    // Method to check if the search box is visible
    public boolean isSearchBoxVisible() {
        return searchBox.isDisplayed();
    }
    
    // Method to get the size of the autocomplete suggestion list
    public int autoSuggestionListSize() throws InterruptedException {
        searchBox.click(); // Click the search box
        Thread.sleep(5000); // Wait for suggestions to load
        searchBox.sendKeys("Web Development"); // Enter search term
        Thread.sleep(5000); // Wait for suggestions to update
        int size = list.size(); // Get the size of the suggestion list
        return size;
    }
    
    // Method to click on a specific autocomplete suggestion
    public void click() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-autowhatever-1-section-1-item-2\"]/div/div/div[2]/span")));
        WebElement we = driver.findElement(By.xpath("//*[@id=\"react-autowhatever-1-section-1-item-2\"]/div/div/div[2]/span"));
        we.click(); // Click the suggestion
        Thread.sleep(3000); // Wait for the action to complete
    }
}